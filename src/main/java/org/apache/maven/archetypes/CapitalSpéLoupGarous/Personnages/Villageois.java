package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Partie;

public abstract class Villageois extends Personnage {
	
	
	public Villageois(int idRole) {
		super(true, idRole);
	}
	
	

	
	
	@Override
	public void meurt() {
		super.meurt();
		this.getVillage().getVillageois().remove(this);
	}
	
	
	

}
