package application.models;

import application.models.Abstracts.R�seau;

public class Visa extends R�seau {
	
	public Visa() {
		this.coutR�seau = 0.25;
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
