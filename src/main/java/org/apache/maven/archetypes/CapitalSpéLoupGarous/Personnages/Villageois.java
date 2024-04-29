package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.StatsPersonnages.StatPersonnage;

public abstract class Villageois extends Personnage {
	
	
	protected Villageois(int idRole, boolean aUnPouvoirSpecial) {
		super(true, idRole, aUnPouvoirSpecial);
		
	}
	
	protected Villageois(int idRole, boolean aUnPouvoirSpecial, StatPersonnage statPersonnage) {
		super(true, idRole, aUnPouvoirSpecial, statPersonnage);
		
	}
	

	
	

	
	
	
	

}
