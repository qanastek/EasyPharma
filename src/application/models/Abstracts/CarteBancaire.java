package application.models.Abstracts;

import application.models.Interfaces.R�seau;

public abstract class CarteBancaire {
	
	private R�seau r�seau;
	
	public CarteBancaire(R�seau r�seau) {
		this.r�seau = r�seau;
	}
	
	public abstract boolean recevoir(Double montant);
	
	public abstract boolean payer(Double montant);

	public R�seau getR�seau() {
		return r�seau;
	}

	public void setR�seau(R�seau r�seau) {
		this.r�seau = r�seau;
	}
	
	@Override
	public String toString() {
		return getR�seau().toString();
	}
}
