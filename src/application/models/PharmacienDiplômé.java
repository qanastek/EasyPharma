package application.models;

import application.models.Abstracts.Pharmacie;
import application.models.Interfaces.Métier;

public class PharmacienDiplômé implements Métier {
	
	private Double salaire;
	
	public PharmacienDiplômé(Double salaire) {
		this.setSalaire(salaire);
	}
	
	private Double calculePrime(Pharmacie pharmacie) {
		// TODO: Implement It
		return null;
	}

	public Double calculSalaire(Pharmacie pharmacie) {
		return salaire + calculePrime(pharmacie);
	}

	public Double getSalaire() {
		return salaire;
	}

	public void setSalaire(Double salaire) {
		this.salaire = salaire;
	}

}
