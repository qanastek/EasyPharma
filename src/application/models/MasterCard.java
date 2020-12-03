package application.models;

import application.models.Abstracts.R�seau;

public class MasterCard extends R�seau {
	
	public MasterCard() {
		this.coutR�seau = 0.5;
	}

	/**
	 * Montant rembourser au client
	 */
	public double calculRemboursement(Transaction t) {
		return t.getMontant() * (1 - 0.5);
	}
	
	@Override
	public String toString() {
		return "MasterCard";
	}
}
