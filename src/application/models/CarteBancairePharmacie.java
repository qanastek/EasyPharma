package application.models;

import application.models.Abstracts.CarteBancaire;
import application.models.Abstracts.CompteBancaire;
import application.models.Interfaces.Réseau;

public class CarteBancairePharmacie extends CarteBancaire {

	public CarteBancairePharmacie(Réseau réseau) {
		super(réseau);
	}

	@Override
	public boolean transfert(Double montant, CompteBancaire compteEmmeteur, CompteBancaire compteReceveur) {
		// TODO Auto-generated method stub
		return false;
	}
}
