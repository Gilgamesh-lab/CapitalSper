package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;

public class Chasseur extends VillageoisSpecial {
	public final static int IDROLE = 22 ;

	public Chasseur() {
		super(IDROLE);
	}
	
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Mort));
	}
	
	@Override
	public void meurt() {
		super.meurt();
		if(this.getVillage().getNbLoupGarou() > 0) {
			Personnage cible = this.getVillage().getPersonnageParId(this.voter());
			this.resetListeDeVote();
			Logger.log("Dans son dernier souffle " + this + " a décidé d'emporter avec lui " + cible);
			this.tuer(cible);
			this.perdrePouvoir(TypeDePouvoir.Mort);
		}
		
		
		
	}
	
	

	@Override
	public void agirPremiereNuit() {}

	@Override
	public void agir() {}

	@Override
	public void agirAprèsNuit() {}
	
	@Override
	public String toString() {
		if(this.getVillage() != null && this.getVillage().getVillage().stream().anyMatch(x->x.getIdDeRole() == this.getIdDeRole() && x != this)) {
			return "le chasseur" + this.getId();
		}
		else {
			return "le chasseur";
		}
		
	}

}
