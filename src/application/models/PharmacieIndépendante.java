package application.models;

import application.models.Abstracts.Pharmacie;

public class PharmacieInd�pendante extends Pharmacie {
	
	public PharmacieInd�pendante(String nom, int surfaceCommerciale, String siret, Pays pays) {
		super(nom,surfaceCommerciale,siret,pays);
	}

	@Override
	public String getType() {
		return "Ind�pendante";
	}
}
