package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;

public class Salvateur extends VillageoisSpecial {
	private Personnage dernierPersonnageProtéger;

	public Salvateur() {
		super(7, Arrays.asList(TypeDePouvoir.Vie) );
		this.dernierPersonnageProtéger = null;
	}
	
	public void salvater() {
		 ArrayList<Personnage> personnages = this.getVillage().getHabitants();
		 if(this.dernierPersonnageProtéger != null && personnages.contains(dernierPersonnageProtéger)) {
			 personnages.remove(dernierPersonnageProtéger);
		 }
		 int nb = (int) (Math.random() * ( personnages.size()   - 0 ));
		 Personnage personnageProtéger = personnages.get(nb);
		 personnageProtéger.getStatut().setProtéger(true);
		 Logger.log("Le salvateur a décidé de protéger " + personnageProtéger, TypeDeLog.role);
		 this.dernierPersonnageProtéger = personnageProtéger;
		 
	}
	
	
	public void reset() {
		super.reset();
		this.dernierPersonnageProtéger = null;
	}
	
	@Override
	public String toString() {
		return "le salvateur";
	}

}
