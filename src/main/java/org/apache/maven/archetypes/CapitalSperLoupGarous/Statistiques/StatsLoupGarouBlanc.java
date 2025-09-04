package org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques;


import org.apache.maven.archetypes.CapitalSperLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.LoupGarouBlanc;

public class StatsLoupGarouBlanc extends Statistiques {
	private float nbLgTUer;
	private float NbVictoireLoupGarouEnEtantEnVie;
	private float nbFoisReveiller;
	private float nbVictoireLoupGarou;
	
	
	
	public StatsLoupGarouBlanc() {
		this.nbLgTUer = 0;
		this.NbVictoireLoupGarouEnEtantEnVie = 0;
		this.nbFoisReveiller = 0;
		this.nbVictoireLoupGarou = 0;
	}
	
	public float getNbLgTUer() {
		return nbLgTUer;
	}

	public void incrementerNbLgTUer() {
		this.nbLgTUer++;
	}

	public float getNbVictoireLoupGarouEnEtantEnVie() {
		return NbVictoireLoupGarouEnEtantEnVie;
	}


	public void incrementerNbVictoireLoupGarouEnETantEnVie(Village village) {
		this.nbVictoireLoupGarou++;
		if(village.estEnVie(LoupGarouBlanc.IDROLE)) {
			NbVictoireLoupGarouEnEtantEnVie++;
			
		}
	}
	
	public float getNbVictoireLoupGarou() {
		return nbVictoireLoupGarou;
	}

	public float getNbFoisReveiller() {
		return nbFoisReveiller;
	}

	public void incrementerNbFoisReveiller() {
		this.nbFoisReveiller++;
	}

	@Override
	public String getStats() {
		String stats1 = "Sur " + (int) super.getNbPartie() + " parties, le loup-garou blanc a tuer en moyenne " + ((double) (this.getNbLgTUer() / super.getNbPartie())) + " loup-garou par partie.";
		String stats2 = "Sur les " + (int) this.getNbVictoireLoupGarou() +  " victoire des loups-garous, le loup-garou blanc étaient encore en vie dans " + ((double) ((this.getNbVictoireLoupGarouEnEtantEnVie() / this.getNbVictoireLoupGarou()) * 100)) + "% d'entre elles.";
		String stats3 = "Sur les " + (int) super.getNbToursTotale() +  " nuit du jeu, le loup-garou blanc s'est réveillé dans " + ((double) ((this.getNbFoisReveiller() / super.getNbToursTotale()) * 100)) + "% d'entre elles." ;
		String stats4 = "Sur les " + (int) this.getNbFoisReveiller() +  " nuit du jeu où il s'est réveillé, le loup-garou blanc a décidé de tuer un autre loup-garou dans " + ((double) ((this.getNbLgTUer() / this.getNbFoisReveiller()) * 100)) + "% des cas." ;
		return stats1 + "\n" + stats2 + "\n" + stats3 + "\n" + stats4;
	}


	@Override
	public void reset() {
		this.nbLgTUer = 0;
		this.NbVictoireLoupGarouEnEtantEnVie = 0;
		this.nbFoisReveiller = 0;
		this.nbVictoireLoupGarou = 0;
		
	}
	
	
	
	
	
}
