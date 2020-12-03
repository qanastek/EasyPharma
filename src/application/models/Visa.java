package application.models;

import application.models.Abstracts.Réseau;

public class Visa extends Réseau {
	
	public Visa() {
		this.coutRéseau = 0.25;
	}

	/**
	 * Montant rembourser au client
	 */
	public double calculRemboursement(Transaction t) {
		return t.getMontant() * (1 - t.getVendeur().getPays().getCoutVisa());
	}
	
	@Override
	public String toString() {
		return "Visa";
	}
}
