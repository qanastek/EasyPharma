package application.models.Patterns.AbstractFactoryPharmacie;

import application.models.Pays;
import application.models.PharmacieFranchis�e;
import application.models.PharmacieInd�pendante;

public interface IPharmacieFactory {	
	public PharmacieInd�pendante getPharmacieInd�pendante(String nom, int surfaceCommerciale, String siret, Pays pays);
	
	public PharmacieFranchis�e getPharmacieFranchis�e(String nom, int surfaceCommerciale, String siret, Pays pays);
	public PharmacieFranchis�e getPharmacieFranchis�e(String nom, int surfaceCommerciale, String siret, Pays pays, PharmacieFranchis�e parent);
}
