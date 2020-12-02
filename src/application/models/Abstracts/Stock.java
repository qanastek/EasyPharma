package application.models.Abstracts;

import java.util.HashMap;

import application.models.ProduitPharmaceutique;

public abstract class Stock {
		
	private HashMap<ProduitPharmaceutique,Integer> produits;

	public HashMap<ProduitPharmaceutique,Integer> getProduits() {
		return produits;
	}

	public void setProduits(HashMap<ProduitPharmaceutique,Integer> produits) {
		this.produits = produits;
	}

}
