package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.Statistiques;

public abstract class Villageois extends Personnage {
	
	
	protected Villageois(int idRole, boolean aUnPouvoirSpecial) {
		super(true, idRole, aUnPouvoirSpecial);
		
	}
	
	protected Villageois(int idRole, boolean aUnPouvoirSpecial, Statistiques statPersonnage) {
		super(true, idRole, aUnPouvoirSpecial, statPersonnage);
		
	}
	

	
	

	
	
	
	

}
