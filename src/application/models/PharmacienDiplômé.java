package application.models;

import application.models.Abstracts.Pharmacie;
import application.models.Interfaces.M�tier;

public class PharmacienDipl�m� implements M�tier {
	
	private Double salaire;
	
	public PharmacienDipl�m�(Double salaire) {
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
