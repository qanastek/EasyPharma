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
	public CommandTransaction(Transaction t, boolean immédiat) {
		
		// The transaction instance
		this.t = t;
		
		// Débit immédiat
		t.setDébité(immédiat);
		
		// Si débit immédiat
		if (immédiat == true) {
			
			// Débite l'acheteur
			t.getAcheteur().getCompteBancaire().débiter(
				t.getMontant() * (1 + t.getCarteClient().getRéseau().getCoutRéseau())
			);
			
			// Crédite le vendeur
			t.getVendeur().getCompteBancaire().créditer(t.getMontant());			
		}
				
		// Insert the record
		DBTransaction.getInstance().addRecord(this.t);
	}
	
	/**
	 * Rembourse la transaction
	 */
	public void rembourser() {
		
		// Débite l'acheteur
		t.getAcheteur().getCompteBancaire().créditer(
			t.getCarteClient().getRéseau().calculRemboursement(t)
		);
		
		// Crédite le vendeur
		t.getVendeur().getCompteBancaire().débiter(
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
