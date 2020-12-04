package application.models;

import java.util.ArrayList;
import java.util.Date;

import application.models.Abstracts.Client;
import application.models.Abstracts.Pharmacie;
import application.models.Enums.TypeProduitPharmaceutique;
import application.models.Patterns.CommandTransaction.CommandTransaction;

public class PharmacieFranchisée extends Pharmacie {

	private PharmacieFranchisée parent;
	private ArrayList<PharmacieFranchisée> franchises;
	private CompteFranchisé compteFranchisé;

	public PharmacieFranchisée(String nom, int surfaceCommerciale, String siret, Pays pays) {
		super(nom,surfaceCommerciale,siret,pays);		
		this.parent = null;
	}
	
	public PharmacieFranchisée(String nom, int surfaceCommerciale, String siret, Pays pays, PharmacieFranchisée parent) {
		super(nom,surfaceCommerciale,siret,pays);		
		this.parent = parent;
	}
	
	/**
	 * Return the discount for the current pharmacy
	 */
	public Double getReduction(Pharmacie seller) {
		
		// Total percentage
		double percentages = 0.0;
		
		PharmacieFranchisée current = this.parent;
		
		// While have parent
		while(current != null && current != seller) {
			
			// Get the number of subsidiaries for the current parent
			int nbr = getFranchises().size();

			// Compute discount
			if(2 <= nbr && nbr <= 4) {
				percentages += 2.5;
			}
			else if(5 <= nbr && nbr <= 10) {
				percentages += 5.0;
			}
			else if(nbr > 10) {
				percentages += 7.5;
			}
			
			// Go up in parent tree
			current = current.parent;
		}
		
		// Return the discount percentage
		return percentages;
	}
	
	/**
	 * Compute the royalties percentage
	 * @return
	 */
	public Double calculPercentageRoyalties() {
		
		// Total percentage
		double percentages = 0.0;
		
		// Number of subsidiaries
		int nbr = this.getAllPharmaciesFranchisées().size();
		
		// Compute royalties percentage
		if(2 <= nbr && nbr <= 4) {
			percentages += 1.0;
		}
		else if(5 <= nbr && nbr <= 10) {
			percentages += 2.0;
		}
		else if(nbr > 10) {
			percentages += 3.0;
		}

		// Return the royalties percentage
		return percentages;
	}
	
	/**
	 * Quand ont souhaite payer les royalties en fin de mois
	 */
	public void payRoyalties(Date date) {
		
		// TODO: Confirm the receivers
//		PharmacieFranchisée receiver = getRootParent();
		PharmacieFranchisée receiver = this.parent;
		
		// If we are the root
		if (receiver == null) {
			return;
		}
		
		// List of products
		ArrayList<ProduitPharmaceutique> p = new ArrayList<ProduitPharmaceutique>();
		
		// Add the royalties
		p.add(new ProduitPharmaceutique(
			"Royalties",
			TypeProduitPharmaceutique.Royalties,
			getMontantFinMoisRoyalties(date),
			0.0,
			null
		));

		// Buy the royalties to the receiver
		receiver.vendre(p, this, 0);
	}

	/**
	 * Compute the amount of royalties (in currency) to paid to the parent
	 * @return
	 */
	public Double getMontantFinMoisRoyalties(Date date) {
		return getChiffreAffaireBeforeRoyalties(date) * (1 - calculPercentageRoyalties());
	}
	
	/**
	 * Compute the revenue with the royalties
	 * @param date The month date
	 * @return revenue
	 */
	public Double calculChiffreAffaire(Date date) {
		
		// Total revenues
		double chiffreAffaire = 0.0;
		
		// Get all the transactions
		ArrayList<Transaction> transactions = DBTransaction.getInstance().getAll(this, date);
		
		// For each transaction
		for (Transaction transaction : transactions) {

			// Add the price
			chiffreAffaire += transaction.getMontant();
		}
		
		// Return the revenue without the royalties
		return chiffreAffaire;
	}

	/**
	 * Check if has any subsidaries
	 * @return
	 */
	public boolean hasSubsidiaries() {
		return getFranchises().size() > 0;
	}
	
	/**
	 * Return all the subsidiaries
	 * @return
	 */
	public ArrayList<PharmacieFranchisée> getAllPharmaciesFranchisées() {

		// List of subsidiaries
	    ArrayList<PharmacieFranchisée> subsidiaries = new ArrayList<PharmacieFranchisée>();
	    
	    // Add the current pharmacy subsidiaries
	    subsidiaries.addAll(this.getFranchises());

	    // For each subsidiaries
	    for (PharmacieFranchisée p : subsidiaries) {
	    	
	    	// If has subsidiaries
	    	if (p.hasSubsidiaries() == true) {
	    		
	    		// Add the subsidiaries of subsidiaries 
	    		subsidiaries.addAll(p.getFranchises());
			}			
		}
	    
	    // return the subsidiaries
	    return subsidiaries;
	}
	
	/**
	 * We sale a product to a client for a specific price
	 * @param produits
	 * @param client
	 * @return
	 */
	public CommandTransaction vendre(ArrayList<ProduitPharmaceutique> produits, Client client, int carteClient) {

		// Total price
		double montantPanier = 0.0;
		
		// For each products
		for (ProduitPharmaceutique p : produits) {
			
			// Add the price to the cart
			montantPanier += p.getPrixVente();
		}
		
		// Check if subsidiary of us
		if(this.getAllPharmaciesFranchisées().contains(client)) {

			// We sale the products at the custom price
			return getCompteFranchisé().paiement(produits, montantPanier, client, this, carteClient);
		}
		
		// We sale the products at the base price
		return getCompteClassique().paiement(produits, montantPanier, client, this, carteClient);
	}
	
	public PharmacieFranchisée getParent() {
		return parent;
	}
	
	public void setParent(PharmacieFranchisée parent) {
		this.parent = parent;
	}
	
	public ArrayList<PharmacieFranchisée> getFranchises() {
		return franchises;
	}
	
	public void setFranchises(ArrayList<PharmacieFranchisée> franchises) {
		this.franchises = franchises;
	}

	public CompteFranchisé getCompteFranchisé() {
		return compteFranchisé;
	}
	
	public void setCompteFranchisé(CompteFranchisé compteFranchisé) {
		this.compteFranchisé = compteFranchisé;
	}

	@Override
	public String getType() {
		return "Franchisée";
	}	
}
