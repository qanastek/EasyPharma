package application.models.Abstracts;

public abstract class CarteBancaire {
	
	private R�seau r�seau;
	
	public CarteBancaire(R�seau r�seau) {
		this.r�seau = r�seau;
	}
	
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
