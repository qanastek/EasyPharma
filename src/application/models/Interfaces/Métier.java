package application.models.Interfaces;

import application.models.Abstracts.Pharmacie;

public interface M�tier {
	
	abstract Double calculSalaire(Pharmacie pharmacie);
	
	@Override
	String toString();

}
