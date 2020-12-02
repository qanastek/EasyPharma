package application.models;

import application.models.Abstracts.Client;

public class Personne extends Client {
	
	private String nom;
	private String pr�nom;
	private String adresse;

	public Personne(String nom, String pr�nom, String adresse) {
		this.setNom(nom);
		this.setPr�nom(pr�nom);
		this.setAdresse(adresse);
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPr�nom() {
		return pr�nom;
	}

	public void setPr�nom(String pr�nom) {
		this.pr�nom = pr�nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
