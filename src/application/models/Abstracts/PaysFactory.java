package application.models.Abstracts;

import java.util.HashMap;

import application.models.Pays;

/**
 * Flyweight
 */
public class PaysFactory {
	
	// Instance Singleton
	private static PaysFactory INSTANCE;

	// Instances pays
	private static HashMap<String, Pays> LIST = new HashMap<String, Pays>();
	
	/**
	 * Constructor
	 */
	private PaysFactory() {
	}
	
	/**
	 * Get the flyweight instance
	 * @return PaysFactory
	 */
	public static PaysFactory getInstance() {
		
		// Check instantiated
		if(INSTANCE == null) {
			
			// Instantiate the flyweight
			INSTANCE = new PaysFactory();
		}
		
		// Return the instance of it
		return INSTANCE;
	}
	
	/**
	 * Getter for the Flyweight
	 * @param name The name of the country
	 * @return The country instance
	 */
	public Pays getPays(String name) {
		
		// Get the instance (if exist)
		Pays pays = LIST.get(name.toLowerCase());
		
		// Check if already exist in it
		if (pays != null) {
			
			// Return it directly
			return pays;
		}
		else {
			
			System.out.println("Create " + name);
			
			switch (name.toLowerCase()) {
			
				case "france":
					pays = new Pays(name, 0.0);
					break;
					
				case "espagne":
					pays = new Pays(name, 0.1);
					break;
					
				case "usa":
					pays = new Pays(name, 0.2);
					break;
					
				case "portugal":
					pays = new Pays(name, 0.15);
					break;
					
				case "uk":
					pays = new Pays(name, 0.25);
					break;
					
				default:
					pays = new Pays("Reste du monde", 0.30);
					break;
			}
			
			// Insert It
			LIST.put(name,pays);
		}
		
		// Return the country
		return pays;
	}
}
