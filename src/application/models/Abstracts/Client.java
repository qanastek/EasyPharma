package application.models.Abstracts;

import java.util.ArrayList;

import application.models.ProduitPharmaceutique;

public abstract class Client {
	
	private CompteBancaire compteBancaire;

	public void acheterProduit(ArrayList<ProduitPharmaceutique> produits, Pharmacie pharmacie) {
		// TODO: Implement It
	}

	public CompteBancaire getCompteBancaire() {
		return compteBancaire;
	}

	public void setCompteBancaire(CompteBancaire compteBancaire) {
		this.compteBancaire = compteBancaire;
	}	

}
