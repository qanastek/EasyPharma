package application.models;

import java.util.ArrayList;
import java.util.Date;

import application.models.Abstracts.Pharmacie;

public class PharmacieFranchis�e extends Pharmacie {

	private PharmacieFranchis�e parent;
	private ArrayList<PharmacieFranchis�e> franchises;
	private CompteFranchis� compteFranchis�;

	public PharmacieFranchis�e(String nom, int surfaceCommerciale, String siret, Pays pays) {
		super(nom,surfaceCommerciale,siret,pays);
	}
	
	public PharmacieFranchis�e(String nom, int surfaceCommerciale, String siret, Pays pays, PharmacieFranchis�e parent) {
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
	
	public ArrayList<PharmacieFranchis�e> getAllPharmaciesFranchis�es() {
		// TODO: Implement It
		return null;
	}
	
	public void acheterProduit(ArrayList<ProduitPharmaceutique> produit) {
		// TODO: Implement It
	}
	
	public PharmacieFranchis�e getParent() {
		return parent;
	}
	
	public void setParent(PharmacieFranchis�e parent) {
		this.parent = parent;
	}
	
	public ArrayList<PharmacieFranchis�e> getFranchises() {
		return franchises;
	}
	
	public void setFranchises(ArrayList<PharmacieFranchis�e> franchises) {
		this.franchises = franchises;
	}

	public CompteFranchis� getCompteFranchis�() {
		return compteFranchis�;
	}
	
	public void setCompteFranchis�(CompteFranchis� compteFranchis�) {
		this.compteFranchis� = compteFranchis�;
	}

	@Override
	public String getType() {
		return "Franchis�e";
	}	
}
