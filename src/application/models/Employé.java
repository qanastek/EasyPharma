package application.models;

import application.models.Interfaces.Métier;

public class Employé extends Personne {

	// Métier
	private Métier métier;
	
	/**
	 * Constructeur
	 * @param nom
	 * @param prénom
	 * @param adresse
	 * @param métier
	 */
	public Employé(String nom, String prénom, String adresse, Métier métier) {
		super(nom, prénom, adresse);
		
		this.métier = métier;
	}

	public Métier getMétier() {
		return métier;
	}

	public void setMétier(Métier métier) {
		this.métier = métier;
	}
	
	@Override
	public String toString() {
		return getNom() + " " + getPrénom();
	}
}
