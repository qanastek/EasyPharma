package application.models;

import java.util.Date;

import application.models.Abstracts.Pharmacie;

public class Pr�parateurCommande extends Employ� {
	
	private Double quotit�Horraires;
	private Double coutHeure;

	public Pr�parateurCommande(
		String nom,
		String pr�nom,
		String adresse,
		Date dateEmbauch�,
		Double salaire,
		Double quotit�Horraires,
		Double coutHeure
	) {
		super(nom, pr�nom, adresse, dateEmbauch�);

		this.setQuotit�Horraires(quotit�Horraires);
		this.setCoutHeure(coutHeure);
	}
	
	/**
	 * Calcul le montant apr�s prime
	 * @return
	 */
	protected Double salaireApr�sPrime(Pharmacie pharmacie, Double salaire, Date date) {
		
		// Pourcentage de prime
		double prime = 0.0;
		
		// Calcul l'anci�nnet�
		int anciennet� = getAnciennet�();

		if (anciennet� < 3) {
			prime = 0.0;
		}
		else if (3 <= anciennet� && anciennet� <= 6) {
			prime = 0.10;
		}
		else {
			prime = 0.15;
		}

		// Retourne le salaire apr�s la prime
		return salaire + (1 + prime);
	}

	/**
	 * Calcul le salaire final
	 * @param pharmacie
	 * @return
	 */
	public Double calculSalaire(Pharmacie pharmacie, Date date) {
		
		// Salaire
		double salaire = quotit�Horraires * coutHeure;
		
		// Salaire + Prime
		return salaireApr�sPrime(pharmacie, salaire, date);
	}

	public Double getCoutHeure() {
		return coutHeure;
	}

	public void setCoutHeure(Double coutHeure) {
		this.coutHeure = coutHeure;
	}

	public Double getQuotit�Horraires() {
		return quotit�Horraires;
	}

	public void setQuotit�Horraires(Double quotit�Horraires) {
		this.quotit�Horraires = quotit�Horraires;
	}

	@Override
	public String getM�tier() {
		return "Pr�parateur de commandes";
	}
}
