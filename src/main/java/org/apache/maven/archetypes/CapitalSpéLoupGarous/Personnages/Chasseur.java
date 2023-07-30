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
		this.getListeDeVote().clear();
		for(int i = 0; i < this.getVillage().getNbPersonnage(); i++) {
			this.getListeDeVote().add(this.getVillage().getHabitants().get(i));
		}
		for(int i = 0 ; i < this.getAlliés().size() ; i++) {
			this.getListeDeVote().remove(this.getAlliés().get(i));
		}
		int nb = (int) (Math.random() * ( this.getListeDeVote().size()    - 0 ));
		Logger.log("Dans son dernier souffle le chasseur a décidé d'emporter avec lui " + this.getListeDeVote().get(nb));
		super.meurt();
		this.tuer(getVillage().getHabitants().get(nb));;
		
	}

	@Override
	public String toString() {
		return "le chasseur";
	}

}
