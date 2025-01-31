package org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Personnage;

public class StatsDeuxSoeurs extends Statistiques {
	
	private float nbVote;
	private float nbLgVoter;
	
	public StatsDeuxSoeurs() {
		this.nbVote = 0;
		this.nbLgVoter = 0;
	}
	
	public void voter(Personnage personnage) {
		this.nbVote++;
		if(!personnage.estUnVillageois()) {
			this.nbLgVoter++;
		}
	}
	
	public void reset() {
		this.nbVote = 0;
		this.nbLgVoter = 0;
	}

	@Override
	public String getStats() {
		String stats1 = "Sur " + (int) this.nbVote + " votes, les deux soeurs ont voter contre un loups-garous dans " + ((double) ((this.nbLgVoter / this.nbVote) * 100 )) + "% des cas" ;
		return stats1 ;
	}

	public float getNbVote() {
		return nbVote;
	}

	public float getNbLgVoter() {
		return nbLgVoter;
	}
	
	

}
