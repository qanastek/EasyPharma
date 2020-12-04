package application.models;

import java.util.ArrayList;
import java.util.Date;

import application.models.Abstracts.Pharmacie;
import application.models.Enums.TypeProduitPharmaceutique;

public class DBTransaction {
	
	private static DBTransaction INSTANCE = null;
	
	/**
	 * Store the transactions for all the pharmacies
	 */
	private static ArrayList<Transaction> TRANSACTIONS = new ArrayList<Transaction>();
	
	/**
	 * Rend inutilisable le constructeur en dehors de la classe
	 */
	private DBTransaction() {
	}
	
	/**
	 * Retourne l'instance du singleton
	 * @return
	 */
	public static DBTransaction getInstance() {

		// Check if instantiated
		if (INSTANCE == null) {
			INSTANCE = new DBTransaction();
		}
		
		// Return the instance
		return INSTANCE;
	}

	/**
	 * Insert the record
	 * @param t
	 */
	public void addRecord(Transaction t) {
		DBTransaction.TRANSACTIONS.add(t);
	}

	/**
	 * Return all the transactions
	 * @return The transaction
	 */
	public ArrayList<Transaction> getAll() {
		return DBTransaction.TRANSACTIONS;
	}

	/**
	 * Return all the transactions
	 * @param pharmacie
	 * @return The transaction
	 */
	public ArrayList<Transaction> getAll(Pharmacie pharmacie) {

		// Output transactions
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		// For each transaction
		for (Transaction transaction : DBTransaction.TRANSACTIONS) {
			
			// If same month and pharmacy
			if (transaction.getVendeur() == pharmacie) {

				// Add the price
				transactions.add(transaction);
			}
		}
		
		// Return all the transactions in the period
		return transactions;
	}
	
	/**
	 * Return all the transactions
	 * @param pharmacie
	 * @param date
	 * @return The transaction
	 */
	public ArrayList<Transaction> getAll(Pharmacie pharmacie, Date date) {

		// Output transactions
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		// For each transaction
		for (Transaction transaction : DBTransaction.TRANSACTIONS) {
			
			// If same month and pharmacy
			if (transaction.getVendeur() == pharmacie &&
				transaction.getDate().getMonth() == date.getMonth() &&
				transaction.getDate().getYear() == date.getYear()
			) {

				// Add the price
				transactions.add(transaction);
			}
		}
		
		// Return all the transactions in the period
		return transactions;
	}
	
	/**
	 * Return all the transactions except the royalties
	 * @param pharmacie
	 * @param date
	 * @return The transaction
	 */
	public ArrayList<Transaction> getWithoutRoyalties(Pharmacie pharmacie, Date date) {
		
		// Output transactions
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		// For each transaction
		for (Transaction transaction : DBTransaction.TRANSACTIONS) {
			
			// If doesn't contains a royalties
			if (transaction.getVendeur() == pharmacie &&
				transaction.contains(TypeProduitPharmaceutique.Royalties) == false &&
				transaction.getDate().getMonth() == date.getMonth() &&
				transaction.getDate().getYear() == date.getYear()
			) {

				// Add the price
				transactions.add(transaction);
			}
		}
		
		// Return all the transactions without any royalties inside
		return transactions;
	}

}
