package application.models;

import application.models.Abstracts.Pharmacie;
import application.models.Interfaces.M�tier;

public class Pr�parateurCommande implements M�tier {
	
	private Double quotit�Horraires;
	private Double coutHeure;
	private Double anciennet�;
	
	public Pr�parateurCommande(Double quotit�Horraires,	Double coutHeure) {
		this.setQuotit�Horraires(quotit�Horraires);
		this.setCoutHeure(coutHeure);
	}

	public Double calculSalaire(Pharmacie pharmacie) {
		return quotit�Horraires * coutHeure;
	}

	public Double getAnciennet�() {
		return anciennet�;
	}

	public void setAnciennet�(Double anciennet�) {
		this.anciennet� = anciennet�;
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

}
