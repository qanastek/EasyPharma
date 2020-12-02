package application.models;

import application.models.Abstracts.CarteBancaire;
import application.models.Interfaces.Réseau;

public class CarteBancaireClassique extends CarteBancaire {

	public CarteBancaireClassique(Réseau réseau) {
		super(réseau);
	}

	@Override
	public boolean recevoir(Double montant) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean payer(Double montant) {
		// TODO Auto-generated method stub
		return false;
	}
}
