package application.models.Abstracts;

import java.util.ArrayList;

import application.models.ProduitPharmaceutique;
import application.models.Patterns.CommandTransaction.CommandTransaction;

public abstract class CompteBancaire {
	
	private Double solde;
	private ArrayList<CarteBancaire> cartes;
	
	public CompteBancaire() {
		solde = 0.0;
	}
	
	public CompteBancaire(Double solde) {
		this.solde = solde;
	}

	/**
	 * Débite le compte d'une somme
	 * @param montant
	 */
	public void débiter(double montant) {
		solde -= montant;
	}
	
	/**
	 * Crédite le compte d'une somme
	 * @param montant
	 */
	public void créditer(double montant) {
		solde += montant;
	}
		
	public boolean vérificationSolvabilité(Double montant) {
		return montant < solde;
	}
	
	// Buy/Sell a product to the seller/buyer
	public abstract CommandTransaction paiement(ArrayList<ProduitPharmaceutique> produits, Double montant, Client acheteur, Pharmacie vendeur, int carteClient);
	
	public ArrayList<CarteBancaire> getCartes() {
		return cartes;
	}

	public void setCartes(ArrayList<CarteBancaire> cartes) {
		this.cartes = cartes;
	}

	public void addCarte(CarteBancaire carte) {
		this.cartes.add(carte);
	}
	
	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}
}
