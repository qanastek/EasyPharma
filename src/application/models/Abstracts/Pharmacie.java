package application.models.Abstracts;

import java.util.ArrayList;
import java.util.Date;

import application.models.CompteClassique;
import application.models.DBTransaction;
import application.models.Employé;
import application.models.Pays;
import application.models.PharmacieFranchisée;
import application.models.PharmacienDiplômé;
import application.models.ProduitPharmaceutique;
import application.models.Stock;
import application.models.Transaction;
import application.models.Enums.TypeProduitPharmaceutique;
import application.models.Interfaces.CalculPrixVente;
import application.models.Patterns.CommandTransaction.CommandTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Pharmacie extends Client {
	
	private String nom;
	private String siret;
	private Pays pays;
	private int surfaceCommerciale;
	protected PharmacienDiplômé responsable;
	private ArrayList<Employé> employés = new ArrayList<Employé>();
	private CalculPrixVente calculPrixVente;
//	private Stock stock;
	private ObservableList<ProduitPharmaceutique> stock = FXCollections.observableArrayList();
	
	public Pharmacie(String nom, int surfaceCommerciale, String siret, Pays pays) {
		this.nom = nom;
		this.surfaceCommerciale = surfaceCommerciale;
		this.siret = siret;
		this.pays = pays;
	}
	
	/**
	 * Payer les salaires des employés
	 */
	public void payerSalaires() {
		
		// Pour chaque employé
		for (Employé employé : employés) {
			
			// Payer son salaire
			this.payerSalaire(employé);			
		}
	}
	
	/**
	 * Payer le salaire de l'employé
	 * @param employé
	 * @return
	 */
	public boolean payerSalaire(Employé employé) {
		
		// Liste les produits
		ArrayList<ProduitPharmaceutique> produits = new ArrayList<ProduitPharmaceutique>();
		
		// Construire le produit salaire
		ProduitPharmaceutique p = new ProduitPharmaceutique(
			"Salaire",
			TypeProduitPharmaceutique.Salaire,
			employé.calculSalaire(this, new Date()),
			0.0,
			new Date()
		);
		
		// Ajouter le salaire aux produits
		produits.add(p);
		
		// Payer l'employé
		this.getCompteBancaire().paiement(produits, p.getPrixVente(), employé, this, 0);
		
		return false;
	}
	
	/**
	 * Compute the revenue without the royalties
	 * @param date The month date
	 * @return revenue
	 */
	public Double getChiffreAffaireBeforeRoyalties(Date date) {

		// Revenue
		double ca = 0.0;
		
		// For each transaction
		for (Transaction t: DBTransaction.getInstance().getWithoutRoyalties(this, date)) {
			
			// Increment
			ca += t.getMontant();
		}
		
		// Return revenue
		return ca;
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
			
			montantPanier += p.getPrixVente();
		}
		
		// We sale the products at the base price
		return getCompteBancaire().paiement(produits, montantPanier, client, this, carteClient);
	}

	// Return the pharmacy type name
	public abstract String getType();	
	
	public int getNbrEmployés() {
		return employés.size();
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getSurfaceCommerciale() {
		return surfaceCommerciale;
	}
	
	public void setSurfaceCommerciale(int surfaceCommerciale) {
		this.surfaceCommerciale = surfaceCommerciale;
	}
	
	public ArrayList<Employé> getEmployés() {
		return employés;
	}
	
	public void setEmployés(ArrayList<Employé> employés) {
		this.employés = employés;
	}
	
	public PharmacienDiplômé getResponsable() {
		return responsable;
	}
	
	public void setResponsable(PharmacienDiplômé responsable) {
		this.responsable = responsable;
	}
	
	public String getSiret() {
		return siret;
	}
	
	public void setSiret(String siret) {
		this.siret = siret;
	}

	public ObservableList<ProduitPharmaceutique> getStock() {
		return stock;
	}
	
	public void setStock(ObservableList<ProduitPharmaceutique> stock) {
		this.stock = stock;
	}
	
//	public Stock getStock() {
//		return stock;
//	}
//	
//	public void setStock(Stock stock) {
//		this.stock = stock;
//	}
	
	public Pays getPays() {
		return pays;
	}
	
	public void setPays(Pays pays) {
		this.pays = pays;
	}
	
	public CalculPrixVente getCalculPrixVente() {
		return calculPrixVente;
	}
	
	public void setCalculPrixVente(CalculPrixVente calculPrixVente) {
		this.calculPrixVente = calculPrixVente;
	}
	
	public void addEmployé(Employé e) {
		this.employés.add(e);
	}
	
	public void removeEmployé(Employé e) {
		this.employés.remove(e);
	}

	public void addProduit(ProduitPharmaceutique p) {
		this.stock.add(p);
	}
	
	public void removeProduit(ProduitPharmaceutique p) {
		this.stock.remove(p);
	}
	
	@Override
	public String toString() {
		return this.getNom();
	}
}
