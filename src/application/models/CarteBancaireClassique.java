package application.models;

import application.models.Abstracts.CarteBancaire;
import application.models.Interfaces.R�seau;

public class CarteBancaireClassique extends CarteBancaire {

	public CarteBancaireClassique(R�seau r�seau) {
		super(r�seau);
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
