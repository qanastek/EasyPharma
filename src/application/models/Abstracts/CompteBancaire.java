package application.models.Abstracts;

import java.util.ArrayList;

import application.models.ProduitPharmaceutique;
import application.models.Patterns.CommandTransaction.CommandTransaction;

public abstract class CompteBancaire {
	
	// Solde
	private Double solde;
	
	// Cartes
	private ArrayList<CarteBancaire> cartes = new ArrayList<CarteBancaire>();
	
	public CompteBancaire() {
		solde = 0.0;
	}
	
	public CompteBancaire(Double solde) {
		this.solde = solde;
	}

	/**
	 * D�bite le compte d'une somme
	 * @param montant
	 */
	public void d�biter(double montant) {
		solde -= montant;
	}
	
	/**
	 * Cr�dite le compte d'une somme
	 * @param montant
	 */
	public void cr�diter(double montant) {
		solde += montant;
	}
		
	public boolean v�rificationSolvabilit�(Double montant) {
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
	
	public void removeCarte(CarteBancaire carte) {
		this.cartes.remove(carte);
	}
	
	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}
}
