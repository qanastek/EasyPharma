package application.models;

import application.models.Abstracts.CarteBancaire;
import application.models.Abstracts.CompteBancaire;
import application.models.Interfaces.R�seau;

public class CarteBancairePharmacie extends CarteBancaire {

	public CarteBancairePharmacie(R�seau r�seau) {
		super(r�seau);
	}

	@Override
	public boolean transfert(Double montant, CompteBancaire compteEmmeteur, CompteBancaire compteReceveur) {
		// TODO Auto-generated method stub
		return false;
	}
}
