package application.models.Threads;

import java.util.Date;

import application.models.DBTransaction;
import application.models.Transaction;
import javafx.concurrent.Task;

public class Crawler extends Task<Void> {

	@Override
	protected Void call() throws Exception {

		// Get the database of transactions
		DBTransaction db = DBTransaction.getInstance();
		
		// For each transaction
		for (Transaction t: db.getAll()) {
			
			// Check if can be debited
			if (t.isDébité() == false && 
				t.getDate().getYear() == new Date().getYear() &&
				t.getDate().getMonth() == new Date().getMonth() - 1
			) {

				// Débite l'acheteur
				t.getAcheteur().getCompteBancaire().débiter(t.getMontant());
				
				// Crédite le vendeur
				t.getVendeur().getCompteBancaire().créditer(t.getMontant());				
				
				// Change status
				t.setDébité(true);
			}
		}
		
		// Stop the thread
		return null;
	}

}
