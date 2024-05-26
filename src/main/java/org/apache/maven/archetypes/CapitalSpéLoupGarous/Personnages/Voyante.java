package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.StatsChasseur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.StatsVoyante;

public class Voyante extends VillageoisSpecial {
	public final static int IDROLE = 4;
	private static StatsVoyante statsVoyante = new StatsVoyante();

	public Voyante() {
		super(IDROLE, statsVoyante);
	}
	
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Voyance));
	}
	
	public void sonder() {
		 Personnage persoASonder = super.voteCibleAction();
		 statsVoyante.voyance(persoASonder);
		 if(persoASonder.equals(this)) {
				System.out.println("erreur vovo détecté : " + this + "s'est choisi elle-même");
			}
		 if(persoASonder.estUnVillageois()) {
			 Logger.log(this + " a décidé de sonder " + persoASonder + " qui s'est révélé être innocent", TypeDeLog.role);
			 this.ajouterAlliés(persoASonder);
			 
		 }
		 else {
			 Logger.log(this + " a décidé de sonder " + persoASonder + " qui s'est révélé être un ennemie du village", TypeDeLog.role);
			 this.ajouterEnnemies(persoASonder);
			 
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
