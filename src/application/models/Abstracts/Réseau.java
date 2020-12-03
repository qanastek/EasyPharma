package application.models.Abstracts;

import application.models.Transaction;

public abstract class R�seau {

	/**
	 * Cout d'un achat avec le r�seau
	 */
	protected double coutR�seau;

	/**
	 * Montant rembourser au client
	 */
	abstract public double calculRemboursement(Transaction t);
	
	@Override
	public abstract String toString();
	
	public double getCoutR�seau() {
		return this.coutR�seau;
	}
}
