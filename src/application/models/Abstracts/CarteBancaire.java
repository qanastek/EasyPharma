package application.models.Abstracts;

public abstract class CarteBancaire {
	
	private Réseau réseau;
	
	public CarteBancaire(Réseau réseau) {
		this.réseau = réseau;
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
}
