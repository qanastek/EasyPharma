package application.models;

import application.models.Abstracts.Pharmacie;

public class PharmacieIndépendante extends Pharmacie {
	
	public PharmacieIndépendante(String nom, int surfaceCommerciale, String siret, Pays pays) {
		super(nom,surfaceCommerciale,siret,pays);
	}

	@Override
	public String getType() {
		return "Indépendante";
	}
}
