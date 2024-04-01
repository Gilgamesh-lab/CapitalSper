package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;

public class Salvateur extends VillageoisSpecial {
	private Personnage dernierPersonnageProtéger;

	public Salvateur() {
		super(7, Arrays.asList(TypeDePouvoir.Vie));
		this.dernierPersonnageProtéger = null;
	}
	
	public void salvater() {
		 ArrayList<Personnage> personnages = this.getVillage().getHabitantsEnVie();
		 if(this.dernierPersonnageProtéger != null && personnages.contains(dernierPersonnageProtéger)) {
			 personnages.remove(dernierPersonnageProtéger);
		 }
		 int nb = (int) (Math.random() * ( personnages.size()   - 0 ));
		 Personnage personnageProtéger = personnages.get(nb);
		 personnageProtéger.getStatut().setProtéger(true);
		 Logger.log("Le salvateur a décidé de protéger " + personnageProtéger, TypeDeLog.role);
		 this.dernierPersonnageProtéger = personnageProtéger;
		 
		 /*if(!this.getVillage().getHabitants().stream().filter(x->x.aUnPouvoirSpecial() && !x.equals(this) ).map(c -> (VillageoisSpecial) c).anyMatch(x->x.getTypeDePouvoir().contains(TypeDePouvoir.Vie))) {
			 if(!this.getAlliés().contains(personnageProtéger)) {
				 this.ajouterAlliés(personnageProtéger);
				 Logger.log("Le salvateur a confiance en " + personnageProtéger, TypeDeLog.role);
			 }
		 }*/
		 
	}
	
	
	
	public Personnage getDernierPersonnageProtéger() {
		return dernierPersonnageProtéger;
	}

	public void finSalvation() {
		this.dernierPersonnageProtéger.getStatut().setProtéger(false);
	}
	
	
	public void reset() {
		super.reset();
		this.dernierPersonnageProtéger = null;
	}
	
	@Override
	public String toString() {
		return "le salvateur";
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dernierPersonnageProtéger);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salvateur other = (Salvateur) obj;
		return Objects.equals(dernierPersonnageProtéger, other.dernierPersonnageProtéger);
	}
	
	

}
