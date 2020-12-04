package application.models;

import java.util.Date;

import application.models.Abstracts.Pharmacie;

public class PharmacienDiplômé extends Employé {
	
	private Double salaire;
	
	public PharmacienDiplômé(String nom, String prénom, String adresse, Date dateEmbauché, Double salaire) {
		super(nom, prénom, adresse, dateEmbauché);
		this.setSalaire(salaire);
	}

	/**
	 * Calcul le montant après prime
	 * @return
	 */
	protected Double salaireAprèsPrime(Pharmacie pharmacie, Double salaire, Date date) {
		
		// Retourne le pourcentage de prime
		return salaire + (0.01 * pharmacie.getChiffreAffaireBeforeRoyalties(date));
	}

	/**
	 * Calcul le salaire final
	 * @param pharmacie
	 * @return
	 */
	public Double calculSalaire(Pharmacie pharmacie, Date date) {
		
		// Salaire final
		return salaireAprèsPrime(pharmacie, this.salaire, date);
	}

	public Double getSalaire() {
		return salaire;
	}

	public void setSalaire(Double salaire) {
		this.salaire = salaire;
	}

	@Override
	public String getMétier() {
		return "Pharmacien Diplômé";
	}
}
