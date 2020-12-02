package application.models.Abstracts;

import java.util.ArrayList;

import application.models.ProduitPharmaceutique;

public abstract class CompteBancaire {
	
	private Double solde;
	private ArrayList<CarteBancaire> cartes;
	
	public CompteBancaire() {
		solde = 0.0;
	}
	
	public CompteBancaire(Double solde) {
		this.solde = solde;
	}
	
	public boolean vérificationSolvabilité(Double montant) {
		return montant < solde;
	}
	
	public abstract boolean payer(ProduitPharmaceutique produit);

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
