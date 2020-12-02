package application.models;

import java.util.Date;

import application.models.Enums.TypeProduitPharmaceutique;

public class ProduitPharmaceutique {
	
	private String nom;
	private TypeProduitPharmaceutique type;
	private Double prixAchat;
	private Double marge;
	private Date datePeremption;
	
	public ProduitPharmaceutique(String nom, TypeProduitPharmaceutique type, Double prixAchat, Double marge, Date datePeremption) {
		this.nom = nom;
		this.type = type;
		this.prixAchat = prixAchat;
		this.marge = marge;
		this.datePeremption = datePeremption;
	}
	
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
	
	public Double getPrixVente() {
		return getPrixAchat() * getMarge();
	}
	
	public void setPrixAchat(Double prixAchat) {
		this.prixAchat = prixAchat;
	}
	
	public TypeProduitPharmaceutique getType() {
		return type;
	}
	
	public void setType(TypeProduitPharmaceutique type) {
		this.type = type;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getMarge() {
		return marge;
	}

	public void setMarge(Double marge) {
		this.marge = marge;
	}
	
}
