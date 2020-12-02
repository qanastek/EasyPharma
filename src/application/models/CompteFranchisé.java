package application.models;

import java.util.ArrayList;
import java.util.Date;

import application.models.Abstracts.Client;
import application.models.Abstracts.CompteBancaire;
import application.models.Abstracts.Pharmacie;

public class CompteFranchis� extends CompteBancaire {

	@Override
	public boolean paiement(ArrayList<ProduitPharmaceutique> produits, Double montant, Client acheteur, Pharmacie vendeur, int carteClient) {
		// TODO Auto-generated method stub


		// Check if the client can pay
		if (acheteur.getCompteBancaire().v�rificationSolvabilit�(montant) == false) {
			return false;
		}
		
		// Cr�er une transaction
		Transaction t = new Transaction(
			vendeur,
			acheteur,
			acheteur.getCompteBancaire().getCartes().get(carteClient),
			new Date(),
			new VenteMaisonM�re().calculPrixVente(montant, vendeur, acheteur),
			produits
		);
		
		// Insert the record
		DBTransaction.getInstance().addRecord(t);
		
		return false;
	}
}
