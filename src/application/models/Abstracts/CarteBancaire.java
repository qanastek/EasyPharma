package application.models.Abstracts;

import application.models.Interfaces.Réseau;

public abstract class CarteBancaire {
	
	private Réseau réseau;
	
	public CarteBancaire(Réseau réseau) {
		this.réseau = réseau;
	}
	
	public abstract boolean transfert(Double montant, CompteBancaire compteEmmeteur, CompteBancaire compteReceveur);

	public Réseau getRéseau() {
		return réseau;
	}

	public void setRéseau(Réseau réseau) {
		this.réseau = réseau;
	}
	
	@Override
	public String toString() {
		return getRéseau().toString();
	}
}
