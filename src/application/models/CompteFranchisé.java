package application.models;

import application.models.Abstracts.CompteBancaire;

public class CompteFranchis� extends CompteBancaire {

	@Override
	public boolean payer(ProduitPharmaceutique produit) {
		// TODO Auto-generated method stub
		return false;
	}

}
