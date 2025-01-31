package org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;

public class StatsSorciere extends Statistiques  {
	private float nbPotionDeMort;
	private float nbPotionDeVie;
	private float nbDeuxPotion;
	private float nbLoupGarouTuer;
	private float nbAutoProtection;
	
	
	

	public StatsSorciere() {
		this.nbPotionDeMort = 0;
		this.nbPotionDeVie = 0;
		this.nbDeuxPotion = 0;
		this.nbLoupGarouTuer = 0;
		this.nbAutoProtection = 0;
	}
	
	public void reset(){
		this.nbPotionDeMort = 0;
		this.nbPotionDeVie = 0;
		this.nbDeuxPotion = 0;
		this.nbLoupGarouTuer = 0;
		this.nbAutoProtection = 0;
	}
	
	public void incrementerNbUtilisationPotionDeMort() {
		this.nbPotionDeMort++;
	}
	
	public void incrementerNbUtilisationPotionDeVie() {
		this.nbPotionDeVie++;
	}
	
	public void incrementerNbDeuxPotionsUtiliser(int action) {
		if(action == 3) {
			this.nbDeuxPotion++;
		}
	}
	
	public void incrementerNbLoupGarouTuer(Personnage personnage) {
		if(!personnage.estUnVillageois()) {
			this.nbLoupGarouTuer++;
		}
		
	}
	
	public void incrementerNbAutoProtection() {
		this.nbAutoProtection++;
	}




	public float getNbPotionDeMort() {
		return nbPotionDeMort;
	}

	public float getNbPotionDeVie() {
		return nbPotionDeVie;
	}

	public float getNbDeuxPotion() {
		return nbDeuxPotion;
	}

	public float getNbLoupGarouTuer() {
		return nbLoupGarouTuer;
	}

	public float getNbAutoProtection() {
		return nbAutoProtection;
	}

	@Override
	public String getStats() {
		String stats1 = "Sur " + (int) super.getNbPartie() +  " parties, la sorcière a utiliser une potion de mort dans " + ((double) ((this.nbPotionDeMort / super.getNbPartie()) * 100 )) + "% d'entre elles" ;
		String stats2 = "Sur " + (int) this.nbPotionDeMort + " potions de mort utilisé, la sorcière a tuer un loups-garous dans " + ((double) ((this.nbLoupGarouTuer / this.nbPotionDeMort) * 100 )) + "% des cas";
		String stats3 = "Sur " + (int) super.getNbPartie() +  " parties, la sorcière a utiliser une potion de vie dans " + ((double) ((this.nbPotionDeVie / super.getNbPartie()) * 100 )) + "% d'entre elles" ;
		String stats4 = "Sur " + (int) this.nbPotionDeVie + " potions de vie utilisé, la sorcière c'est sauvé elle-même dans " + ((double) ((this.nbAutoProtection / this.nbPotionDeVie) * 100 )) + "% des cas" ;
		String stats5 = "Sur " + (int) super.getNbPartie() +  " parties, la sorcière a utilisé ses deux potions en même temps dans " + ((double) ((this.nbDeuxPotion / super.getNbPartie()) * 100 )) + "% d'entre elles" ;
		return stats1 + "\n" + stats2 + "\n" + stats3 + "\n" + stats4 + "\n" + stats5;
	}

}
