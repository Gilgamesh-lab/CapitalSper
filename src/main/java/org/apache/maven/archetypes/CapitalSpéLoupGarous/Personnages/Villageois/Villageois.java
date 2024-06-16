package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.Statistiques;

public abstract class Villageois extends Personnage {
	
	
	protected Villageois(int idRole, boolean aUnPouvoirSpecial) {
		super(true, idRole, aUnPouvoirSpecial);
		
	}
	
	protected Villageois(int idRole, boolean aUnPouvoirSpecial, Statistiques statPersonnage) {
		super(true, idRole, aUnPouvoirSpecial, statPersonnage);
		
	}
	

	
	

	
	
	
	

}
