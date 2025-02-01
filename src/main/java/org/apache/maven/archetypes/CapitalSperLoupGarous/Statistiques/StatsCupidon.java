package org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Personnage;

public class StatsCupidon extends Statistiques{
	
	private float nbCoupleVillageois;
	private float nbCoupleLoupGarou;
	private float nbCoupleTraitre;
	private float nbDeMortCouple;
	
	

	public StatsCupidon() {
		this.nbCoupleVillageois = 0;
		this.nbCoupleLoupGarou = 0;
		this.nbCoupleTraitre = 0;
		this.nbDeMortCouple = 0;
	}
	
	public void reset() {
		this.nbCoupleVillageois = 0;
		this.nbCoupleLoupGarou = 0;
		this.nbCoupleTraitre = 0;
		this.nbDeMortCouple = 0;
	}
	
	public void amoureux(Personnage amoureux1, Personnage amoureux2) {
		if(amoureux1.estUnVillageois() == amoureux2.estUnVillageois()) {
			if(amoureux1.estUnVillageois()) {
				this.nbCoupleVillageois++;
			}
			else {
				this.nbCoupleLoupGarou++;
			}
		}
		else {
			this.nbCoupleTraitre++;
		}
	}
	
	public void ASurvecuCoupleNonTraitre() {
		this.nbDeMortCouple++;
	}
	
	public float getNbCoupleVillageois() {
		return nbCoupleVillageois;
	}

	public float getNbCoupleLoupGarou() {
		return nbCoupleLoupGarou;
	}

	public float getNbCoupleTraitre() {
		return nbCoupleTraitre;
	}

	@Override
	public String getStats() {
		String stats1 = "Sur " + (int) super.getNbPartie() +  " parties, le couple etait composés de deux villageois dans " + ((double) ((this.nbCoupleVillageois / super.getNbPartie()) * 100)) + "% des cas.";
		String stats2 = "Sur " + (int) super.getNbPartie() +  " parties, le couple etait composés de deux loups-garous dans " + ((double) ((this.nbCoupleLoupGarou / super.getNbPartie()) * 100)) + "% des cas.";
		String stats3 = "Sur " + (int) super.getNbPartie() +  " parties, le couple etait un couple traitre " + ((double) ((this.nbCoupleTraitre / super.getNbPartie()) * 100)) + "% des cas.";
		String stats4 = "Sur " + (int) super.getNbPartie() +  " parties, un couple non traitre a survécu dans " + ((double) ((1 - (this.nbDeMortCouple / super.getNbPartie())) * 100)) + "% des cas.";
		return stats1 + "\n" + stats2 + "\n" + stats3 + "\n" + stats4;
	}

}
