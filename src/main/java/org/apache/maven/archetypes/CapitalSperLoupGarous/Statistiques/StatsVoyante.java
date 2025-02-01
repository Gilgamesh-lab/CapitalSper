package org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Personnage;

public class StatsVoyante extends Statistiques {
	private float nbDevination;
	private float nbLoupGarouTrouver;
	private float nbVote;
	private float nbLgVoter;
	
	

	public StatsVoyante() {
		this.nbDevination = 0;
		this.nbLoupGarouTrouver = 0;
		this.nbVote = 0;
		this.nbLgVoter = 0;
	}
	
	public void reset() {
		this.nbDevination = 0;
		this.nbLoupGarouTrouver = 0;
		this.nbVote = 0;
		this.nbLgVoter = 0;
	}
	
	public void voyance(Personnage personnage) {
		this.nbDevination++;
		if(!personnage.estUnVillageois()) {
			this.nbLoupGarouTrouver++;
		}
	}
	
	public void voter(Personnage personnage) {
		this.nbVote++;
		if(!personnage.estUnVillageois()) {
			this.nbLgVoter++;
		}
	}



	public float getNbDevination() {
		return nbDevination;
	}

	public float getNbLoupGarouTrouver() {
		return nbLoupGarouTrouver;
	}

	@Override
	public String getStats() {
		String stats1 = "Sur les " + (int) super.getNbToursTotale() +  " tours de jeu, la voyante a été en vie durant " + ((double) ((this.getNbDevination() / super.getNbToursTotale()) * 100)) + "% d'entre eux." ;
		String stats2 = "La voyante a effectué en moyenne " + ((double) ((this.nbDevination / super.getNbPartie()))) + " divinations par partie." ;
		String stats3 = "Sur les " + (int) this.getNbDevination() + " divinations,  la voyante a trouvé un loup-garou dans " + ((double) ((this.getNbLoupGarouTrouver()  / this.getNbDevination()) * 100 )) + "% d'entre elles.";
		String stats4 = "Sur " + (int) this.nbVote + " votes, la voyante a voter contre un loups-garous dans " + ((double) ((this.nbLgVoter / this.nbVote) * 100 )) + "% des cas." ;
		return stats1 + "\n" + stats2 + "\n" + stats3 + "\n" + stats4;
	}

}
