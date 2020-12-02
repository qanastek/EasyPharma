package application.models.Abstracts;

import java.util.ArrayList;

import application.models.CompteClassique;
import application.models.Employ�;
import application.models.Pays;
import application.models.PharmacienDipl�m�;
import application.models.ProduitPharmaceutique;
import application.models.Interfaces.CalculPrixVente;

public abstract class Pharmacie extends Client {
	
	private String nom;
	private String siret;
	private Pays pays;
	private int surfaceCommerciale;
	private PharmacienDipl�m� responsable;
	private CompteClassique compteClassique;
	private ArrayList<Employ�> employ�s;
	private CalculPrixVente calculPrixVente;
	private Stock stock;
	
	public Pharmacie(String nom, int surfaceCommerciale, String siret, Pays pays) {
		this.nom = nom;
		this.surfaceCommerciale = surfaceCommerciale;
		this.siret = siret;
		this.pays = pays;
	}
	
	public abstract String getType();
	
	public int getNbrEmploy�s() {
		return employ�s.size();
	}
	
	public boolean payerSalaire(Employ� employ�) {
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
