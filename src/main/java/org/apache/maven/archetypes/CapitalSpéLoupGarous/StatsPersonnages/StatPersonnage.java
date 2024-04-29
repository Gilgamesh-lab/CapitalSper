package org.apache.maven.archetypes.CapitalSpéLoupGarous.StatsPersonnages;

public abstract class StatPersonnage {
	
	private static int nbOccasionDeTuerLg = 0;
	
	public StatPersonnage() {
		
	}
	
	public static void incrementerNbOccasionDeTuerLg() {
		nbOccasionDeTuerLg++;
	}
	
	public static int getNbOccasionDeTuerLg() {
		return nbOccasionDeTuerLg;
	}
	
	public abstract String getStats();

}
