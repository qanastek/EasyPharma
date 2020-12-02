package application.models;

import application.models.Abstracts.CompteBancaire;

public class CompteFranchisé extends CompteBancaire {

	@Override
	public boolean payer(ProduitPharmaceutique produit) {
		// TODO Auto-generated method stub
		return false;
	}

}
