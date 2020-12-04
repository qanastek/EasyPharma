package application.models.Abstracts;

import application.models.Utils.Toolbox;

public abstract class CarteBancaire {
		
	private Réseau réseau;
	private String numéro;
	
	public CarteBancaire(Réseau réseau) {
		
		this.réseau = réseau;
		
		this.numéro = String.valueOf(Toolbox.generateNbr(9999)) + " " +
					  String.valueOf(Toolbox.generateNbr(9999)) + " " +
					  String.valueOf(Toolbox.generateNbr(9999)) + " " +
					  String.valueOf(Toolbox.generateNbr(9999));
	}
	
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

	public String getNuméro() {
		return numéro;
	}

	public void setNuméro(String numéro) {
		this.numéro = numéro;
	}
}
