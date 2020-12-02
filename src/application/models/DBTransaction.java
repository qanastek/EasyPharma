package application.models;

import java.util.ArrayList;
import java.util.Date;

import application.models.Abstracts.Pharmacie;

public class DBTransaction {
	
	private ArrayList<Transaction> transactions;

	/**
	 * Return all the transactions
	 * @param pharmacie
	 * @param date
	 * @return The transaction
	 */
	public ArrayList<Transaction> getAll(Pharmacie pharmacie, Date date) {
		return transactions;	
	}
	
	/**
	 * Return all the transactions except the royalties
	 * @param pharmacie
	 * @param date
	 * @return The transaction
	 */
	public ArrayList<Transaction> getWithoutRoyalties(Pharmacie pharmacie, Date date) {
		return transactions;
	}

}
