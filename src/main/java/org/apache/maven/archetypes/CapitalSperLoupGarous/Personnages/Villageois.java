package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Villageois;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.Statistiques;

public abstract class Villageois extends Personnage {
	
	
	protected Villageois(int idRole, boolean aUnPouvoirSpecial) {
		super(true, idRole, aUnPouvoirSpecial);
		
	}
	
	protected Villageois(int idRole, boolean aUnPouvoirSpecial, Personnage infecter) {
		super(true, idRole, aUnPouvoirSpecial);
		
		
		
	}
	
	
	
	protected Villageois(int idRole, boolean aUnPouvoirSpecial, Statistiques statPersonnage) {
		super(true, idRole, aUnPouvoirSpecial, statPersonnage);
		
	}
	

	
	

	
	
	
	

}
