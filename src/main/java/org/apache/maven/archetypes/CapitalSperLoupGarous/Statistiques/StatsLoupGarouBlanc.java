package org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques;


public class StatsLoupGarouBlanc extends Statistiques {
	private float nbLgTUer;
	private float NbVictoireLoupGarouEnVie;
	
	
	
	public StatsLoupGarouBlanc() {
		this.nbLgTUer = 0;
		this.NbVictoireLoupGarouEnVie = 0;
	}
	
	
	public void incrementation() {
		
	}
	
	@Override
	public String getStats() {
		String stats1 = "Sur "  ;
		String stats2 = "Sur "  ;
		return "";//stats1 + "\n" + stats2;
	}


	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
