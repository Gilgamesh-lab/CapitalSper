package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;

public class Chasseur extends VillageoisSpecial {
	

	public Chasseur() {
		super(5, Arrays.asList(TypeDePouvoir.Mort) );
	}

	@Override
	public void agir() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void meurt() {
		int nb = (int) (Math.random() * ( this.getVillage().getNbPersonnage()   - 0 ));
		while(this.getAlliés().contains(this.getVillage().getHabitants().get(nb))) {
			nb = (int) (Math.random() * ( this.getVillage().getNbPersonnage()   - 0 ));
		}
		Logger.log("Dans son dernier souffle le chasseur a décidé d'emporter avec lui " + this.getVillage().getHabitants().get(nb));
		this.getVillage().getHabitants().get(nb).meurt();
		super.meurt();
	}

	@Override
	public String toString() {
		return "Chasseur";
	}

}
