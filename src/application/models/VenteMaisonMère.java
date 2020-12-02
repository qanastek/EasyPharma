package application.models;

import application.models.Abstracts.Client;
import application.models.Abstracts.Pharmacie;
import application.models.Interfaces.CalculPrixVente;

public class VenteMaisonMère implements CalculPrixVente {

	/**
	 * 10 percent reduction for the parent company
	 * @param produit
	 * @param source La pharmacie qui vend
	 * @param destination La pharmacie qui achete
	 * @return
	 */
	public double calculPrixVente(Double montant, Pharmacie seller, Client buyer) {
		return montant * (1 - buyer.getReduction(seller));
	}

}
