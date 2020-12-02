package application.models;

import application.models.Abstracts.Client;

public class Personne extends Client {
	
	private String nom;
	private String prénom;
	private String adresse;

	public Personne(String nom, String prénom, String adresse) {
		this.setNom(nom);
		this.setPrénom(prénom);
		this.setAdresse(adresse);
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPrénom() {
		return prénom;
	}

	public void setPrénom(String prénom) {
		this.prénom = prénom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
