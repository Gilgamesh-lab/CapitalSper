package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;

public class Meute {
	
	private ArrayList<LoupGarou> meute;
	private Village village;
	
	public Meute() {
		this.meute = new ArrayList<LoupGarou>();
	}

	public ArrayList<LoupGarou> getMeute() {
		return (ArrayList<LoupGarou>) this.meute.stream().filter(x->x.estEnvie()).collect(Collectors.toList());
	}
	
	public void enrolerUnLoupGarou(LoupGarou loupGarou) {
		loupGarou.rejoindreUneMeute(this);
		for(int i = 0; i < this.meute.size(); i++) {
			loupGarou.ajouterAlliés(this.meute.get(i));
		}
		for(int i = 0; i < this.meute.size(); i++) {
			this.meute.get(i).ajouterAlliés(loupGarou);;
		}
		this.meute.add(loupGarou);
	}

	public void setVillage(Village village) {
		this.village = village;
	}
	
	public boolean estEnVie() {
		return this.meute.size() != 0;
	}
	
	
	public void devorer(Personnage personnage) {
		personnage.getStatut().setAttaquerParLg(true);
	}
	
	public void attaquerVillage() {
		if(this.village.getNbVillageois() !=0) {
			int nb = (int) (Math.random() * ( (this.village.getNbVillageois())));
			//Logger.log(this.village.getVillageois().get(nb) + " a été tué la nuit");
			//this.village.getVillageois().get(nb).meurt();
			this.devorer(this.village.getVillageois().get(nb));
		}
		
	}

}
