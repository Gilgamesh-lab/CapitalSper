package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;

public class Sorcière extends VillageoisSpecial{
	private boolean aUnePotionDeVie;
	private boolean aUnePotionDeMort;
	private ArrayList<Integer> actions;
	private Integer action;

	public Sorcière() {
		super(6, Arrays.asList(TypeDePouvoir.Mort,TypeDePouvoir.Vie, TypeDePouvoir.Voyance));
		this.aUnePotionDeVie = true;
		this.aUnePotionDeMort = true;
		this.action = null;
		this.actions = new ArrayList<Integer>(Arrays.asList(0,1,2,3)) ;
		/* 0= potion de vie
		 * 1 = potion de mort
		 * 2 = aucune action
		 * 3 = potion de vie et de mort
		 * 
		 * */
	}
	

	public void agir() {
		if((this.getStatut().aEteAttaquerParLaMeute() && this.aUnePotionDeVie)) {
			this.potionDeVie(this);
			Logger.log("La sorcière s'est sauvé elle-même de l'attaque des Loups-garous grâce à sa potion de vie", TypeDeLog.role);
		}
		
		if(this.actions.size() == 1) {
			return;
		}
		if(this.action == null) {
			this.action = (int) (Math.random() * ( this.actions.size() - 0 ));
			//this.action = this.actions.get(action);
		}
		
		
		if((this.aUnePotionDeVie && ((this.action == 0 || this.action == 3)  || (this.estAmoureux() && this.getAmoureux().getStatut().aEteAttaquerParLaMeute()))) && this.getVillage().getHabitantsEnVie().stream().anyMatch(x->x.getStatut().aEteAttaquerParLaMeute()) ) {
			Personnage personnageASauver = this.getVillage().getHabitantsEnVie().stream().filter(x->x.getStatut().aEteAttaquerParLaMeute()).findAny().get();
			this.potionDeVie(personnageASauver);
			Logger.log("La sorcière a sauvé " + personnageASauver +  " de l'attaque des Loups-garous grâce à sa potion de vie", TypeDeLog.role);
		}
		if ((this.action == 1 || this.action == 3) && this.aUnePotionDeMort) {
			this.potionDeMort(this.getVillage().getPersonnageParId(this.voter()));
			this.resetListeDeVote();
		}
		this.action = null;
	}
	
	public void potionDeVie(Personnage personnageASauver) {
		if(!this.getAlliés().contains(personnageASauver)) {
			this.ajouterAlliés(personnageASauver);
		}
		
		personnageASauver.getStatut().setAEteAttaqueParLaMeute(false);
		this.actions.remove(0);
		if(this.actions.contains(3)) {
			this.actions.remove((Object)3);
		}
		this.aUnePotionDeVie = false;
		
	}
	
	public void potionDeMort(Personnage personnageATuer) {
		this.actions.remove(1);
		if(this.actions.contains(3)) {
			this.actions.remove((Object)3);
		}
		this.aUnePotionDeMort = false;
		String messageMort;
		if(Logger.isAfficherLogDetailsRoleActionOn()) {
			messageMort = "La sorcière a tué " + personnageATuer +  " avec sa potion de mort";
		}
		else {
			messageMort = personnageATuer +  " a été tué durant la nuit";
		}
		Logger.log(messageMort, TypeDeLog.role);
		this.tuer(personnageATuer);
	}
	


	public void setAction(Integer action) {
		this.action = action;
	}
	
	


	public boolean isaUnePotionDeVie() {
		return aUnePotionDeVie;
	}


	public boolean isaUnePotionDeMort() {
		return aUnePotionDeMort;
	}


	public void reset() {
		super.reset();
		this.aUnePotionDeVie = true;
		this.aUnePotionDeMort = true;
		this.action = null;
		this.actions = new ArrayList<Integer>(Arrays.asList(0,1,2,3));
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(aUnePotionDeMort, aUnePotionDeVie, action, actions);
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
		Sorcière other = (Sorcière) obj;
		return aUnePotionDeMort == other.aUnePotionDeMort && aUnePotionDeVie == other.aUnePotionDeVie
				&& Objects.equals(action, other.action) && Objects.equals(actions, other.actions);
	}


	/*@Override
	public String toString() {
		return "la sorcière";
	}*/

}
