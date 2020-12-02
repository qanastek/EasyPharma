package application.models;

import java.util.ArrayList;
import java.util.Date;

import application.models.Abstracts.CarteBancaire;
import application.models.Abstracts.Client;
import application.models.Abstracts.Pharmacie;

public class Transaction {
	
	private Pharmacie vendeur;
	private CarteBancaire carteVendeur;
	private Client acheteur;
	private CarteBancaire carteClient;
	private Date date;
	private Double montant;
	private ArrayList<ProduitPharmaceutique> produits;
	
	public Transaction(
		Pharmacie vendeur,
		CarteBancaire carteVendeur,
		Client acheteur,
		CarteBancaire carteClient,
		Date date,
		Double montant,
		ArrayList<ProduitPharmaceutique> produits
	) {
		this.setVendeur(vendeur);
		this.setCarteVendeur(carteVendeur);
		this.setAcheteur(acheteur);
		this.setCarteClient(carteClient);
		this.setDate(date);
		this.setMontant(montant);
		this.setProduits(produits);
	}
	
	public void annuler() {
		// TODO: Implements It
	}
	
	public void rembourser() {
		// TODO: Implements It
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

	public CarteBancaire getCarteVendeur() {
		return carteVendeur;
	}

	public void setCarteVendeur(CarteBancaire carteVendeur) {
		this.carteVendeur = carteVendeur;
	}

	public Pharmacie getVendeur() {
		return vendeur;
	}

	public void setVendeur(Pharmacie vendeur) {
		this.vendeur = vendeur;
	}

}
