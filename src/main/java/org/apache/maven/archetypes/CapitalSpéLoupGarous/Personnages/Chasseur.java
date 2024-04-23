package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;

public class Chasseur extends VillageoisSpecial {
	public final static int IDROLE = 22 ;

	public Chasseur() {
		super(IDROLE, Arrays.asList(TypeDePouvoir.Mort) );
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

	@Override
	public void agirPremiereNuit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agirAprèsNuit() {
		// TODO Auto-generated method stub
		
	}

}
