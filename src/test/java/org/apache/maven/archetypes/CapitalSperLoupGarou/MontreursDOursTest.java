package org.apache.maven.archetypes.CapitalSperLoupGarou;


import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.MontreurDOurs;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsMontreursDOurs;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;


public class MontreursDOursTest {

	private Village village = new Village(0,0);
	
	private Logger log = new Logger();
	
	@Rule
	public TestName name = new TestName();
	
	private MontreurDOurs montreurDOurs;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("");
		System.out.println("Lancement de la méthode " + name.getMethodName());
		System.out.println("");
		this.log.setOnAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
		this.log.setDetailVoteVillage(true);
		this.montreurDOurs = new MontreurDOurs();
		MontreurDOurs.setStatsMontreursDOurs(new StatsMontreursDOurs());
	}
	
	@Test
	public void testTraque() {// Je t'ai trouvé
		this.village = new Village(1, 1);
		this.village.ajouterPersonnage(this.montreurDOurs);
		Personnage[] voisins = {this.village.getPersonnage(0),this.village.getPersonnage(1) };
		this.montreurDOurs.setVoisins(voisins);
		this.montreurDOurs.traquerLoupGarous();
		Assert.assertTrue(this.montreurDOurs.aTrouverUnLoup());
		Assert.assertTrue(this.montreurDOurs.getEnnemies().size() == 2);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbGrognement() == 1);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbUnLoupGarouVoisin() == 1);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbZeroLoupGarouVoisin() == 0);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbDeuxLoupGarouVoisin() == 0);
		
	}
	
	
	@Test
	public void testCramer() {// Si il a détecter un loups parmis ses voisins depuis l'arrivé d'un nouveau voisin 
		this.village = new Village(1, 0);
		SimpleVillageois sv = new SimpleVillageois();
		this.village.ajouterPersonnage(this.montreurDOurs);
		this.village.ajouterPersonnage(sv);
		Personnage[] voisins = {this.village.getPersonnage(0),this.village.getPersonnage(2) };
		this.montreurDOurs.setVoisins(voisins);
		this.montreurDOurs.traquerLoupGarous();
		
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbGrognement() == 0);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbUnLoupGarouVoisin() == 0);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbZeroLoupGarouVoisin() == 1);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbDeuxLoupGarouVoisin() == 0);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbVoisinDifférent() == 0);
		
		Assert.assertFalse(this.montreurDOurs.aTrouverUnLoup());
		LoupGarouSimple lg = new LoupGarouSimple();
		sv.meurt();
		this.village.ajouterPersonnage(lg);
		this.montreurDOurs.traquerLoupGarous();
		Assert.assertTrue(this.montreurDOurs.aTrouverUnLoup());
		Assert.assertTrue(this.montreurDOurs.getEnnemies().contains(lg));
		Assert.assertTrue(this.montreurDOurs.getEnnemies().size() == 1);
		Assert.assertTrue(this.montreurDOurs.getAlliés().size() == 2);
		
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbGrognement() == 1);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbUnLoupGarouVoisin() == 1);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbZeroLoupGarouVoisin() == 1);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbDeuxLoupGarouVoisin() == 0);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbVoisinDifférent() == 1);
	}
	
	
	@Test
	public void testCramer2() {//  Si il avait trouver un loup-garou parmis ses voisins et que un des ses ancien(=mort) voisin était innocent
		
		this.village = new Village(0, 1);
		SimpleVillageois sv = new SimpleVillageois();
		this.village.ajouterPersonnage(sv);
		this.village.ajouterPersonnage(this.montreurDOurs);
		Personnage[] voisins = {this.village.getPersonnage(0),this.village.getPersonnage(1) };
		this.montreurDOurs.setVoisins(voisins);
		this.montreurDOurs.traquerLoupGarous();
		
		
		Assert.assertTrue(this.montreurDOurs.aTrouverUnLoup());
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbGrognement() == 1);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbUnLoupGarouVoisin() == 1);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbZeroLoupGarouVoisin() == 0);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbDeuxLoupGarouVoisin() == 0);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbVoisinDifférent() == 0);
		
		LoupGarouSimple lg = new LoupGarouSimple();
		sv.meurt();
		
		SimpleVillageois sv2 = new SimpleVillageois();
		this.village.ajouterPersonnage(sv2);
		this.montreurDOurs.traquerLoupGarous();
		
		Assert.assertTrue(this.montreurDOurs.aTrouverUnLoup());
		Assert.assertTrue(this.montreurDOurs.getEnnemies().contains(lg));
		Assert.assertFalse(this.montreurDOurs.getEnnemies().contains(sv2));
		
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbGrognement() == 2);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbUnLoupGarouVoisin() == 2);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbZeroLoupGarouVoisin() == 0);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbDeuxLoupGarouVoisin() == 0);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbVoisinDifférent() == 1);
	}
	
	@Test
	public void testInnocent() {// Si le Montreurs est mort alors qu'il n'a pas grogné juste avant
		this.village = new Village(2, 0);
		this.village.ajouterPersonnage(montreurDOurs);
		Personnage[] voisins = {this.village.getPersonnage(0),this.village.getPersonnage(1) };
		this.montreurDOurs.setVoisins(voisins);
		this.montreurDOurs.traquerLoupGarous();
		this.montreurDOurs.meurt();
		Assert.assertTrue(this.village.getPersoDevoilerCommeAlliéeParMontreursDOurs().contains(this.village.getPersonnageParId(1)));
		Assert.assertTrue(this.village.getPersoDevoilerCommeAlliéeParMontreursDOurs().contains(this.village.getPersonnageParId(0)));
		
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbGrognement() == 0);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbUnLoupGarouVoisin() == 0);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbZeroLoupGarouVoisin() == 1);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbDeuxLoupGarouVoisin() == 0);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbMort() == 1);
	}
	
	@Test
	public void testPotentielCoupable() {// Si le Montreurs est mort alors qu'il a grogné juste avant
		this.village = new Village(1, 1);
		this.village.ajouterPersonnage(montreurDOurs);
		Personnage[] voisins = {this.village.getPersonnage(0),this.village.getPersonnage(1) };
		this.montreurDOurs.setVoisins(voisins);
		this.montreurDOurs.traquerLoupGarous();
		this.montreurDOurs.meurt();
		Assert.assertTrue(this.village.getPersoDevoilerCommeEnnemieParMontreursDOurs().contains(this.village.getPersonnageParId(1)));
		Assert.assertTrue(this.village.getPersoDevoilerCommeEnnemieParMontreursDOurs().contains(this.village.getPersonnageParId(0)));
		this.village.getPersonnage(1).meurt();
		Assert.assertFalse(this.village.getPersoDevoilerCommeEnnemieParMontreursDOurs().contains(this.village.getPersonnageParId(0)));
		
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbGrognement() == 1);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbUnLoupGarouVoisin() == 1);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbZeroLoupGarouVoisin() == 0);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbDeuxLoupGarouVoisin() == 0);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbMort() == 1);
	}
	
	
	@Test
	public void testGracier() {// Si un innocent a été considéré comme un ennemies car l'autre voisin l'était
		this.village = new Village(1, 1);
		this.village.ajouterPersonnage(montreurDOurs);
		Personnage[] voisins = {this.village.getPersonnage(0),this.village.getPersonnage(1) };
		this.montreurDOurs.setVoisins(voisins);
		this.montreurDOurs.traquerLoupGarous();
		this.village.getPersonnageParId(1).meurt();
		this.village.ajouterPersonnage(new SimpleVillageois());
		this.montreurDOurs.traquerLoupGarous();
		
		Assert.assertEquals(1, this.montreurDOurs.getEnnemies().size());
		Assert.assertEquals(2, this.montreurDOurs.getAlliés().size());
		
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbGrognement() == 1);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbUnLoupGarouVoisin() == 1);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbZeroLoupGarouVoisin() == 1);
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbDeuxLoupGarouVoisin() == 0);
		System.out.println(MontreurDOurs.getStatsMontreursDOurs().getNbVoisinDifférent());
		Assert.assertTrue(MontreurDOurs.getStatsMontreursDOurs().getNbVoisinDifférent() == 1);
	}
	
	
	
	
	

}
