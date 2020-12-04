package application.models.Abstracts;

import application.models.Utils.Toolbox;

public abstract class CarteBancaire {
		
	private R�seau r�seau;
	private String num�ro;
	
	public CarteBancaire(R�seau r�seau) {
		
		this.r�seau = r�seau;
		
		this.num�ro = String.valueOf(Toolbox.generateNbr(9999)) + " " +
					  String.valueOf(Toolbox.generateNbr(9999)) + " " +
					  String.valueOf(Toolbox.generateNbr(9999)) + " " +
					  String.valueOf(Toolbox.generateNbr(9999));
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

	public String getNum�ro() {
		return num�ro;
	}

	public void setNum�ro(String num�ro) {
		this.num�ro = num�ro;
	}
}
