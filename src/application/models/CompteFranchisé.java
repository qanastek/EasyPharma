package application.models;

import java.util.ArrayList;
import java.util.Date;

import application.models.Abstracts.Client;
import application.models.Abstracts.CompteBancaire;
import application.models.Abstracts.Pharmacie;
import application.models.Patterns.CommandTransaction.CommandTransaction;

public class CompteFranchis� extends CompteBancaire {

	@Override
	public CommandTransaction paiement(ArrayList<ProduitPharmaceutique> produits, Double montant, Client acheteur, Pharmacie vendeur, int carteClient) {

		// Check if the client can pay
		if (acheteur.getCompteBancaire().v�rificationSolvabilit�(montant) == false) {
			return null;
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

		// La commande associ� � la transaction
		CommandTransaction cmd = new CommandTransaction(t, false);
		
		// Ajoute la commande dans l'historique d'achat du client
		acheteur.ajouterCommande(cmd);

		// Retourne la commande
		return cmd;
	}
}
