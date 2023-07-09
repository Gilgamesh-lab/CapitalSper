package org.apache.maven.archetypes.CapitalSperLoupGarou;

import java.io.IOException;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Partie;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Cupidon;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;







public class VillageTest {
	private SimpleVillageois simpleVillageois = new SimpleVillageois();
	private LoupGarouSimple loupGarou = new LoupGarouSimple();
	private Village village = new Village(0,0);
	
	private Logger log = new Logger();
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
		Assert.assertEquals(3, this.village.getNbPersonnage());
		this.village.voter();
		Assert.assertEquals(0, this.village.getNbVillageois());
		this.village.ajouterPersonnage(simpleVillageois);
		Assert.assertEquals(this.village.getVillageois().get(0).getId(), this.village.getMeute().getMeute().get(0).voter());
		Assert.assertEquals(this.village.getVillageois().get(0).getId(), this.village.getMeute().getMeute().get(1).voter());
		this.village.getHabitants().remove(0);
		this.village.voter();
		
	}
	
	@Test
	public void cupidonTest() {
		this.log.setOnAfficherLogDetailsRoleAction();
		System.out.println();
		System.out.println("Lancement de cupidonTest");
		System.out.println();
		this.village = new Village();
		Cupidon cupidon = new Cupidon();
		this.village.ajouterPersonnage(cupidon);
		this.village.ajouterPersonnage(this.simpleVillageois);
		this.village.ajouterPersonnage(this.simpleVillageois);
		this.village.ajouterPersonnage(this.simpleVillageois);
		cupidon.flecheDeLAmour(this.village.getPersonnage(0), this.village.getPersonnage(1));
		this.village.getPersonnage(0).meurt();
		Assert.assertTrue(!this.village.getPersonnage(1).estEnvie());
		Assert.assertEquals(2, this.village.getNbPersonnage());
		
	}
	
	
	
	

}
