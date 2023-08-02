package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;

public class Sorcière extends VillageoisSpecial{
	private boolean aUnePotionDeVie;
	private boolean aUnePotionDeMort;
	ArrayList<Integer> actions;

	public Sorcière() {
		super(6, Arrays.asList(TypeDePouvoir.Mort,TypeDePouvoir.Vie, TypeDePouvoir.Voyance));
		this.aUnePotionDeVie = true;
		this.aUnePotionDeMort = true;
		this.actions = new ArrayList<Integer>(Arrays.asList(0,1,2,3)) ;
		/* 0= potion de vie
		 * 1 = potion de mort
		 * 2 = aucune action
		 * 3 = potion de vie et de mort
		 * 
		 * */
	}
	

	public void agir() {
		if((this.getStatut().estAttaquerParLg() && this.aUnePotionDeVie)) {
			this.potionDeVie(this);
			Logger.log("La sorcière s'est sauvé elle-même de l'attaque des Loups-garous grâce à sa potion de vie", TypeDeLog.role);
		}
		
		if(this.actions.size() == 1) {
			return;
		}
		int action = (int) (Math.random() * ( this.actions.size() - 0 ));
		action = this.actions.get(action);
		if((this.aUnePotionDeVie && ((action == 0 || action == 3)  || (this.estAmoureux() && this.getAmoureux().getStatut().estAttaquerParLg()))) && this.getVillage().getHabitants().stream().anyMatch(x->x.getStatut().estAttaquerParLg()) ) {
			Personnage personnageASauver = this.getVillage().getHabitants().stream().filter(x->x.getStatut().estAttaquerParLg()).findAny().get();
			this.potionDeVie(personnageASauver);
			Logger.log("La sorcière a sauvé " + personnageASauver +  " de l'attaque des Loups-garous grâce à sa potion de vie", TypeDeLog.role);
		}
		if ((action == 1 || action == 3) && this.aUnePotionDeMort) {
			this.potionDeMort(this.voteCibleAction());
			
		}
	}
	
	public void potionDeVie(Personnage personnageASauver) {
		if(!this.getAlliés().contains(personnageASauver)) {
			this.ajouterAlliés(personnageASauver);
		}
		
		personnageASauver.getStatut().setAttaquerParLg(false);
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
			messageMort= personnageATuer +  " a été tué durant la nuit";
		}
		Logger.log(messageMort);
		this.tuer(personnageATuer);
	}
	
	public void reset() {
		super.reset();
		this.aUnePotionDeVie = true;
		this.aUnePotionDeMort = true;
		this.actions = new ArrayList<Integer>(Arrays.asList(0,1,2,3));
	}
	
	@Override
	public String toString() {
		return "la sorcière";
	}

}
