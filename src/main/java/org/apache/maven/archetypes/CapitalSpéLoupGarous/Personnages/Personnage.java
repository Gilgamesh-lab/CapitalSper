package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;

public abstract class Personnage {
	
	private boolean estUnVillageois;
	private int id;
	private Village village;


	public void setId(int id) {
		this.id = id;
	}

	public Personnage(Boolean estUnVillageois) {
		this.estUnVillageois = estUnVillageois;
	}

	public boolean estUnVillageois() {
		return estUnVillageois;
	}
	
	public void agir() {
		
	}


	public void setVillage(Village village) {
		this.village = village;
	}


	
}
