package org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.MeneurDeJeu;

public abstract class Statistiques {
	
	
	public Statistiques() {
		
	}
	
	public int getNbPartie() {
		if(MeneurDeJeu.nbPartie != null) {
			return MeneurDeJeu.nbPartie;
		}
		else {
			return 1;
		}
	}
	
	public int getNbToursTotale() {
		if(MeneurDeJeu.nbTourTotale != null) {
			return MeneurDeJeu.nbTourTotale;
		}
		else {
			return 1;
		}
	}
	
	public abstract String getStats();

}
