package application.models;

import java.util.ArrayList;
import java.util.Date;

import application.models.Abstracts.CarteBancaire;
import application.models.Abstracts.Client;
import application.models.Abstracts.Pharmacie;
import application.models.Enums.TypeProduitPharmaceutique;

public class Transaction implements Cloneable {
	
	private Pharmacie vendeur;
	private Client acheteur;
	private CarteBancaire carteClient;
	private Date date;
	private Double montant;
	private boolean débité = false;
	private ArrayList<ProduitPharmaceutique> produits;
	
	public Transaction(
		Pharmacie vendeur,
		Client acheteur,
		CarteBancaire carteClient,
		Date date,
		Double montant,
		ArrayList<ProduitPharmaceutique> produits
	) {
		this.setVendeur(vendeur);
		this.setAcheteur(acheteur);
		this.setCarteClient(carteClient);
		this.setDate(date);
		this.setMontant(montant);
		this.setProduits(produits);
	}
	
	/**
	 * Check if the transaction contains the type
	 * @param type
	 */
	public boolean contains(TypeProduitPharmaceutique type) {
		
		// For each products
		for (ProduitPharmaceutique produitPharmaceutique : produits) {
			
			// If is a royalties
			if (produitPharmaceutique.getType() == type) {
				return true;
			}
		}
		
		return false;		
	}

	public ArrayList<ProduitPharmaceutique> getProduits() {
		return produits;
	}

	public void setProduits(ArrayList<ProduitPharmaceutique> produits) {
		this.produits = produits;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CarteBancaire getCarteClient() {
		return carteClient;
	}

	public void setCarteClient(CarteBancaire carteClient) {
		this.carteClient = carteClient;
	}

	public Client getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Client acheteur) {
		this.acheteur = acheteur;
	}

	public Pharmacie getVendeur() {
		return vendeur;
	}

	public void setVendeur(Pharmacie vendeur) {
		this.vendeur = vendeur;
	}

	public boolean isDébité() {
		return débité;
	}

	public void setDébité(boolean débité) {
		this.débité = débité;
	}
  
  	public Object clone() {
  		
    	Object o = null;
    	
    	try {
      		o = super.clone();
    	} catch(CloneNotSupportedException cnse) {
      		cnse.printStackTrace(System.err);
	    }
    	
	    return o;
  	}
}
