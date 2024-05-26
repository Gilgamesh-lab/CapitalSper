package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.StatsSorciere;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.StatsVoyante;

public class Sorciere extends VillageoisSpecial{
	private ArrayList<Integer> actions;
	private Integer action;
	public final static int IDROLE = 19;
	private static StatsSorciere statsSorciere = new StatsSorciere();

	public Sorciere() {
		super(IDROLE, statsSorciere);
		this.action = null;
		this.actions = new ArrayList<Integer>(Arrays.asList(0,1,2,3)) ;
		/* 0= potion de vie
		 * 1 = potion de mort
		 * 2 = aucune action
		 * 3 = potion de vie et de mort
		 * 
		 * */
	}
	
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Mort,TypeDePouvoir.Vie, TypeDePouvoir.Voyance));
	}
	
	@Override
	public void agir() {
		if((this.getStatut().aEteAttaquerParLaMeute() && this.isaUnePotionDeVie())) {
			this.potionDeVie(this);
			Logger.log(this + " s'est sauvé elle-même de l'attaque des Loups-garous grâce à sa potion de vie", TypeDeLog.role);
			this.statsSorciere.incrementerNbAutoProtection();
		}
		
		if(this.actions.size() == 1) {
			return;
		}
		if(this.action == null) {
			this.action = (int) (Math.random() * ( this.actions.size() - 0 ));
			//this.action = this.actions.get(action);
		}
		
		this.statsSorciere.incrementerNbDeuxPotionsUtiliser(this.action);
		
		if((this.isaUnePotionDeVie() && ((this.action == 0 || this.action == 3)  || (this.estAmoureux() && this.getAmoureux().getStatut().aEteAttaquerParLaMeute()))) && this.getVillage().getHabitantsEnVie().stream().anyMatch(x->x.getStatut().aEteAttaquerParLaMeute()) ) {
			Personnage personnageASauver = this.getVillage().getHabitantsEnVie().stream().filter(x->x.getStatut().aEteAttaquerParLaMeute()).findAny().get();
			this.potionDeVie(personnageASauver);
			Logger.log(this + " a sauvé " + personnageASauver +  " de l'attaque des Loups-garous grâce à sa potion de vie", TypeDeLog.role);
			
		}// potion de vie avant car elle connait l'innocent tuer par les loups-garous, ce qui est à prendre en compte pour la potion de mort
		
		
		if ((this.action == 1 || this.action == 3) && this.isaUnePotionDeMort()) {
			if(this.isaUnePotionDeVie() && this.getVillage().getHabitantsEnVie().stream().anyMatch(x->x.getStatut().aEteAttaquerParLaMeute())) {
				this.ajouterAlliés(this.getVillage().getHabitantsEnVie().stream().filter(x->x.getStatut().aEteAttaquerParLaMeute()).findFirst().get());// ne pas tuer la victime des loups-garous (innocent sure)
			}
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
		if(this.actions.contains(3)) {// au cas où même si déja fait par la potion de mort
			this.actions.remove((Object)3);
			
		}
		if(!this.getVillage().estEnVie(Salvateur.IDROLE)) {// pour la voyance du salvateur
			this.perdrePouvoir(TypeDePouvoir.Vie);
		}
		this.statsSorciere.incrementerNbUtilisationPotionDeVie();
		
	}
	
	public void potionDeMort(Personnage personnageATuer) {
		this.actions.remove(1);
		if(this.actions.contains(3)) {
			this.actions.remove((Object)3);
		}
		
		
		String messageMort;
		if(Logger.isAfficherLogDetailsRoleActionOn()) {
			messageMort = this + " a tué " + personnageATuer +  " avec sa potion de mort";
		}
		else {
			messageMort = personnageATuer +  " a été tué durant la nuit";
		}
		Logger.log(messageMort, TypeDeLog.role);
		this.tuer(personnageATuer);
		this.perdrePouvoir(TypeDePouvoir.Mort);
		this.statsSorciere.incrementerNbLoupGarouTuer(personnageATuer);
		this.statsSorciere.incrementerNbUtilisationPotionDeMort();
	}
	


	public void setAction(Integer action) {
		this.action = action;
	}
	
	


	public boolean isaUnePotionDeVie() {
		return this.getTypeDePouvoir().contains(TypeDePouvoir.Vie);
	}


	public boolean isaUnePotionDeMort() {
		return this.getTypeDePouvoir().contains(TypeDePouvoir.Mort);
	}

	@Override
	public void reset() {
		super.reset();
		this.action = null;
		this.actions = new ArrayList<Integer>(Arrays.asList(0,1,2,3));
	}
	
	public static void setStatsSorciere(StatsSorciere statsSorciere) {
		Sorciere.statsSorciere = statsSorciere;
	}

	public StatsSorciere getStatsSorciere() {
		return Sorciere.statsSorciere;
	}

	@Override
	public void agirAprèsNuit() {
		if(!this.actions.contains(0) && this.aCePouvoir(TypeDePouvoir.Vie)) {// pour le salvateur
			this.perdrePouvoir(TypeDePouvoir.Vie);
		}
		
	}
	
	@Override
	public String toString() {
		if(this.getVillage() != null && this.getVillage().getVillage().stream().anyMatch(x->x.getIdDeRole() == this.getIdDeRole() && x != this)) {
			return "la sorcière" + this.getId();
		}
		else {
			return "la sorcière";
		}
		
	}

}
