package org.apache.maven.archetypes.CapitalSperLoupGarou;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Chasseur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.junit.Before;
import org.junit.Test;


public class ChasseurTest {
	
	private Village village = new Village(0,0);
	
	private Logger log = new Logger();
	
	@Before
	public void setUp() throws Exception {
		this.log.setOffAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
	}
	

	@Test
	public void testSiJeMeureTuMeures() {// œil pour œil, dent pour dent
		System.out.println();
		System.out.println("Lancement du test SiJeMeureTuMeures");
		System.out.println();
		this.village = new Village(0, 1);
		Chasseur chasseur = new Chasseur();
		this.village.ajouterPersonnage(chasseur);
		this.village.getMeute().attaquerVillage();
		this.village.bilanTuerLaNuit();
		assertEquals(0 ,this.village.getNbPersonnageEnVie());
		
	}
	
	@Test
	public void testMeCherchePasDesNoises() {// Vaux mieux ne pas être son ennemie
		System.out.println();
		System.out.println("Lancement du test MeCherchePasDesNoises");
		System.out.println();
		LoupGarouSimple lg = new LoupGarouSimple();
		Chasseur chasseur = new Chasseur();
		this.village = new Village(99, 0);
		
		chasseur.ajouterEnnemies(lg);
		village.ajouterPersonnage(chasseur);
		village.ajouterPersonnage(lg);
		
		chasseur.meurt();
		assertFalse(lg.estEnvie());
		assertEquals(chasseur , this.village.getPersonnageParId(lg.getStatut().getTueur()));
		assertEquals(99 ,this.village.getNbVillageois());
		
	}
	
	@Test
	public void testSoisSageEtTauraPasDeProbleme() {// Mieux vaux être son amis
		System.out.println();
		System.out.println("Lancement du test SoisSageEtTauraPasDeProbleme");
		System.out.println();
		SimpleVillageois sv = new SimpleVillageois();
		LoupGarouSimple lg = new LoupGarouSimple();
		Chasseur chasseur = new Chasseur();
		this.village = new Village();
		
		chasseur.ajouterAlliés(sv);
		village.ajouterPersonnage(chasseur);
		village.ajouterPersonnage(sv);
		village.ajouterPersonnage(lg);
		
		chasseur.meurt();
		assertFalse(lg.estEnvie());
		assertEquals(chasseur , this.village.getPersonnageParId(lg.getStatut().getTueur()));
		assertEquals(1 ,this.village.getNbVillageois());
		
	}

}
