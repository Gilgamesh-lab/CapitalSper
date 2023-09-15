package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;

public class Cupidon extends VillageoisSpecial {

	public Cupidon() {
		super(4, Arrays.asList(TypeDePouvoir.Mort));
	}

	
	public void flecheDeLAmour() {
		int nbPersonnageAmoureux1 = (int) (Math.random() * ( this.getVillage().getHabitantsEnVie().size()    - 0 ));
		int nbPersonnageAmoureux2 = (int) (Math.random() * ( this.getVillage().getHabitantsEnVie().size()    - 0 ));
		while(nbPersonnageAmoureux1 == nbPersonnageAmoureux2) {
			nbPersonnageAmoureux2 = (int) (Math.random() * ( this.getVillage().getHabitantsEnVie().size()    - 0 ));
		}
		Personnage personnageAmoureux = this.getVillage().getHabitantsEnVie().get(nbPersonnageAmoureux1);
		Personnage personnageAmoureux2 = this.getVillage().getHabitantsEnVie().get(nbPersonnageAmoureux2);
		personnageAmoureux.tomberAmoureux(personnageAmoureux2);
		personnageAmoureux2.tomberAmoureux(personnageAmoureux);
		Logger.log(personnageAmoureux + " et " + personnageAmoureux2 +  " sont tombés amoureux grâce à la magie de Cupidon", TypeDeLog.role);
	}

	@Override
	public String toString() {
		return "Cupidon";
	}
	
	



}
