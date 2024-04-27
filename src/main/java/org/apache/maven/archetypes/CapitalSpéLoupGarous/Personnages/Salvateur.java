package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;

public class Salvateur extends VillageoisSpecial {
	private Personnage dernierPersonnageProtéger;
	public final static int IDROLE = 14;

	public Salvateur() {
		super(IDROLE);
		this.dernierPersonnageProtéger = null;
	}
	
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Vie, TypeDePouvoir.Voyance));
	}
	
	public void salvater(Personnage personnageProtéger) {
		 
		 personnageProtéger.getStatut().setProtéger(true);
		 Logger.log("Le salvateur a décidé de protéger " + personnageProtéger, TypeDeLog.role);
		 this.dernierPersonnageProtéger = personnageProtéger;
		 
		 
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

	@Override
	public void agirPremiereNuit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agir() {
		ArrayList<Personnage> personnages = this.getVillage().getHabitantsEnVie();
		personnages.removeAll(this.getEnnemies());
		 if(this.dernierPersonnageProtéger != null && personnages.contains(dernierPersonnageProtéger)) {
			 personnages.remove(dernierPersonnageProtéger);
		 }
		 int nb = (int) (Math.random() * ( personnages.size()   - 0 ));
		 if(personnages.size() != 0) {
			 Personnage personnageProtéger = personnages.get(nb);
			 this.salvater(personnageProtéger);
		 }
		 else {
			 Logger.log(this + " a préférée protéger personne");// si aucun perso non ennemie disponible
		 }
		 
		
	}

	@Override
	public void agirAprèsNuit() {
		if(this.getDernierPersonnageProtéger().estEnvie() && this.getVillage().getNbSpéEnVieACePouvoir(TypeDePouvoir.Vie) < 2) { // en se comptant lui même
			Logger.log("Puisque qu'il n'y a pas eu de mort et que " + this + " est le seule protecteur du village, " + this + " a confiance en " + this.getDernierPersonnageProtéger(), TypeDeLog.role);
			this.ajouterAlliés(dernierPersonnageProtéger);
		}
		
	}
	
	@Override
	public String toString() {
		if(this.getVillage() != null && this.getVillage().getVillage().stream().anyMatch(x->x.getIdDeRole() == this.getIdDeRole() && x != this)) {
			return "le salvateur" + this.getId();
		}
		else {
			return "le salvateur";
		}
		
	}
	
	

}
