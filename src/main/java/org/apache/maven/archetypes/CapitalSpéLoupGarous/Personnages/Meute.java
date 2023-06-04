package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;

public class Meute {
	
	private ArrayList<LoupGarou> meute;
	private Village village;
	
	public Meute() {
		this.meute = new ArrayList<LoupGarou>();
	}

	public ArrayList<LoupGarou> getMeute() {
		return this.meute;
	}
	
	public void ajouterLoup(LoupGarou loupGarou) {
		this.meute.add(loupGarou);
	}

	public void setVillage(Village village) {
		this.village = village;
	}
	
	
	public void attaquerVillage() {
		int nb = (int) (Math.random() * ( (this.village.getNbVillageois() )  - 0 ));
		this.village.getVillage().remove(this.village.getVillageois().get(nb));
		this.village.getVillageois().remove(nb);
		System.out.println("Un villageois a été tué la nuit par les loups-garous");
	}

}
