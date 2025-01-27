package org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques;


public class StatsVoleur extends Statistiques {
	private float nbLoupGarou;
	private float nbSpé;
	
	
	
	public StatsVoleur() {
		this.nbLoupGarou = 0;
		this.nbSpé = 0;
	}
	
	
	public void incrementation() {
		
	}
	
	@Override
	public String getStats() {
		String stats1 = "Sur "  ;
		String stats2 = "Sur "  ;
		return stats1 + "\n" + stats2;
	}
	
	
	
	
	
}