package org.apache.maven.archetypes.CapitalSperLoupGarou;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Chasseur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Voleur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.StatsChasseur;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class VoleurTest {
private Village village = new Village(0,0);
	
	private Logger log = new Logger();
	
	@Rule
	public TestName name = new TestName();
	
	private Voleur voleur;
	
	@Before
	public void setUp() throws Exception {
		this.log.setOnAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
		this.log.setDetailVoteVillage(true);
		System.out.println("");
		System.out.println("Lancement de la méthode " + name.getMethodName());
		System.out.println("");
		this.voleur = new Voleur();
		//Chasseur.setStatsChasseur(new StatsChasseur());
		
	}
	
	@Test
	public void canibalisme() {// œil pour œil, dent pour dent
		
		
	}

}
