package org.apache.maven.archetypes.CapitalSperLoupGarou;

import java.io.IOException;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Partie;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Cupidon;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;







public class VillageTest {
	private SimpleVillageois simpleVillageois = new SimpleVillageois();
	private LoupGarouSimple loupGarou = new LoupGarouSimple();
	private Village village = new Village(0,0);
	
	private Logger log = new Logger();
	//log.setDetailVoteVillage(true);
	private Partie partie = new Partie(village, log);
	
	@Rule
	public TestName name = new TestName();
	
	
	@Before
	public void setUp() throws Exception {
		this.log.setOnAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
		this.log.setDetailVoteVillage(true);
		System.out.println("");
		System.out.println("Lancement de la méthode " + name.getMethodName());
		System.out.println("");
		Village village = new Village(0,0);
	}

	@Test
	public void ajouterPersonnageTest()  {
		this.village.ajouterPersonnage(loupGarou);
		
		Assert.assertEquals(1, this.village.getNbLoupGarou());
		Assert.assertEquals(0, this.village.getNbVillageois());
		Assert.assertEquals(1 ,this.village.getNbPersonnageEnVie());
		
		this.village.ajouterPersonnage(simpleVillageois);
		
		Assert.assertEquals(1 , this.village.getNbLoupGarou());
		Assert.assertEquals(1, this.village.getNbVillageois());
		Assert.assertEquals(2, this.village.getNbPersonnageEnVie());
		
		
	}
	
	@Test
	public void voterTest()  {
		this.village = new Village(1,2);
		Assert.assertEquals(1, this.village.getNbVillageois());
		Assert.assertEquals(3, this.village.getNbPersonnageEnVie());
		this.village.tribunal();
		Assert.assertEquals(0, this.village.getNbVillageois());
		this.village.ajouterPersonnage(simpleVillageois);
		Assert.assertEquals(this.village.getVillageois().get(0).getId(), this.village.getMeute().getMeute().get(0).voter());
		Assert.assertEquals(this.village.getVillageois().get(0).getId(), this.village.getMeute().getMeute().get(1).voter());
		this.village.getHabitantsEnVie().remove(0);
		this.village.tribunal();
		
	}
	
	
	
	
	
	

}
