package org.apache.maven.archetypes.CapitalSperLoupGarou;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.MeneurDeJeu;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarous.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois.Chasseur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois.Cupidon;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois.Salvateur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois.Sorciere;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;


public class MeneurDeJeuTest {
	private SimpleVillageois simpleVillageois = new SimpleVillageois();
	private Village village = new Village(0,0);
	private double delta;
	
	private Logger log = new Logger();
	private MeneurDeJeu meneurDeJeu ;
	
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
		this.log.setOnAfficherLogDetailsPourcentage();
		
	}
	
	@Test
	public void testConditionDeFinPartie()  {
		this.village = new Village(1,1);// un vi pour un loups-garous
		this.meneurDeJeu = new MeneurDeJeu(this.village,log);
		Assert.assertTrue(this.meneurDeJeu.conditionFinPartie());
		Assert.assertTrue(this.meneurDeJeu.conditionVictoireLoupGarous());
		
		this.village = new Village(1,0);
		this.meneurDeJeu.setVillage(this.village);
		Assert.assertTrue(this.meneurDeJeu.conditionFinPartie());
		Assert.assertTrue(this.meneurDeJeu.conditionVictoireVillageois());
		
		this.meneurDeJeu.setVillage( new Village(2,1));// deux viellageois pour un loups-garous
		Assert.assertFalse(this.meneurDeJeu.conditionFinPartie());
		
		this.village = new Village(0,1);// un vi pour deux loups-garous
		this.village.ajouterPersonnage(Chasseur.IDROLE);
		this.meneurDeJeu.setVillage(this.village);
		Assert.assertTrue(this.meneurDeJeu.conditionFinPartie());
		Assert.assertTrue(this.meneurDeJeu.conditionEgaliter());
		
		this.village = new Village(1,1);// 
		this.village.ajouterPersonnage(Cupidon.IDROLE);
		this.village.getPersonnageParIdRole(Cupidon.IDROLE).tomberAmoureux((this.village.getPersonnageParIdRole(LoupGarouSimple.IDROLE)));
		this.village.getPersonnageParIdRole(LoupGarouSimple.IDROLE).tomberAmoureux((this.village.getPersonnageParIdRole(Cupidon.IDROLE)));
		this.meneurDeJeu.setVillage(this.village);
		this.village.getMeute().attaquerVillage();
		this.village.bilanTuerLaNuit();
		Assert.assertTrue(this.meneurDeJeu.conditionFinPartie());
		Assert.assertTrue(this.meneurDeJeu.conditionVictoireAmoureux());
		
		this.village = new Village(0,1);
		this.village.ajouterPersonnage(Sorciere.IDROLE);
		this.meneurDeJeu.setVillage(this.village);
		Assert.assertTrue(this.meneurDeJeu.conditionFinPartie());
		Assert.assertTrue(this.meneurDeJeu.conditionEgaliter());
		
		this.village = new Village(0,1);
		this.village.ajouterPersonnage(Salvateur.IDROLE);
		this.meneurDeJeu.setVillage(this.village);
		Assert.assertTrue(this.meneurDeJeu.conditionFinPartie());
		Assert.assertTrue(this.meneurDeJeu.conditionVictoireLoupGarous());
	}

	@Test
	public void injectionTest()  {
		this.village = new Village(1,2);
		this.village.voter('0');
		Assert.assertEquals(0, this.village.getNbVillageois());
		this.village.ajouterPersonnage(simpleVillageois);
		this.village.voter('1');
		Assert.assertEquals(1, this.village.getMeute().getMeute().size());
	}
	
	@Test
	public void simulationTest()  {
		this.village = new Village(5,1);
		this.meneurDeJeu = new MeneurDeJeu(village, log);
		this.meneurDeJeu.lancerDesParties(1);
		
		this.village = new Village(5,4);
		this.meneurDeJeu.setVillage(village);
		this.meneurDeJeu.lancerDesParties(1);
		
		this.village = new Village(0,1);
		this.village.ajouterPersonnage(Cupidon.IDROLE);
		this.meneurDeJeu.setVillage(village);
		this.meneurDeJeu.lancerDesParties(1);
	}
	
	@Test
	public void explorationTest() {
		this.village = new Village(3, 1);
		this.meneurDeJeu = new MeneurDeJeu(village, log);
		this.meneurDeJeu.exploration();
		delta = 1.0;
		Assert.assertEquals(66 , this.meneurDeJeu.getPourcentWinLoupGarous() , delta);
		Assert.assertEquals(33 , this.meneurDeJeu.getPourcentWinVillage() , delta);
		
		delta = 0.1;
		
		Assert.assertEquals(100 , (this.meneurDeJeu.getPourcentWinLoupGarous() + this.meneurDeJeu.getPourcentWinVillage() + this.meneurDeJeu.getPourcentÉgalité() ), delta);
		this.village = new Village(5, 1);
		this.meneurDeJeu = new MeneurDeJeu(village, log);
		this.meneurDeJeu.exploration();
		
		Assert.assertEquals(100 , (this.meneurDeJeu.getPourcentWinLoupGarous() + this.meneurDeJeu.getPourcentWinVillage() + this.meneurDeJeu.getPourcentÉgalité() ), delta);
		
		this.village = new Village(7, 1);
		this.meneurDeJeu = new MeneurDeJeu(village, log);
		this.meneurDeJeu.exploration();
		
		Assert.assertEquals(100 , (this.meneurDeJeu.getPourcentWinLoupGarous() + this.meneurDeJeu.getPourcentWinVillage() + this.meneurDeJeu.getPourcentÉgalité() ), delta);


		this.village = new Village(10, 1);
		this.meneurDeJeu = new MeneurDeJeu(village, log);
		this.meneurDeJeu.exploration();
		Assert.assertEquals(100 , (this.meneurDeJeu.getPourcentWinLoupGarous() + this.meneurDeJeu.getPourcentWinVillage() + this.meneurDeJeu.getPourcentÉgalité() ), delta);
		
		this.village = new Village(10, 2);
		this.meneurDeJeu = new MeneurDeJeu(village, log);
		this.meneurDeJeu.exploration();
		Assert.assertEquals(100 , (this.meneurDeJeu.getPourcentWinLoupGarous() + this.meneurDeJeu.getPourcentWinVillage() + this.meneurDeJeu.getPourcentÉgalité() ), delta);
	}
	
		

}
