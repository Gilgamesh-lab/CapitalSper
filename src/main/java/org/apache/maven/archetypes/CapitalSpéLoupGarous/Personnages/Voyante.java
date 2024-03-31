package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;

public class Voyante extends VillageoisSpecial {
	

	public Voyante() {
		super(8, Arrays.asList(TypeDePouvoir.Voyance));
	}
	
	public void sonder() {
		 Personnage persoASonder = super.voteCibleAction();
		 if(persoASonder.estUnVillageois()) {
			 Logger.log("La voyante a décidé de sonder " + persoASonder + " qui s'est révélé être innocent", TypeDeLog.role);
			 this.ajouterAlliés(persoASonder);
			 
		 }
		 else {
			 Logger.log("La voyante a décidé de sonder " + persoASonder + " qui s'est révélé être un ennemie du village", TypeDeLog.role);
			 this.ajouterEnnemies(persoASonder);
			 
		 }
	}
	
	
	
	@Override
	public String toString() {
		return "la voyante";
	}
	
	

}
