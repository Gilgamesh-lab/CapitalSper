package org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques;

import java.util.ArrayList;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Personnage;


public class StatsVillage extends Statistiques {
	private float nbVote;
	private float nbLoupGarouTuer;
	private float nbSurvivants;
	private float nbPersonnageTotal;
	
	

	public StatsVillage() {
		this.nbVote = 0;
		this.nbLoupGarouTuer = 0;
		this.nbSurvivants = 0;
		this.nbPersonnageTotal = 0;
	}
	
	public void reset() {
		this.nbVote = 0;
		this.nbLoupGarouTuer = 0;
		this.nbSurvivants = 0;
		this.nbPersonnageTotal = 0;
	}
	
	public void vote(Personnage personnage) {
		this.nbVote++;
		if(!personnage.estUnVillageois()) {
			this.nbLoupGarouTuer++;
		}
	}
	
	public void decompteNbSurvivants(ArrayList<Personnage> personnages) {
		this.nbSurvivants += personnages.stream().filter(x->x.estEnvie()).count();
		if(this.nbPersonnageTotal == 0) {
			this.nbPersonnageTotal = personnages.size() * super.getNbPartie();
		}
	}
	
	public float getNbVote() {
		return nbVote;
	}

	public float getNbLoupGarouTuer() {
		return nbLoupGarouTuer;
	}

	public float getNbSurvivants() {
		return nbSurvivants;
	}

	public float getNbPersonnageTotal() {
		return nbPersonnageTotal;
	}

	@Override
	public String getStats() {
		String stats1 = "Sur " + (int) super.getNbPartie() +  " parties, le village a effectuer en moyenne " + ((double) ((this.nbVote / super.getNbPartie()))) + " vote par partie" ;
		String stats2 = "Sur " + (int) this.nbVote + " votes, la personne éliminée par le village était un loup-garou dans " + ((double) ((this.nbLoupGarouTuer / this.nbVote) * 100 )) + "% des cas" ;
		String stats3 = "Sur " + (int) super.getNbPartie() + " parties, " + ((double) ((1 - (this.nbSurvivants / this.nbPersonnageTotal)) * 100 )) + "% des habitants du village ont trouvé la mort en moyenne par partie" ;
		return stats1 + "\n" + stats2 + "\n" + stats3;
	}

}
