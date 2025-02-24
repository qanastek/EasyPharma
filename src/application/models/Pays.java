package application.models;

import java.util.Random;

import application.models.Utils.Toolbox;

public class Pays {
	
	private String nom;
	private int taillePopulation;
	private Double pib;
	private Double coutVisa;

	/**
	 * Constructeur
	 * @param nom
	 * @param coutVisa
	 */
	public Pays(String nom, Double coutVisa) {
		
		this.nom = nom;
		this.taillePopulation = Toolbox.generateInt();
		this.pib = ((double) Toolbox.generateInt());
		this.coutVisa = coutVisa;
	}
	
	/**
	 * Constructeur
	 * @param nom
	 * @param taillePopulation
	 * @param pib
	 * @param coutVisa
	 */
	public Pays(String nom, int taillePopulation, Double pib, Double coutVisa) {
		this.nom = nom;
		this.taillePopulation = taillePopulation;
		this.pib = pib;
		this.coutVisa = coutVisa;
	}
	
	public int getTaillePopulation() {
		return taillePopulation;
	}
	
	public void setTaillePopulation(int taillePopulation) {
		this.taillePopulation = taillePopulation;
	}
	
	public Double getPib() {
		return pib;
	}
	
	public void setPib(Double pib) {
		this.pib = pib;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getCoutVisa() {
		return coutVisa;
	}

	public void setCoutVisa(Double coutVisa) {
		this.coutVisa = coutVisa;
	}

	@Override
	public String toString() {
		return getNom();
	}
}
