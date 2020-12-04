package application.models;

import java.util.Date;

import application.models.Abstracts.Pharmacie;

public class PharmacienDipl�m� extends Employ� {
	
	private Double salaire;
	
	public PharmacienDipl�m�(String nom, String pr�nom, String adresse, Date dateEmbauch�, Double salaire) {
		super(nom, pr�nom, adresse, dateEmbauch�);
		this.setSalaire(salaire);
	}

	/**
	 * Calcul le montant apr�s prime
	 * @return
	 */
	protected Double salaireApr�sPrime(Pharmacie pharmacie, Double salaire, Date date) {
		
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
		return salaireApr�sPrime(pharmacie, this.salaire, date);
	}

	public Double getSalaire() {
		return salaire;
	}

	public void setSalaire(Double salaire) {
		this.salaire = salaire;
	}

	@Override
	public String getM�tier() {
		return "Pharmacien Dipl�m�";
	}
}
