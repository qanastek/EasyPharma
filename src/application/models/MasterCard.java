package application.models;

import application.models.Abstracts.Réseau;

public class MasterCard extends Réseau {
	
	public MasterCard() {
		this.coutRéseau = 0.5;
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
