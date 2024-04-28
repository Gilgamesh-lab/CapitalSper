package org.apache.maven.archetypes.CapitalSperLoupGarou;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Chasseur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;


public class ChasseurTest {
	
	private Village village = new Village(0,0);
	
	private Logger log = new Logger();
	
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
	}
	

	@Test
	public void testSiJeMeureTuMeures() {// œil pour œil, dent pour dent
		this.village = new Village(0, 1);
		Chasseur chasseur = new Chasseur();
		this.village.ajouterPersonnage(chasseur);
		this.village.getMeute().attaquerVillage();
		this.village.bilanTuerLaNuit();
		assertEquals(0 ,this.village.getNbPersonnageEnVie());
		
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
		
		chasseur.ajouterEnnemies(lg);
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
		chasseur.ajouterAlliés(sv);
		
		chasseur.meurt();
		System.out.println(this.village.getPersonnageParId(1));
		assertFalse(lg.estEnvie());
		assertEquals(chasseur , this.village.getPersonnageParIdRole(lg.getStatut().getTueur()));
		assertEquals(1 ,this.village.getNbVillageois());
		
	}

}
