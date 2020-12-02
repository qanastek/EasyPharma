package application.models.Interfaces;

import application.models.ProduitPharmaceutique;
import application.models.Abstracts.Client;
import application.models.Abstracts.Pharmacie;

public interface CalculPrixVente {
	
	public abstract double calculPrixVente(Double montant, Pharmacie seller, Client buyer);

}
