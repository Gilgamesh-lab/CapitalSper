package org.apache.maven.archetypes.CapitalSpéLoupGarous.StatsPersonnages;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;

public class StatsChasseur extends StatPersonnage {
	private float nbTire;
	private float nbLgTuer;
	private float nbPartie;
	
	
	
	public StatsChasseur() {
		this.nbTire = 0;
		this.nbLgTuer = 0;
		this.nbPartie = -1;// car il y aussi la première fois qu'il est crée avant le début de la partie
	}
	
	public void incrementerNbPartie() {
		this.nbPartie++;
	}

	public void tirer(Personnage personnage) {
		this.nbTire++;
		super.incrementerNbOccasionDeTuerLg();
		if(!personnage.estUnVillageois()) {
			this.nbLgTuer++;
		}
	}

	@Override
	public String getStats() {
		String stats1 = "Sur " + (int) this.nbPartie +  " parties le chasseur est mort et a pu utiliser son pouvoir dans " + ((double) ((this.nbTire / this.nbPartie) * 100 )) + "% d'entre elles" ;
		String stats2 =  "Sur " + (int) this.nbTire + " tire, le chasseur a tuer un loups-garous dans " + ((double) ((this.nbLgTuer / this.nbTire) * 100 )) + "% des cas" ;
		return stats1 + "\n" + stats2;
	}

	public float getNbTire() {
		return nbTire;
	}

	public float getNbLgTuer() {
		return nbLgTuer;
	}
	
	

}
