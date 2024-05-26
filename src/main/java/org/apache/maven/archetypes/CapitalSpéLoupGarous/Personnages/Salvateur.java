package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.StatsSalvateur;

public class Salvateur extends VillageoisSpecial {
	private Personnage dernierPersonnageProtéger;
	public final static int IDROLE = 14;
	private static StatsSalvateur statsSalvateur = new StatsSalvateur();

	public Salvateur() {
		super(IDROLE, statsSalvateur);
		this.dernierPersonnageProtéger = null;
	}
	
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Vie, TypeDePouvoir.Voyance));
	}
	
	public void salvater(Personnage personnageProtéger) {
		 
		 personnageProtéger.getStatut().setProtéger(true);
		 Logger.log("Le salvateur a décidé de protéger " + personnageProtéger, TypeDeLog.role);
		 this.dernierPersonnageProtéger = personnageProtéger;
		 this.getStatsSalvateur().incrementerNbSalvation();
		 this.getStatsSalvateur().incrementerNbVillageoisSalvater(personnageProtéger);
	}
	
	public static StatsSalvateur getStatsSalvateur() {
		return statsSalvateur;
	}

	public static void setStatsSalvateur(StatsSalvateur statsSalvateur) {
		Salvateur.statsSalvateur = statsSalvateur;
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
	public void agir() {
		ArrayList<Personnage> personnages = this.getVillage().getHabitantsEnVie();
		personnages.removeAll(this.getEnnemies());
		 if(this.dernierPersonnageProtéger != null && personnages.contains(dernierPersonnageProtéger)) {
			 personnages.remove(dernierPersonnageProtéger);
		 }
		 int nb = (int) (Math.random() * ( personnages.size()   - 0 ));
		 if(!personnages.isEmpty()) {
			 Personnage personnageProtéger = personnages.get(nb);
			 this.salvater(personnageProtéger);
			 
		 }
		 else {
			 Logger.log(this + " a préférée protéger personne");// si aucun perso non ennemie disponible
			 //System.out.println("le salvateur a pas d'amis");
		 }
		 
		
	}

	@Override
	public void agirAprèsNuit() {
		if(this.getVillage().getNuitSansMort() && this.getVillage().getNbSpéEnVieACePouvoir(TypeDePouvoir.Vie) == 1) { // en se comptant lui même
			Logger.log("Puisque qu'il n'y a pas eu de mort et que " + this + " est le seule protecteur du village, " + this + " a confiance en " + this.getDernierPersonnageProtéger(), TypeDeLog.role);
			this.ajouterAllié(dernierPersonnageProtéger);
			this.getStatsSalvateur().incrementerNbInnocentIdentiferGraceSalvation();;
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
