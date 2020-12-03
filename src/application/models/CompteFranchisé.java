package application.models;

import java.util.ArrayList;
import java.util.Date;

import application.models.Abstracts.Client;
import application.models.Abstracts.CompteBancaire;
import application.models.Abstracts.Pharmacie;
import application.models.Patterns.CommandTransaction.CommandTransaction;

public class CompteFranchisé extends CompteBancaire {

	@Override
	public CommandTransaction paiement(ArrayList<ProduitPharmaceutique> produits, Double montant, Client acheteur, Pharmacie vendeur, int carteClient) {

		// Check if the client can pay
		if (acheteur.getCompteBancaire().vérificationSolvabilité(montant) == false) {
			return null;
		}
		
		// Créer une transaction
		Transaction t = new Transaction(
			vendeur,
			acheteur,
			acheteur.getCompteBancaire().getCartes().get(carteClient),
			new Date(),
			new VenteMaisonMère().calculPrixVente(montant, vendeur, acheteur),
			produits
		);

		// La commande associé à la transaction
		CommandTransaction cmd = new CommandTransaction(t, false);
		
		// Ajoute la commande dans l'historique d'achat du client
		acheteur.ajouterCommande(cmd);

		// Retourne la commande
		return cmd;
	}
}
