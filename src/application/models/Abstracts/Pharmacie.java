package application.models.Abstracts;

import java.util.ArrayList;
import java.util.Date;

import application.models.CompteClassique;
import application.models.DBTransaction;
import application.models.Employ�;
import application.models.Pays;
import application.models.PharmacieFranchis�e;
import application.models.PharmacienDipl�m�;
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
	protected PharmacienDipl�m� responsable;
	private ArrayList<Employ�> employ�s = new ArrayList<Employ�>();
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
	 * Payer les salaires des employ�s
	 */
	public void payerSalaires() {
		
		// Pour chaque employ�
		for (Employ� employ� : employ�s) {
			
			// Payer son salaire
			this.payerSalaire(employ�);			
		}
	}
	
	/**
	 * Payer le salaire de l'employ�
	 * @param employ�
	 * @return
	 */
	public boolean payerSalaire(Employ� employ�) {
		
		// Liste les produits
		ArrayList<ProduitPharmaceutique> produits = new ArrayList<ProduitPharmaceutique>();
		
		// Construire le produit salaire
		ProduitPharmaceutique p = new ProduitPharmaceutique(
			"Salaire",
			TypeProduitPharmaceutique.Salaire,
			employ�.calculSalaire(this, new Date()),
			0.0,
			new Date()
		);
		
		// Ajouter le salaire aux produits
		produits.add(p);
		
		// Payer l'employ�
		this.getCompteBancaire().paiement(produits, p.getPrixVente(), employ�, this, 0);
		
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
	
	public int getNbrEmploy�s() {
		return employ�s.size();
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
	
	public ArrayList<Employ�> getEmploy�s() {
		return employ�s;
	}
	
	public void setEmploy�s(ArrayList<Employ�> employ�s) {
		this.employ�s = employ�s;
	}
	
	public PharmacienDipl�m� getResponsable() {
		return responsable;
	}
	
	public void setResponsable(PharmacienDipl�m� responsable) {
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
	
	public void addEmploy�(Employ� e) {
		this.employ�s.add(e);
	}
	
	public void removeEmploy�(Employ� e) {
		this.employ�s.remove(e);
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
