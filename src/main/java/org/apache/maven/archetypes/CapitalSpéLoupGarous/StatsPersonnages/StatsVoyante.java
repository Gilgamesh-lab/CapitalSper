package org.apache.maven.archetypes.CapitalSpéLoupGarous.StatsPersonnages;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;

public class StatsVoyante extends StatPersonnage {
	private float nbDevination;
	private float nbLoupGarouTrouver;
	private float nbPartie;
	
	

	public StatsVoyante() {
		this.nbDevination = 0;
		this.nbLoupGarouTrouver = 0;
		this.nbPartie = -1;
	}
	
	public void voyance(Personnage personnage) {
		this.nbDevination++;
		if(!personnage.estUnVillageois()) {
			this.nbLoupGarouTrouver++;
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
		String stats1 = "Sur " + (int) super.getNbPartie() +  " parties, la voyante a effectué en moyenne " + ((double) ((this.nbDevination / super.getNbPartie()))) + " par partie" ;
		String stats2 = "Sur " + (int) this.getNbDevination() + " devination,  la voyante a trouvé un loup-garou dans " + ((double) ((this.getNbLoupGarouTrouver()  / this.getNbDevination()) * 100 )) + "% des cas";
		return stats1 + "\n" + stats2;
	}

}
