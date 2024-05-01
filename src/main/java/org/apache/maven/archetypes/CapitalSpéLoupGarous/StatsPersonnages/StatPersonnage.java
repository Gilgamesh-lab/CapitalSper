package org.apache.maven.archetypes.CapitalSpéLoupGarous.StatsPersonnages;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.MeneurDeJeu;

public abstract class StatPersonnage {
	
	
	public StatPersonnage() {
		
	}
	
	public int getNbPartie() {
		if(MeneurDeJeu.nbPartie != null) {
			return MeneurDeJeu.nbPartie;
		}
		else {
			return 1;
		}
	}
	
	public abstract String getStats();

}
