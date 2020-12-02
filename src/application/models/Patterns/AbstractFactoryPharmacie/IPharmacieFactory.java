package application.models.Patterns.AbstractFactoryPharmacie;

import application.models.Pays;
import application.models.PharmacieFranchisée;
import application.models.PharmacieIndépendante;

public interface IPharmacieFactory {	
	public PharmacieIndépendante getPharmacieIndépendante(String nom, int surfaceCommerciale, String siret, Pays pays);
	
	public PharmacieFranchisée getPharmacieFranchisée(String nom, int surfaceCommerciale, String siret, Pays pays);
	public PharmacieFranchisée getPharmacieFranchisée(String nom, int surfaceCommerciale, String siret, Pays pays, PharmacieFranchisée parent);
}
