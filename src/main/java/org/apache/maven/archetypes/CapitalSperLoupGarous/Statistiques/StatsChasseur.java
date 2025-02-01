package org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques;


import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Personnage;

public class StatsChasseur extends Statistiques {
	private float nbTire;
	private float nbLgTuer;
	
	
	
	public StatsChasseur() {
		this.nbTire = 0;
		this.nbLgTuer = 0;
	}
	

	public void tirer(Personnage personnage) {
		this.nbTire++;
		if(!personnage.estUnVillageois()) {
			this.nbLgTuer++;
		}
	}
	
	public void reset() {
		this.nbTire = 0;
		this.nbLgTuer = 0;
	}

	@Override
	public String getStats() {
		String stats1 = "Sur " + (int) super.getNbPartie() +  " parties, le chasseur est mort et a pu utiliser son pouvoir dans " + ((double) ((this.nbTire / super.getNbPartie()) * 100 )) + "% d'entre elles." ;
		String stats2 = "Sur " + (int) this.nbTire + " tires, le chasseur a tuer un loups-garous dans " + ((double) ((this.nbLgTuer / this.nbTire) * 100 )) + "% des cas." ;
		return stats1 + "\n" + stats2;
	}

	public float getNbTire() {
		return nbTire;
	}

	public float getNbLgTuer() {
		return nbLgTuer;
	}
	
	
	
	

}
