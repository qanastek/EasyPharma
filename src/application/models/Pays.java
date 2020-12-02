package application.models;

public class Pays {
	
	private String nom;
	private int taillePopulation;
	private Double pib;
	
	public Pays(String nom, int taillePopulation, Double pib) {
		this.nom = nom;
		this.taillePopulation = taillePopulation;
		this.pib = pib;
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
}
