package application.models;

import application.models.Abstracts.Pharmacie;
import application.models.Interfaces.Métier;

public class PréparateurCommande implements Métier {
	
	private Double quotitéHorraires;
	private Double coutHeure;
	private Double ancienneté;
	
	public PréparateurCommande(Double quotitéHorraires,	Double coutHeure) {
		this.setQuotitéHorraires(quotitéHorraires);
		this.setCoutHeure(coutHeure);
	}

	public Double calculSalaire(Pharmacie pharmacie) {
		return quotitéHorraires * coutHeure;
	}

	public Double getAncienneté() {
		return ancienneté;
	}

	public void setAncienneté(Double ancienneté) {
		this.ancienneté = ancienneté;
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

}
