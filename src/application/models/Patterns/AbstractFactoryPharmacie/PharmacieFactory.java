package application.models.Patterns.AbstractFactoryPharmacie;

import application.models.Pays;
import application.models.PharmacieFranchis�e;
import application.models.PharmacieInd�pendante;

public class PharmacieFactory implements IPharmacieFactory {

	@Override
	public PharmacieInd�pendante getPharmacieInd�pendante(String nom, int surfaceCommerciale, String siret, Pays pays) {
		return new PharmacieInd�pendante(nom, surfaceCommerciale, siret, pays);
	}

	@Override
	public PharmacieFranchis�e getPharmacieFranchis�e(String nom, int surfaceCommerciale, String siret, Pays pays) {
		return new PharmacieFranchis�e(nom, surfaceCommerciale, siret, pays);
	}

	@Override
	public PharmacieFranchis�e getPharmacieFranchis�e(String nom, int surfaceCommerciale, String siret, Pays pays, PharmacieFranchis�e parent) {
		return new PharmacieFranchis�e(nom, surfaceCommerciale, siret, pays, parent);
	}

}
