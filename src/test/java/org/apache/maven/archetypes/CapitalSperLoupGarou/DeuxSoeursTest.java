package org.apache.maven.archetypes.CapitalSperLoupGarou;

import static org.junit.Assert.assertEquals;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.DeuxSoeurs;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.StatsDeuxSoeurs;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class DeuxSoeursTest {
	
	private Village village = new Village(0,0);
	
	private Logger log = new Logger();
	
	@Rule
	public TestName name = new TestName();
	
	private DeuxSoeurs deuxSoeurs;
	
	private double delta = 0.0;
	
	@Before
	public void setUp() throws Exception {
		this.log.setOnAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
		this.log.setDetailVoteVillage(true);
		System.out.println("");
		System.out.println("Lancement de la méthode " + name.getMethodName());
		System.out.println("");
		this.deuxSoeurs = new DeuxSoeurs();
		DeuxSoeurs.setStatsDeuxSoeurs(new StatsDeuxSoeurs());
	}
	
	@Test
	public void testOuEstSoeurette() {// comme les cheveux d'une même mèches
		this.village.ajouterPersonnage(this.deuxSoeurs);
		assertEquals(1 ,this.village.getNbPersonnageEnVie());
		assertEquals(0 ,this.deuxSoeurs.getAlliés().size());
		deuxSoeurs.trouverJumelle();
		assertEquals(2 ,this.village.getNbPersonnageEnVie());
		assertEquals(1 ,this.deuxSoeurs.getAlliés().size());
		
		
	}
	
	@Test
	public void testEnsemble() {// unis comme deux doigts d'une même main
		this.village.ajouterPersonnage(this.deuxSoeurs);
		this.village.ajouterPersonnage(new LoupGarouSimple());
		deuxSoeurs.trouverJumelle();
		
		assertEquals(0 ,DeuxSoeurs.getStatsDeuxSoeurs().getNbVote(), delta);
		assertEquals(1 ,this.village.getNbLoupGarou());
		
		this.village.tribunal();
		
		assertEquals(0 ,this.village.getNbLoupGarou());
		assertEquals(2 ,DeuxSoeurs.getStatsDeuxSoeurs().getNbVote(), delta);
		assertEquals(2 ,DeuxSoeurs.getStatsDeuxSoeurs().getNbLgVoter(), delta);
		
		
		
		
	}

}
