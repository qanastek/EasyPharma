package application.models.Abstracts;

import application.models.Transaction;

public abstract class Réseau {

	/**
	 * Cout d'un achat avec le réseau
	 */
	protected double coutRéseau;

	/**
	 * Montant rembourser au client
	 */
	abstract public double calculRemboursement(Transaction t);
	
	@Override
	public abstract String toString();
	
	public double getCoutRéseau() {
		return this.coutRéseau;
	}
}
