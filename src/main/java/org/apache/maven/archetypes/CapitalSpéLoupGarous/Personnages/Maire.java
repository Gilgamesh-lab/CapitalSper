package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;

public class Maire extends Fonction {

	public Maire() {
		super(true);
	}
	
	public void entrerEnFonction(Personnage personnage, Village village) {
		if(this.getPersonnage() != null) {
			Logger.log(this.getPersonnage() + " a choisi " + personnage + " pour lui succéder en tant que maire du village");
		}
		else {
			Logger.log(personnage + " a été élue maire du village");
		}
		
		super.setPersonnage(personnage);
		super.setVillage(village);
		personnage.setFonction(this);
		personnage.incrementerNbVote(1);
		
	}
	
	public void election() {
		int nb;
		if(this.getPersonnage() != null) {
			this.getPersonnage().setAlliés(new ArrayList<Personnage>(this.getPersonnage().getAlliés().stream().filter(x->x.estEnvie()).collect(Collectors.toList())));//maj alliés
			if(this.getPersonnage().getAlliés().size() > 1 && this.getPersonnage().estUnVillageois()) {// si au moins un alliés et villageois
				nb = (int) (Math.random() * ( this.getPersonnage().getAlliés().size()    - 0 ));
				entrerEnFonction(this.getPersonnage().getAlliés().get(nb), this.getVillage());
			}
			else {// sinon full random
				nb = (int) (Math.random() * ( this.getVillage().getNbPersonnage()    - 0 ));
				entrerEnFonction(this.getVillage().getHabitantsEnVie().get(nb), this.getVillage());
			}
		}
		else {// 1er maire
			nb = (int) (Math.random() * ( this.getVillage().getNbPersonnage()    - 0 ));
			entrerEnFonction(this.getVillage().getHabitantsEnVie().get(nb), this.getVillage());
		}
	}

}
