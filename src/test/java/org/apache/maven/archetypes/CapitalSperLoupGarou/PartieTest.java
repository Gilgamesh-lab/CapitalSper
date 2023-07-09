package org.apache.maven.archetypes.CapitalSperLoupGarou;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Partie;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PartieTest {
	private SimpleVillageois simpleVillageois = new SimpleVillageois();
	private LoupGarouSimple loupGarou = new LoupGarouSimple();
	private Village village = new Village(0,0);
	private double delta;
	
	private Logger log = new Logger();
	//log.setDetailVoteVillage(true);
	private Partie partie ;
	
	
	@Before
	public void setUp() throws Exception {
		Village village = new Village(0,0);
	}
	
	@Test
	public void startExplorationTest()  {
		System.out.println();
		System.out.println("Lancement d'explorationTest");
		System.out.println();
		this.village = new Village(1,2);
		this.village.voter('0');
		Assert.assertEquals(0, this.village.getNbVillageois());
		this.village.ajouterPersonnage(simpleVillageois);
		this.village.voter('1');
		Assert.assertEquals(1, this.village.getMeute().getMeute().size());
	}
	
	@Test
	public void explorationTest() {
		this.village = new Village(3, 1);
		this.partie = new Partie(village, log);
		this.partie.exploration();
		delta = 1.0;
		Assert.assertEquals(66 , this.partie.getPourcentWinLoupGarous() , delta);
		Assert.assertEquals(33 , this.partie.getPourcentWinVillage() , delta);
		
		delta = 0.1;
		
		Assert.assertEquals(100 , (this.partie.getPourcentWinLoupGarous() + this.partie.getPourcentWinVillage() + this.partie.getPourcentÉgalité() ), delta);
		this.village = new Village(5, 1);
		this.partie = new Partie(village, log);
		this.partie.exploration();
		
		Assert.assertEquals(100 , (this.partie.getPourcentWinLoupGarous() + this.partie.getPourcentWinVillage() + this.partie.getPourcentÉgalité() ), delta);
		
		this.village = new Village(7, 1);
		this.partie = new Partie(village, log);
		this.partie.exploration();
		
		Assert.assertEquals(100 , (this.partie.getPourcentWinLoupGarous() + this.partie.getPourcentWinVillage() + this.partie.getPourcentÉgalité() ), delta);


		this.village = new Village(10, 1);
		this.partie = new Partie(village, log);
		this.partie.exploration();
		Assert.assertEquals(100 , (this.partie.getPourcentWinLoupGarous() + this.partie.getPourcentWinVillage() + this.partie.getPourcentÉgalité() ), delta);
		
		this.village = new Village(10, 2);
		this.partie = new Partie(village, log);
		this.partie.exploration();
		Assert.assertEquals(100 , (this.partie.getPourcentWinLoupGarous() + this.partie.getPourcentWinVillage() + this.partie.getPourcentÉgalité() ), delta);
	}

}