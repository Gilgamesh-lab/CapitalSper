package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Partie;

public abstract class Villageois extends Personnage {
	
	
	public Villageois(int idRole, boolean aUnPouvoirSpecial) {
		super(true, idRole, aUnPouvoirSpecial);
		
	}
	
	public void reset() {
		this.getAlliés().clear();
		
		super.reset();
	}
	
	

	
	
	
	

}
