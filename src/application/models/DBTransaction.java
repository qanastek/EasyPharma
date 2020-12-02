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
	private static ArrayList<Transaction> TRANSACTIONS;
	
	private DBTransaction() {
	}
	
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
	 * @param pharmacie
	 * @param date
	 * @return The transaction
	 */
	public ArrayList<Transaction> getAll(Pharmacie pharmacie, Date date) {
		return DBTransaction.TRANSACTIONS;
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
			if (transaction.contains(TypeProduitPharmaceutique.Royalties) == false) {

				// Add the price
				transactions.add(transaction);
			}
		}
		
		// Return all the transactions without any royalties inside
		return transactions;
	}

}
