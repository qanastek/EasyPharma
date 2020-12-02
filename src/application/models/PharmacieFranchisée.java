package application.models;

import java.util.ArrayList;
import java.util.Date;

import application.models.Abstracts.Pharmacie;

public class PharmacieFranchisée extends Pharmacie {

	private PharmacieFranchisée parent;
	private ArrayList<PharmacieFranchisée> franchises;
	private CompteFranchisé compteFranchisé;

	public PharmacieFranchisée(String nom, int surfaceCommerciale, String siret, Pays pays) {
		super(nom,surfaceCommerciale,siret,pays);
	}
	
	public PharmacieFranchisée(String nom, int surfaceCommerciale, String siret, Pays pays, PharmacieFranchisée parent) {
		super(nom,surfaceCommerciale,siret,pays);
		
		this.parent = parent;
	}
	
	public Double getPercentage() {
		// TODO: Implement It
		return 0.0;
	}
	
	public Double calculRoyalties(Double percentage) {
		// TODO: Implement It
		return 0.0;
	}
	
	public void collectRoyalties(Double percentage) {
		// TODO: Implement It
	}
	
	public void getMontantFinMoisRoyalties() {
		// TODO: Implement It
	}
	
	public void getMontantPayerRoyalties() {
		// TODO: Implement It
	}
	
	public Double calculChiffreAffaire() {
		// TODO: Implement It
		return 0.0;
	}
	
	public Double getChiffreAffaireBeforeRoyalties(Date date) {
		// TODO: Implement It
		return 0.0;
	}
	
	public ArrayList<PharmacieFranchisée> getAllPharmaciesFranchisées() {
		// TODO: Implement It
		return null;
	}
	
	public void acheterProduit(ArrayList<ProduitPharmaceutique> produit) {
		// TODO: Implement It
	}
	
	public PharmacieFranchisée getParent() {
		return parent;
	}
	
	public void setParent(PharmacieFranchisée parent) {
		this.parent = parent;
	}
	
	public ArrayList<PharmacieFranchisée> getFranchises() {
		return franchises;
	}
	
	public void setFranchises(ArrayList<PharmacieFranchisée> franchises) {
		this.franchises = franchises;
	}

	public CompteFranchisé getCompteFranchisé() {
		return compteFranchisé;
	}
	
	public void setCompteFranchisé(CompteFranchisé compteFranchisé) {
		this.compteFranchisé = compteFranchisé;
	}

	@Override
	public String getType() {
		return "Franchisée";
	}	
}
