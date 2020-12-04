package application.models.Patterns.CommandTransaction;

import application.models.DBTransaction;
import application.models.Transaction;

public class CommandTransaction {
		
	// The transaction
	Transaction t;
	
	/**
	 * Constructor
	 * @param t The transaction
	 */
	public CommandTransaction(Transaction t, boolean imm�diat) {
		
		// The transaction instance
		this.t = t;
		
		// D�bit imm�diat
		t.setD�bit�(imm�diat);
		
		// Si d�bit imm�diat
		if (imm�diat == true) {
			
			// D�bite l'acheteur
			t.getAcheteur().getCompteBancaire().d�biter(
				t.getMontant() * (1 + t.getCarteClient().getR�seau().getCoutR�seau())
			);
			
			// Cr�dite le vendeur
			t.getVendeur().getCompteBancaire().cr�diter(t.getMontant());			
		}
				
		// Insert the record
		DBTransaction.getInstance().addRecord(this.t);
	}
	
	/**
	 * Rembourse la transaction
	 */
	public void rembourser() {
		
		// D�bite l'acheteur
		t.getAcheteur().getCompteBancaire().cr�diter(
			t.getCarteClient().getR�seau().calculRemboursement(t)
		);
		
		// Cr�dite le vendeur
		t.getVendeur().getCompteBancaire().d�biter(
			t.getMontant()
		);

		// Clone la transaction
		Transaction transaction = (Transaction) t.clone();
		
		// Inverse
		transaction.setMontant(-t.getMontant());

		// Insert the record
		DBTransaction.getInstance().addRecord(transaction);
		
		
	}
}
