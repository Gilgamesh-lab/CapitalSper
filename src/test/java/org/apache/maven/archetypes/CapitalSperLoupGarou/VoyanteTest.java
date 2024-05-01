package org.apache.maven.archetypes.CapitalSperLoupGarou;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Voyante;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.StatsPersonnages.StatsChasseur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.StatsPersonnages.StatsVoyante;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class VoyanteTest {

	private Village village;
	
	private Logger log = new Logger();
	
	private Voyante voyante;
	
	@Rule
	public TestName name = new TestName();
	
	@Before
	public void setUp() throws Exception {
		System.out.println("");
		System.out.println("Lancement de la méthode " + name.getMethodName());
		System.out.println("");
		this.log.setOnAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
		this.log.setDetailVoteVillage(true);
		this.village = new Village(0,0);
		this.voyante = new Voyante();
		this.voyante.setStatsVoyante(new StatsVoyante());
	}
	
	@Test
	public void testVoyanceLg() {// Je vais te dénoncer
		LoupGarouSimple lg = new LoupGarouSimple();
		this.village.ajouterPersonnage(voyante);
		this.village.ajouterPersonnage(lg);
		voyante.sonder();
		Assert.assertTrue(voyante.getEnnemies().size() == 1);
		Assert.assertTrue(voyante.getEnnemies().contains(lg));
		Assert.assertTrue(voyante.getStatsVoyante().getNbDevination() == 1);// Car Equals déprécié pour float, trouver une autre alternative
		Assert.assertTrue(voyante.getStatsVoyante().getNbLoupGarouTrouver() == 1);
	}
	
	@Test
	public void testVoyanceVi() {// Je te protégerais
		SimpleVillageois vi = new SimpleVillageois();
		this.village.ajouterPersonnage(voyante);
		this.village.ajouterPersonnage(vi);
		voyante.sonder();
		Assert.assertTrue(voyante.getEnnemies().isEmpty());
		Assert.assertEquals(1, voyante.getAlliés().size());
		Assert.assertTrue(voyante.getAlliés().contains(vi));
		Assert.assertTrue(voyante.getStatsVoyante().getNbDevination() == 1);
		Assert.assertTrue(voyante.getStatsVoyante().getNbLoupGarouTrouver() == 0);
	}
}
