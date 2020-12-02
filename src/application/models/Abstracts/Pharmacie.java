package application.models.Abstracts;

import java.util.ArrayList;

import application.models.CompteClassique;
import application.models.Employé;
import application.models.Pays;
import application.models.PharmacienDiplômé;
import application.models.ProduitPharmaceutique;
import application.models.Interfaces.CalculPrixVente;

public abstract class Pharmacie extends Client {
	
	private String nom;
	private String siret;
	private Pays pays;
	private int surfaceCommerciale;
	private PharmacienDiplômé responsable;
	private CompteClassique compteClassique;
	private ArrayList<Employé> employés;
	private CalculPrixVente calculPrixVente;
	private Stock stock;
	
	public Pharmacie(String nom, int surfaceCommerciale, String siret, Pays pays) {
		this.nom = nom;
		this.surfaceCommerciale = surfaceCommerciale;
		this.siret = siret;
		this.pays = pays;
	}
	
	public abstract String getType();
	
	public int getNbrEmployés() {
		return employés.size();
	}
	
	public boolean payerSalaire(Employé employé) {
		// TODO: Implement It
		return false;
	}
	

	public Double calculChiffreAffaire() {
		// TODO: Implement It
		return null;
	}
	
	public boolean vendre(ArrayList<ProduitPharmaceutique> produits, Client client) {
		// TODO: Use the default selling
		return false;		
	}
	
	public boolean vendre(ArrayList<ProduitPharmaceutique> produits, Client client, CalculPrixVente methode) {
		// TODO: Use the default selling
		return false;		
	}
		
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public CompteClassique getCompteClassique() {
		return compteClassique;
	}
	
	public void setCompteClassique(CompteClassique compteClassique) {
		this.compteClassique = compteClassique;
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
	
	public Stock getStock() {
		return stock;
	}
	
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
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
}
