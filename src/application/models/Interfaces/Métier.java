package application.models.Interfaces;

import application.models.Abstracts.Pharmacie;

public interface Métier {
	
	abstract Double calculSalaire(Pharmacie pharmacie);
	
	@Override
	String toString();

}
