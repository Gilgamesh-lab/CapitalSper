package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;

public class Chasseur extends VillageoisSpecial {
	

	public Chasseur() {
		super(5, Arrays.asList(TypeDePouvoir.Mort) );
	}
	
	@Override
	public void meurt() {
		if(this.getVillage().getNbLoupGarou() > 0) {
			Personnage cible = this.getVillage().getPersonnageParId(this.voter());
			this.resetListeDeVote();
			Logger.log("Dans son dernier souffle le chasseur a décidé d'emporter avec lui " + cible);
			this.tuer(cible);
		}
		super.meurt();
		
	}
	
	
	

	@Override
	public String toString() {
		return "le chasseur";
	}

}
