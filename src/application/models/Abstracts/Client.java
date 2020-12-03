package application.models.Abstracts;

import java.util.ArrayList;

import application.models.Patterns.CommandTransaction.CommandTransaction;

public abstract class Client {
	
	// Compte bancaire
	private CompteBancaire compteBancaire;
	
	// Liste de commandes
	private ArrayList<CommandTransaction> commandes = new ArrayList<CommandTransaction>();

	public CompteBancaire getCompteBancaire() {
		return compteBancaire;
	}

	public void setCompteBancaire(CompteBancaire compteBancaire) {
		this.compteBancaire = compteBancaire;
	}	
	
	// 0%
	public Double getReduction(Pharmacie seller) {		
		return 0.0;
	}
	
	/**
	 * Ajoute la commande dans la liste des achats du client
	 * @param commande
	 */
	public void ajouterCommande(CommandTransaction commande) {
		this.commandes.add(commande);
	}

	public ArrayList<CommandTransaction> getCommandes() {
		return commandes;
	}

	public void setCommandes(ArrayList<CommandTransaction> commandes) {
		this.commandes = commandes;
	}

}
