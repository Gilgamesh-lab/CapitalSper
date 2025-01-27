package org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;

public class StatsVoleur extends Statistiques {
	private float nbLoupGarou;
	private float nbVillageoisSpecial;
	private float fullSpe;
	private float fullLoupGarou;
	private float fullSimpleVillageois;
	
	
	
	public StatsVoleur() {
		this.nbLoupGarou = 0;
		this.nbVillageoisSpecial = 0;
		this.fullSpe = 0;
		this.fullLoupGarou = 0;
		this.fullSimpleVillageois = 0;
	}
	
	public void initPartie(Personnage perso1, Personnage perso2) {
		if(perso1.estUnVillageois() && perso2.estUnVillageois()) {
			if(perso1.aUnPouvoirSpecial() && perso2.aUnPouvoirSpecial()) {
				this.fullSpe++;
			}
			else if (!perso1.aUnPouvoirSpecial() && !perso2.aUnPouvoirSpecial()) {
				this.fullSimpleVillageois++;
			}
		}
		else if (!perso1.estUnVillageois() && !perso2.estUnVillageois()) {
			this.fullLoupGarou++;
		}
	}
	
	public void voler(Personnage personnageChoisie) {
		if(personnageChoisie.estUnVillageois() && personnageChoisie.aUnPouvoirSpecial()) {
			this.nbVillageoisSpecial++;
		}
		else if (!personnageChoisie.estUnVillageois() )  {
			this.nbLoupGarou++;
		}
	}
	
	@Override
	public String getStats() {
		//String stats6 = "Sur " + (int) super.getNbPartie() +  " parties, le chasseur est devenue un villageois dans " + ((double) (((  this.nbLoupGarou / super.getNbPartie() ) - 1) / super.getNbPartie() * 100 ) ) + "% d'entre elles" ;
		String stats1 = "Sur " + (int) super.getNbPartie() +  " parties, le chasseur est devenue un villageois spécial dans " + ((double) ((this.nbVillageoisSpecial / super.getNbPartie()) * 100 )) + "% d'entre elles" ;
		String stats2 = "Sur " + (int) super.getNbPartie() +  " parties, le chasseur est devenue un loup garou dans " + ((double) ((this.nbLoupGarou / super.getNbPartie()) * 100 )) + "% d'entre elles" ;
		
		String stats3 = "Sur " + (int) super.getNbPartie() +  " parties, les cartes proposés aux voleur étaient que des villagois spécial dans " + ((double) ((this.fullSpe / super.getNbPartie()) * 100 )) + "% des cas" ;
		String stats4 = "Sur " + (int) super.getNbPartie() +  " parties, les cartes proposés aux voleur étaient que des simples villageois dans " + ((double) ((this.fullSimpleVillageois / super.getNbPartie()) * 100 )) + "% des cas" ;
		String stats5 = "Sur " + (int) super.getNbPartie() +  " parties, les cartes proposés aux voleur étaient que des loups-garous dans " + ((double) ((this.fullLoupGarou / super.getNbPartie()) * 100 )) + "% des cas" ;
		return stats1 + "\n" + stats2 + "\n" + stats3 + "\n" + stats4 + "\n" + stats5; // + "\n" + stats6;
	}
	
	
	
	
	
}