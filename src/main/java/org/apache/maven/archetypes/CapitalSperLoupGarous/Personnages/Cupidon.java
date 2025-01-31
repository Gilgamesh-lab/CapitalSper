package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsCupidon;

public class Cupidon extends VillageoisSpecial {
	public final static int IDROLE = 3;
	private static StatsCupidon statCupidon = new StatsCupidon();
	
	public Cupidon() {
		super(IDROLE, statCupidon);
	}
	
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Mort));
	}

	public void flecheDeLAmour() {
		int nbPersonnageAmoureux1 = (int) (Math.random() * ( this.getVillage().getHabitantsEnVie().size()    - 0 ));
		int nbPersonnageAmoureux2 = (int) (Math.random() * ( this.getVillage().getHabitantsEnVie().size()    - 0 ));
		while(nbPersonnageAmoureux1 == nbPersonnageAmoureux2) {
			nbPersonnageAmoureux2 = (int) (Math.random() * ( this.getVillage().getHabitantsEnVie().size()    - 0 ));
		}
		Personnage personnageAmoureux = this.getVillage().getHabitantsEnVie().get(nbPersonnageAmoureux1);
		Personnage personnageAmoureux2 = this.getVillage().getHabitantsEnVie().get(nbPersonnageAmoureux2);
		statCupidon.amoureux(personnageAmoureux, personnageAmoureux2);
		personnageAmoureux.tomberAmoureux(personnageAmoureux2);
		personnageAmoureux2.tomberAmoureux(personnageAmoureux);
		Logger.log(personnageAmoureux + " et " + personnageAmoureux2 +  " sont tombés amoureux grâce à la magie de " + this, TypeDeLog.role);
		this.perdrePouvoir(TypeDePouvoir.Mort);
	}
	
	
	public static StatsCupidon getStatCupidon() {
		return statCupidon;
	}

	public static void setStatCupidon(StatsCupidon statCupidon) {
		Cupidon.statCupidon = statCupidon;
	}

	@Override
	public void agirPremiereNuit() {
		this.flecheDeLAmour();
	}

	@Override
	public String toString() {
		if(this.getVillage() != null && this.getVillage().getVillage().stream().anyMatch(x->x.getIdDeRole() == this.getIdDeRole() && x != this)) {
			return "Cupidon" + this.getId();
		}
		else {
			return "Cupidon";
		}
		
	}


	
	



}
