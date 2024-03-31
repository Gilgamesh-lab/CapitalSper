package org.apache.maven.archetypes.CapitalSperLoupGarou;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Sorcière;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SorciereTest {

	private Village village;
	
	private Logger log = new Logger();
	
	private Sorcière sorcière;
	
	@Before
	public void setUp() throws Exception {
		this.log.setOnAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
		this.log.setDetailVoteVillage(true);
		this.village = new Village(1,1);
		sorcière = new Sorcière();
	}
	
	@Test
	public void testPotionDeVie() {// La mort n'est qu'un état temporaire
		System.out.println();
		System.out.println("Lancement du test PotionDeVie");
		System.out.println();
		this.village = new Village(0,0);
		SimpleVillageois vi = new SimpleVillageois();
		LoupGarouSimple lg = new LoupGarouSimple();
		lg.ajouterEnnemies(vi);
		this.village.ajouterPersonnage(sorcière);
		this.village.ajouterPersonnage(vi);
		this.village.ajouterPersonnage(lg);
		this.village.getMeute().attaquerVillage();
		System.out.println(this.village.getHabitantsEnVie());
		sorcière.setAction(0);
		sorcière.agir();
		this.village.bilanTuerLaNuit();
		Assert.assertTrue(sorcière.getAlliés().size() == 2);
		Assert.assertEquals(3, this.village.getHabitantsEnVie().size());
		Assert.assertFalse(this.sorcière.isaUnePotionDeVie());
		Assert.assertTrue(this.sorcière.isaUnePotionDeMort());
	}
	
	@Test
	public void testMoiDAbord() {// Ad Vitam Aeternam
		System.out.println();
		System.out.println("Lancement du test MoiDAbord");
		System.out.println();
		this.village = new Village(20,0);
		LoupGarouSimple lg = new LoupGarouSimple();
		lg.ajouterEnnemies(sorcière);
		this.village.ajouterPersonnage(lg);
		this.village.ajouterPersonnage(sorcière);
		this.village.getMeute().attaquerVillage();
		sorcière.setAction(2);
		sorcière.agir();
		this.village.bilanTuerLaNuit();
		Assert.assertTrue(sorcière.estEnvie());
		Assert.assertTrue(this.village.getNbPersonnageEnVie() == 22);
		Assert.assertFalse(this.sorcière.isaUnePotionDeVie());
		Assert.assertTrue(this.sorcière.isaUnePotionDeMort());
	}
	
	@Test
	public void testPotionDeMort() {// Il est temps de payer
		System.out.println();
		System.out.println("Lancement du test PotionDeMort");
		System.out.println();
		this.village = new Village(20,0);
		LoupGarouSimple lg = new LoupGarouSimple();
		sorcière.ajouterEnnemies(lg);
		this.village.ajouterPersonnage(lg);
		this.village.ajouterPersonnage(sorcière);
		this.village.getMeute().attaquerVillage();
		sorcière.setAction(1);
		sorcière.agir();
		this.village.bilanTuerLaNuit();
		Assert.assertFalse(lg.estEnvie());
		Assert.assertFalse(this.sorcière.isaUnePotionDeMort());
		Assert.assertTrue(this.sorcière.isaUnePotionDeVie());
	}
	
	@Test
	public void testDeuxPotionsEnUneNuit() {// Je suis toute puisante
		System.out.println();
		System.out.println("Lancement du test DeuxPotionsEnUneNuit");
		System.out.println();
		this.village = new Village(20,0);
		LoupGarouSimple lg = new LoupGarouSimple();
		sorcière.ajouterEnnemies(lg);
		this.village.ajouterPersonnage(lg);
		this.village.ajouterPersonnage(sorcière);
		this.village.getMeute().attaquerVillage();
		sorcière.setAction(3);
		sorcière.agir();
		this.village.bilanTuerLaNuit();
		Assert.assertFalse(lg.estEnvie());
		Assert.assertTrue(this.village.getNbVillageois() == 21);
		Assert.assertFalse(this.sorcière.isaUnePotionDeVie());
		Assert.assertFalse(this.sorcière.isaUnePotionDeMort());
	}

}
