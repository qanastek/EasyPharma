package application.models;

import java.util.ArrayList;

import application.models.Abstracts.Subject;

public class Stock extends Subject {
	
	// Products list
	private ArrayList<ProduitPharmaceutique> produits;
	
	public void addProduct(ProduitPharmaceutique p) {
		produits.add(p);
	}
	
	public ProduitPharmaceutique get(int index) {
		return produits.remove(index);
	}

	public ArrayList<ProduitPharmaceutique> getProduits() {
		return produits;
	}

	public void setProduits(ArrayList<ProduitPharmaceutique> produits) {
		this.produits = produits;
	}
}
