package application.models;

import java.util.Date;

import application.models.Abstracts.Pharmacie;

public abstract class Employé extends Personne {
	
	// Date d'embauche
	private Date dateEmbauché;
	
	/**
	 * Constructeur
	 * @param nom
	 * @param prénom
	 * @param adresse
	 * @param métier
	 */
	public Employé(String nom, String prénom, String adresse, Date dateEmbauché) {
		super(nom, prénom, adresse);
		
		this.setDateEmbauché(dateEmbauché);
	}
	
	/**
	 * Calcul le salaire après la prime
	 * @param pharmacie
	 * @return
	 */	
	protected abstract Double salaireAprèsPrime(Pharmacie pharmacie, Double salaire, Date date);
	
	/**
	 * Calcul le salaire final
	 * @param pharmacie
	 * @return
	 */
	public abstract Double calculSalaire(Pharmacie pharmacie, Date date);

	public Date getDateEmbauché() {
		return dateEmbauché;
	}

	public void setDateEmbauché(Date dateEmbauché) {
		this.dateEmbauché = dateEmbauché;
	}
	
	/**
	 * Retourne l'ancienneté
	 * @return
	 */
	public int getAncienneté() {
		return new Date().getYear() - dateEmbauché.getYear();
	}
	
	@Override
	public String toString() {
		return getNom() + " " + getPrénom();
	}
}
