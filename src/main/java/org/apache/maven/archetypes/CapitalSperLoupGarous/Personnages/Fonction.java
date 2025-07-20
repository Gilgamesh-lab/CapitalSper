package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Village;

public abstract class Fonction {
	private Personnage personnage;
	private Village village;
	private boolean estMaire;

	public Fonction(boolean estMaire) {
		this.estMaire = estMaire;
	}

	public Personnage getPersonnage() {
		return personnage;
	}

	public void setPersonnage(Personnage personnage) {
		this.personnage = personnage;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}
	
	public boolean estMaire() {
		return this.estMaire;
	}
	
	public void reset() {
		this.personnage = null;
		this.village = null;
	}
	

}
