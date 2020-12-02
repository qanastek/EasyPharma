package application.models;

import java.util.Random;

public class Pays {
	
	private String nom;
	private int taillePopulation;
	private Double pib;
	private Double coutVisa;

	public Pays(String nom, Double coutVisa) {
		
		Random rand = new Random();
		
		this.nom = nom;
		this.taillePopulation = rand.nextInt(500000000);
		this.pib = ((double) rand.nextInt(Integer.MIN_VALUE));
		this.coutVisa = coutVisa;
	}
	
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

	@Override
	public String toString() {
		return getNom();
	}

	public Double getCoutVisa() {
		return coutVisa;
	}

	public void setCoutVisa(Double coutVisa) {
		this.coutVisa = coutVisa;
	}
}
