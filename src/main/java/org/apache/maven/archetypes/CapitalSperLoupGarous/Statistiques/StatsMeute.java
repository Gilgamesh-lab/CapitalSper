package org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques;

import java.util.ArrayList;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois.SimpleVillageois;

public class StatsMeute extends Statistiques {
	private float nbVote;
	private float nbSperVoter;
	private float nbSurvivants;
	private Integer dernierePersonneDevorer;
	
	

	public StatsMeute() {
		this.nbVote = 0;
		this.nbSperVoter = 0;
		this.nbSurvivants = 0;
		this.dernierePersonneDevorer = null;
	}
	
	public void enregistrer(Personnage personnage){
		this.dernierePersonneDevorer = personnage.getId();
	}
	
	
	public void vote(Personnage personnage) {
		this.nbVote++;
		this.enregistrer(personnage);
		if(personnage.getIdDeRole() != SimpleVillageois.IDROLE) {
			this.nbSperVoter++;
		}
		
	}
	
	public void ASurvecu(ArrayList<Personnage> village) {
		if(dernierePersonneDevorer != null && village.stream().filter(x->x.getId() == dernierePersonneDevorer).anyMatch(x->x.estEnvie())) {
			this.nbSurvivants++;
			/*if(village.stream().filter(x->x.getId() == dernierePersonneDevorer).anyMatch(x->x.getIdDeRole() != SimpleVillageois.IDROLE) ) {
				this.nbSperVoter--;
			}*/
		}
		this.dernierePersonneDevorer = null;
		
	}
	
	public float getNbVote() {
		return nbVote;
	}

	public float getNbSperVoter() {
		return nbSperVoter;
	}

	public float getNbSurvivants() {
		return nbSurvivants;
	}
	

	public Integer getDernierePersonneDevorer() {
		return dernierePersonneDevorer;
	}
	
	public void incrementerNbSurvivants() {
		 this.nbVote++;
		 this.nbSurvivants++;
	}

	@Override
	public String getStats() {
		String stats1 = "Sur " + (int) super.getNbPartie() +  " parties, la meute a effectuer en moyenne " + ((double) ((this.nbVote / super.getNbPartie()))) + " attaque nocturne par partie" ;
		String stats2 = "Sur " + (int) this.nbVote + " votes, la personne choisie par la meute était un personnage spécial dans " + ((double) ((this.nbSperVoter / this.nbVote) * 100 )) + "% des cas" ;
		String stats3 = "Sur " + (int) this.nbVote + " attaques noctures, dans " + ((double) (((this.nbSurvivants / this.nbVote)) * 100 )) + "% des cas la victime a été sauvé par un rôle de protection" ;
		return stats1 + "\n" + stats2 + "\n" + stats3;
	}

}
