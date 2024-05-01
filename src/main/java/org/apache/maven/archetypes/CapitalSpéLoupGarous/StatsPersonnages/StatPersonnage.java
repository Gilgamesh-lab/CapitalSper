package org.apache.maven.archetypes.CapitalSpéLoupGarous.StatsPersonnages;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Partie;

public abstract class StatPersonnage {
	
	
	public StatPersonnage() {
		
	}
	
	public int getNbPartie() {
		if(Partie.nbPartie != null) {
			return Partie.nbPartie;
		}
		else {
			return 1;
		}
	}
	
	public abstract String getStats();

}
