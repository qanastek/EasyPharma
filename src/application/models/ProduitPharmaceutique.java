package application.models;

import java.util.Date;

public class ProduitPharmaceutique {
	
	private String nom;
	private ProduitPharmaceutique type;
	private Double prixAchat;
	private Date datePeremption;
	
	public Date getDatePeremption() {
		return datePeremption;
	}
	
	public boolean estPerimer() {
		return new Date().after(datePeremption);
	}
	
	public void setDatePeremption(Date datePeremption) {
		this.datePeremption = datePeremption;
	}
	
	public Double getPrixAchat() {
		return prixAchat;
	}
	
	public void setPrixAchat(Double prixAchat) {
		this.prixAchat = prixAchat;
	}
	
	public ProduitPharmaceutique getType() {
		return type;
	}
	
	public void setType(ProduitPharmaceutique type) {
		this.type = type;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

}
