package org.apache.maven.archetypes.CapitalSperLoupGarou;


import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Cupidon;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


class CupidonTest {
	private Village village;
	private Cupidon cupidon;
	private Logger log = new Logger();

	
	
	@Before
	public void init() throws Exception {
		this.log.setOnAfficherLogDetailsRoleAction();
		this.cupidon = new Cupidon();
	}
	
	@Test
	public void testUniePourLaVie() {
		System.out.println();
		System.out.println("Lancement du test JusquaCeQueLaMortNousSepare");
		System.out.println();
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
		
		
	}
	
	@Test
	public void testJusquaCeQueLaMortNousSepare() {// Mais que se passerait-il si un très malheureux accident les frappaient ?
		System.out.println();
		System.out.println("Lancement du test JusquaCeQueLaMortNousSepare");
		System.out.println();
		this.village = new Village(1,0);
		this.village.ajouterPersonnage(this.cupidon);
		this.cupidon.flecheDeLAmour();
		Assert.assertEquals(2, this.village.getNbPersonnageEnVie());
		this.cupidon.meurt();
		Assert.assertEquals(0, this.village.getNbPersonnageEnVie());
		
	}
	
	@Test
	public void testEnsembleEnversEtContreTous() {// sont ils prêt à tout pour que leur amour triomphe ?
		System.out.println();
		System.out.println("Lancement du test EnsembleContreTous");
		System.out.println();
		
		this.village = new Village(0,1);
		this.village.ajouterPersonnage(this.cupidon);
		this.cupidon.flecheDeLAmour();
		this.village.ajouterPersonnage(new LoupGarouSimple());// iront ils jusqu'a trahir leurs camps?
		this.village.voteEnnemie();
		Assert.assertEquals(2, this.village.getNbPersonnageEnVie());
		Assert.assertTrue(this.village.getHabitantsEnVie().stream().allMatch(x->x.estAmoureux()));
		
		Personnage perso = new SimpleVillageois();
		this.village.getHabitantsEnVie().stream().filter(x->x.estAmoureux()).forEach(x->x.ajouterAlliés(perso));// et tuer leurs amis?
		this.village.ajouterPersonnage(perso);
		Assert.assertFalse(this.village.getHabitantsEnVie().stream().allMatch(x->x.estAmoureux()));
		this.village.voteEnnemie();
		Assert.assertTrue(this.village.getHabitantsEnVie().stream().allMatch(x->x.estAmoureux()));
	}

}
