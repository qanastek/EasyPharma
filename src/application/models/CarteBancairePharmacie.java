package application.models;

import application.models.Abstracts.CarteBancaire;
import application.models.Interfaces.Réseau;

public class CarteBancairePharmacie extends CarteBancaire {

	public CarteBancairePharmacie(Réseau réseau) {
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
