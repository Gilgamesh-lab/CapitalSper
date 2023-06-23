package org.apache.maven.archetypes.CapitalSperLoupGarou;

import java.io.IOException;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Log;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Partie;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;







public class VillageTest {
	private SimpleVillageois simpleVillageois = new SimpleVillageois();
	private LoupGarouSimple loupGarou = new LoupGarouSimple();
	private Village village = new Village(0,0);
	
	private Log log = new Log();
	//log.setDetailVoteVillage(true);
	private Partie partie = new Partie(village, log);
	
	
	@Before
	public void setUp() throws Exception {
		Village village = new Village(0,0);
	}

	@Test
	public void ajouterPersonnageTest()  {
		System.out.println();
		System.out.println("Lancement de ajouterPersonnageTest");
		System.out.println();
		
		this.village.ajouterPersonnage(loupGarou);
		
		Assert.assertEquals(1, this.village.getNbLoupGarou());
		Assert.assertEquals(1 ,this.village.getNbPersonnage());
		
		this.village.ajouterPersonnage(simpleVillageois);
		
		Assert.assertEquals(1 , this.village.getNbLoupGarou());
		Assert.assertEquals(2, this.village.getNbPersonnage());
		
		this.village.getMeute().attaquerVillage();
		
		Assert.assertEquals(1 ,this.village.getNbLoupGarou());
		Assert.assertEquals(1 , this.village.getNbPersonnage());
		
		/*this.village.ajouterPersonnage(simpleVillageois);
		this.village.voter();
		
		Assert.assertEquals(0 , this.village.getNbLoupGarou());
		Assert.assertEquals(0 , this.village.getNbPersonnage() );
		
		this.village.ajouterPersonnage(simpleVillageois);
		
		Assert.assertEquals(0 , this.village.getNbLoupGarou());
		Assert.assertEquals(1 , this.village.getNbPersonnage());*/
		
		
	}
	
	@Test
	public void voterTest()  {
		System.out.println();
		System.out.println("Lancement de voterTest");
		System.out.println();
		this.village = new Village(1,2);
		Assert.assertEquals(1, this.village.getNbVillageois());
		Assert.assertEquals(1, this.village.getNbVillageois());
		Assert.assertEquals(3, this.village.getNbPersonnage());
		this.village.voter();
		Assert.assertEquals(0, this.village.getNbVillageois());
		this.village.ajouterPersonnage(simpleVillageois);
		Assert.assertEquals(this.village.getVillageois().get(0).getId(), this.village.getMeute().getMeute().get(0).voter());
		Assert.assertEquals(this.village.getVillageois().get(0).getId(), this.village.getMeute().getMeute().get(1).voter());
		this.village.getVillage().remove(0);
		this.village.voter();
		
	}
	
	@Test
	public void explorationTest()  {
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
	
	

}
