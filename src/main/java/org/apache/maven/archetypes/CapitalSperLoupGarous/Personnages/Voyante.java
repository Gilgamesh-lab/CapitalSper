package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Referentiel;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsChasseur;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsVoyante;

public class Voyante extends VillageoisSpecial {
	public final static int IDROLE = 4;
	private static StatsVoyante statsVoyante = new StatsVoyante();
	private Personnage dernierPersonnageSonder;

	public Voyante() {
		super(IDROLE, statsVoyante);
	}
	
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Voyance));
	}
	
	@Override
	public int voter() {
		int persoVoter = super.voter();
		statsVoyante.voter(this.getVillage().getPersonnageParId(persoVoter));
		return persoVoter;
	}
	
	public void sonder() {
		dernierPersonnageSonder = super.voteCibleAction();
		 statsVoyante.voyance(dernierPersonnageSonder);
		 if(dernierPersonnageSonder.equals(this)) {
				System.out.println("erreur vovo détecté : " + this + "s'est choisi elle-même.");
			}
		 if(dernierPersonnageSonder.estUnVillageois()) {
			 Logger.log(this + " a décidé de sonder " + dernierPersonnageSonder + " qui s'est révélé être innocent.", TypeDeLog.role);
			 //this.ajouterAlliés(dernierPersonnageSonder.getAlliés());
			 this.ajouterAllié(dernierPersonnageSonder);
			 
		 }
		 else {
			 Logger.log(this + " a décidé de sonder " + dernierPersonnageSonder + " qui s'est révélé être un ennemie du village.", TypeDeLog.role);
			 this.ajouterEnnemie(dernierPersonnageSonder);
			 
		 }
	}
	
	@Override
	public void agir() {
		this.sonder();
		
	}
	
	public static void setStatsVoyante(StatsVoyante statsVoyante) {
		Voyante.statsVoyante = statsVoyante;
	}

	public StatsVoyante getStatsVoyante() {
		return Voyante.statsVoyante;
	}
	
	/*@Override
	public void meurt() {// Lors de sa mort les villageois se rendent compte qu'il peuvent avoir confiance aux dire de la voyante
		super.meurt();// dans cet ordre pour ne pas prendre en compte l'amoureux
		if(this.getStatut().getTueur() == LoupGarouSimple.IDROLE) {// la voyante n'a pas u le temps de parler au village de sa dernière divination car elle est morte la nuit
			//System.out.println(dernierPersonnageSonder + " ," + this.getAlliés().size() + " , " + this.getEnnemies().size()+ " , " + Référentiel.conversionDeIdRoleVersPersonnage(this.getStatut().getTueur()));
			if(dernierPersonnageSonder.estUnVillageois()) {
				this.getAlliés().remove(dernierPersonnageSonder);
			 }
			 else {
				 this.getEnnemies().remove(dernierPersonnageSonder);
			 }
			
		}
		this.getVillage().getVillageois().stream().forEach(x-> {x.ajouterAlliés(this.getAlliés()); x.ajouterEnnemies(this.getEnnemies());});	
	}*/

	@Override
	public String toString() {
		if(this.getVillage() != null && this.getVillage().getVillage().stream().anyMatch(x->x.getIdDeRole() == this.getIdDeRole() && x != this)) {
			return "la voyante" + this.getId();
		}
		else {
			return "la voyante";
		}
		
	}
	

}
