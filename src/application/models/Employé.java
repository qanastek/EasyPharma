package application.models;

import application.models.Interfaces.M�tier;

public class Employ� extends Personne {

	// M�tier
	private M�tier m�tier;
	
	/**
	 * Constructeur
	 * @param nom
	 * @param pr�nom
	 * @param adresse
	 * @param m�tier
	 */
	public Employ�(String nom, String pr�nom, String adresse, M�tier m�tier) {
		super(nom, pr�nom, adresse);
		
		this.m�tier = m�tier;
	}

	public M�tier getM�tier() {
		return m�tier;
	}

	public void setM�tier(M�tier m�tier) {
		this.m�tier = m�tier;
	}
	
	@Override
	public String toString() {
		return getNom() + " " + getPr�nom();
	}
}
