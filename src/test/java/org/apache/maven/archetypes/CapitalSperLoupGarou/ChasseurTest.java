package org.apache.maven.archetypes.CapitalSperLoupGarou;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Chasseur;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsChasseur;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;


public class ChasseurTest {
	
	private Village village = new Village(0,0);
	
	private Logger log = new Logger();
	
	@Rule
	public TestName name = new TestName();
	
	private Chasseur chasseur;
	
	@Before
	public void setUp() throws Exception {
		this.log.setOnAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
		this.log.setDetailVoteVillage(true);
		System.out.println("");
		System.out.println("Lancement de la méthode " + name.getMethodName());
		System.out.println("");
		this.chasseur = new Chasseur();
		Chasseur.setStatsChasseur(new StatsChasseur());
	}
	

	@Test
	public void testSiJeMeureTuMeures() {// œil pour œil, dent pour dent
		this.village = new Village(0, 1);
		this.village.ajouterPersonnage(chasseur);
		this.village.getMeute().attaquerVillage();
		this.village.bilanTuerLaNuit();
		assertEquals(0 ,this.village.getNbPersonnageEnVie());
		assertTrue(this.chasseur.getStatsChasseur().getNbTire() == 1);
		assertTrue(this.chasseur.getStatsChasseur().getNbLgTuer() == 1);
		
		
	}
	
	@Test
	public void testLaBatailleEstFinie() {// Si il n'y a plus de loups-garous à traquer
		this.village = new Village(1, 0);
		Chasseur chasseur = new Chasseur();
		this.village.ajouterPersonnage(chasseur);
		chasseur.meurt();
		assertEquals(1 ,this.village.getNbPersonnageEnVie());
		assertEquals(1, this.village.getNbVillageois());
		
	}
	
	@Test
	public void testMeCherchePasDesNoises() {// Vaux mieux ne pas être son ennemie
		LoupGarouSimple lg = new LoupGarouSimple();
		Chasseur chasseur = new Chasseur();
		this.village = new Village(99, 0);
		
		chasseur.ajouterEnnemie(lg);
		village.ajouterPersonnage(chasseur);
		village.ajouterPersonnage(lg);
		
		chasseur.meurt();
		assertFalse(lg.estEnvie());
		assertEquals(chasseur , this.village.getPersonnageParIdRole(lg.getStatut().getTueur()));
		assertEquals(99 ,this.village.getNbVillageois());
		
	}
	
	@Test
	public void testSoisSageEtTauraPasDeProbleme() {// Mieux vaux être son amis
		SimpleVillageois sv = new SimpleVillageois();
		LoupGarouSimple lg = new LoupGarouSimple();
		Chasseur chasseur = new Chasseur();
		this.village = new Village();
		
		
		village.ajouterPersonnage(chasseur);
		village.ajouterPersonnage(sv);
		village.ajouterPersonnage(lg);
		chasseur.ajouterAllié(sv);
		
		chasseur.meurt();
		System.out.println(this.village.getPersonnageParId(1));
		assertFalse(lg.estEnvie());
		assertEquals(chasseur , this.village.getPersonnageParIdRole(lg.getStatut().getTueur()));
		assertEquals(1 ,this.village.getNbVillageois());
		
	}

}
