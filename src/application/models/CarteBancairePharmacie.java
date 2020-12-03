package application.models;

import application.models.Abstracts.CarteBancaire;
import application.models.Abstracts.CompteBancaire;
import application.models.Abstracts.Réseau;

public class CarteBancairePharmacie extends CarteBancaire {

	public CarteBancairePharmacie(Réseau réseau) {
		super(réseau);
	}
}
