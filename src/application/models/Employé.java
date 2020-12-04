package application.models;

import java.util.Date;

import application.models.Abstracts.Pharmacie;

public abstract class Employ� extends Personne {
	
	// Date d'embauche
	private Date dateEmbauch�;
	
	/**
	 * Constructeur
	 * @param nom
	 * @param pr�nom
	 * @param adresse
	 * @param m�tier
	 */
	public Employ�(String nom, String pr�nom, String adresse, Date dateEmbauch�) {
		super(nom, pr�nom, adresse);
		
		this.setDateEmbauch�(dateEmbauch�);
	}
	
	/**
	 * Calcul le salaire apr�s la prime
	 * @param pharmacie
	 * @return
	 */	
	protected abstract Double salaireApr�sPrime(Pharmacie pharmacie, Double salaire, Date date);
	
	/**
	 * Calcul le salaire final
	 * @param pharmacie
	 * @return
	 */
	public abstract Double calculSalaire(Pharmacie pharmacie, Date date);

	public Date getDateEmbauch�() {
		return dateEmbauch�;
	}

	public void setDateEmbauch�(Date dateEmbauch�) {
		this.dateEmbauch� = dateEmbauch�;
	}
	
	/**
	 * Retourne l'anciennet�
	 * @return
	 */
	public int getAnciennet�() {
		return new Date().getYear() - dateEmbauch�.getYear();
	}
	
	@Override
	public String toString() {
		return getNom() + " " + getPr�nom();
	}
}
