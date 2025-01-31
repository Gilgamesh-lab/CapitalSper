package org.apache.maven.archetypes.CapitalSperLoupGarou;


import static org.junit.Assert.assertTrue;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Cupidon;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsCupidon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;


public class CupidonTest {
	private Village village;
	private Cupidon cupidon;
	private Logger log = new Logger();
	
	@Rule
	public TestName name = new TestName();

	
	
	@Before
	public void init() throws Exception {
		this.log.setOnAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
		this.log.setDetailVoteVillage(true);
		this.cupidon = new Cupidon();
		Cupidon.setStatCupidon(new StatsCupidon());
		System.out.println("");
		System.out.println("Lancement de la méthode " + name.getMethodName());
		System.out.println("");
	}
	
	@Test
	public void testUniePourLaVie() {
		this.village = new Village();
		Personnage perso = new LoupGarouSimple();
		this.village.ajouterPersonnage(perso);
		this.village.ajouterPersonnage(this.cupidon);
		this.cupidon.flecheDeLAmour();
		
		Assert.assertTrue(this.cupidon.estAmoureux()); // sont ils amoureux ?
		Assert.assertTrue(perso.estAmoureux());
		
		Assert.assertTrue(this.cupidon.getAmoureux().equals(perso));// sont ils amoureux l'un de l'autre ?
		Assert.assertTrue(perso.getAmoureux().equals(this.cupidon));
		
		Assert.assertTrue(this.cupidon.getAlliés().contains(perso));// ce considèrent t'ils comme des allies indéfectible ?
		Assert.assertTrue(perso.getAlliés().contains(this.cupidon));
		
		assertTrue(Cupidon.getStatCupidon().getNbCoupleTraitre() == 1);
		assertTrue(Cupidon.getStatCupidon().getNbCoupleLoupGarou() == 0);
		assertTrue(Cupidon.getStatCupidon().getNbCoupleVillageois() == 0);
		
		
	}
	
	@Test
	public void testJusquaCeQueLaMortNousSepare() {// Mais que se passerait-il si un très malheureux accident les frappaient ?
		this.village = new Village(1,0);
		this.village.ajouterPersonnage(this.cupidon);
		this.cupidon.flecheDeLAmour();
		Assert.assertEquals(2, this.village.getNbPersonnageEnVie());
		this.cupidon.meurt();
		Assert.assertEquals(0, this.village.getNbPersonnageEnVie());
		
		assertTrue(Cupidon.getStatCupidon().getNbCoupleTraitre() == 0);
		assertTrue(Cupidon.getStatCupidon().getNbCoupleLoupGarou() == 0);
		assertTrue(Cupidon.getStatCupidon().getNbCoupleVillageois() == 1);
		
	}
	
	@Test
	public void testEnsembleEnversEtContreTous() {// sont ils prêt à tout pour que leur amour triomphe ?
		
		this.village = new Village(0,1);
		this.village.ajouterPersonnage(this.cupidon);
		this.cupidon.flecheDeLAmour();
		this.village.ajouterPersonnage(new LoupGarouSimple());// iront ils jusqu'a trahir leurs camps?
		this.village.tribunal();
		Assert.assertEquals(2, this.village.getNbPersonnageEnVie());
		Assert.assertTrue(this.village.getHabitantsEnVie().stream().allMatch(x->x.estAmoureux()));
		
		Personnage perso = new SimpleVillageois();
		this.village.getHabitantsEnVie().stream().filter(x->x.estAmoureux()).forEach(x->x.ajouterAllié(perso));// et tuer leurs amis?
		this.village.ajouterPersonnage(perso);
		Assert.assertFalse(this.village.getHabitantsEnVie().stream().allMatch(x->x.estAmoureux()));
		this.village.tribunal();
		Assert.assertTrue(this.village.getHabitantsEnVie().stream().allMatch(x->x.estAmoureux()));
		
		assertTrue(Cupidon.getStatCupidon().getNbCoupleTraitre() == 1);
		assertTrue(Cupidon.getStatCupidon().getNbCoupleLoupGarou() == 0);
		assertTrue(Cupidon.getStatCupidon().getNbCoupleVillageois() == 0);
	}

}
