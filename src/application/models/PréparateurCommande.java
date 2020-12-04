package application.models;

import java.util.Date;

import application.models.Abstracts.Pharmacie;

public class PréparateurCommande extends Employé {
	
	private Double quotitéHorraires;
	private Double coutHeure;

	public PréparateurCommande(
		String nom,
		String prénom,
		String adresse,
		Date dateEmbauché,
		Double salaire,
		Double quotitéHorraires,
		Double coutHeure
	) {
		super(nom, prénom, adresse, dateEmbauché);

		this.setQuotitéHorraires(quotitéHorraires);
		this.setCoutHeure(coutHeure);
	}
	
	/**
	 * Calcul le montant après prime
	 * @return
	 */
	protected Double salaireAprèsPrime(Pharmacie pharmacie, Double salaire, Date date) {
		
		// Pourcentage de prime
		double prime = 0.0;
		
		// Calcul l'anciènneté
		int ancienneté = getAncienneté();

		if (ancienneté < 3) {
			prime = 0.0;
		}
		else if (3 <= ancienneté && ancienneté <= 6) {
			prime = 0.10;
		}
		else {
			prime = 0.15;
		}

		// Retourne le salaire après la prime
		return salaire + (1 + prime);
	}

	/**
	 * Calcul le salaire final
	 * @param pharmacie
	 * @return
	 */
	public Double calculSalaire(Pharmacie pharmacie, Date date) {
		
		// Salaire
		double salaire = quotitéHorraires * coutHeure;
		
		// Salaire + Prime
		return salaireAprèsPrime(pharmacie, salaire, date);
	}

	public Double getCoutHeure() {
		return coutHeure;
	}

	public void setCoutHeure(Double coutHeure) {
		this.coutHeure = coutHeure;
	}

	public Double getQuotitéHorraires() {
		return quotitéHorraires;
	}

	public void setQuotitéHorraires(Double quotitéHorraires) {
		this.quotitéHorraires = quotitéHorraires;
	}

	@Override
	public String getMétier() {
		return "Préparateur de commandes";
	}
}
