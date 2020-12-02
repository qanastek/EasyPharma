package application.models.Patterns.AbstractFactoryPharmacie;

import application.models.Pays;
import application.models.PharmacieFranchisée;
import application.models.PharmacieIndépendante;

public class PharmacieFactory implements IPharmacieFactory {

	@Override
	public PharmacieIndépendante getPharmacieIndépendante(String nom, int surfaceCommerciale, String siret, Pays pays) {
		return new PharmacieIndépendante(nom, surfaceCommerciale, siret, pays);
	}

	@Override
	public PharmacieFranchisée getPharmacieFranchisée(String nom, int surfaceCommerciale, String siret, Pays pays) {
		return new PharmacieFranchisée(nom, surfaceCommerciale, siret, pays);
	}

	@Override
	public PharmacieFranchisée getPharmacieFranchisée(String nom, int surfaceCommerciale, String siret, Pays pays, PharmacieFranchisée parent) {
		return new PharmacieFranchisée(nom, surfaceCommerciale, siret, pays, parent);
	}

}
