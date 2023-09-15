package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;

public class Chasseur extends VillageoisSpecial {
	

	public Chasseur() {
		super(5, Arrays.asList(TypeDePouvoir.Mort) );
	}
	
	@Override
	public void meurt() {
		super.meurt();
		if(this.getVillage().getNbLoupGarou() > 0) {
			Personnage cible = this.voteCibleAction();
			Logger.log("Dans son dernier souffle le chasseur a décidé d'emporter avec lui " + cible);
			this.tuer(cible);
		}
	}
	
	
	

	@Override
	public String toString() {
		return "le chasseur";
	}

}
