package org.apache.maven.archetypes.ZeroVillage;

import java.io.IOException;

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
	
	
	
	@Before
	public void setUp() throws Exception {
		Village village = new Village(0,0);
	}

	@Test
	public void ajouterPersonnageTest()  {
		this.village.ajouterPersonnage(loupGarou);
		
		Assert.assertEquals(1, this.village.getNbLoupGarou());
		Assert.assertEquals(1 ,this.village.getNbPersonnage());
		
		this.village.ajouterPersonnage(simpleVillageois);
		Assert.assertEquals(1 , this.village.getNbLoupGarou());
		Assert.assertEquals(2, this.village.getNbPersonnage());
		
		this.village.getMeute().attaquerVillage();
		
		Assert.assertEquals(1 ,this.village.getNbLoupGarou());
		Assert.assertEquals(1 , this.village.getNbPersonnage());
		
		this.village.voter();
		
		Assert.assertEquals(0 , this.village.getNbLoupGarou());
		Assert.assertEquals(0 , this.village.getNbPersonnage() );
		
		this.village.ajouterPersonnage(simpleVillageois);
		
		Assert.assertEquals(0 , this.village.getNbLoupGarou());
		Assert.assertEquals(1 , this.village.getNbPersonnage());
		
		
	}
	
	

}
