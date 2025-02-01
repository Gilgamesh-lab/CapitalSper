package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsDeuxSoeurs;

public class DeuxSoeurs extends VillageoisSpecial {
	public final static int IDROLE = 8;
	public int ordreDeNaissance;
	private static StatsDeuxSoeurs statsDeuxSoeurs = new StatsDeuxSoeurs();

	public DeuxSoeurs() {
		super(IDROLE, statsDeuxSoeurs);
		this.ordreDeNaissance = 1;

	}
	
	

	public int getOrdreDeNaissance() {
		return ordreDeNaissance;
	}



	public void setOrdreDeNaissance(int ordreDeNaissance) {
		this.ordreDeNaissance = ordreDeNaissance;
	}
	
	@Override
	public int voter() {
		int persoVoter = super.voter();
		statsDeuxSoeurs.voter(this.getVillage().getPersonnageParId(persoVoter));
		return persoVoter;
	}



	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Voyance));
	}
	
	public void trouverJumelle() {
		DeuxSoeurs jumelle =  this.getVillage().initDeuxSoeurs();
		this.ajouterAllié(jumelle);
		jumelle.ajouterAllié(this);
		Logger.log("Les deux soeurs " + this + " et " + jumelle  + " se sont reconnus et peuvent ce faire confiance maintenant.", TypeDeLog.role);
	}
	
	@Override
	public void agirPremiereNuit() {
		this.trouverJumelle();
	}
	
	@Override
	public String toString() {
		if(this.getVillage() != null && this.getVillage().getVillage().stream().filter(x->x.getIdDeRole() == this.getIdDeRole() && x != this ).count() > 1) {
			return "la soeurs" + this.getId();
		}
		else {
			return "la soeurs" + ordreDeNaissance ;
		}
		
	}



	public static StatsDeuxSoeurs getStatsDeuxSoeurs() {
		return statsDeuxSoeurs;
	}



	public static void setStatsDeuxSoeurs(StatsDeuxSoeurs statsDeuxSoeurs) {
		DeuxSoeurs.statsDeuxSoeurs = statsDeuxSoeurs;
	}
	
	

}
