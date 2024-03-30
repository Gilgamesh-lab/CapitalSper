package org.apache.maven.archetypes.CapitalSperLoupGarou;


import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.MontreurDOurs;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MontreursDOursTest {

	private Village village = new Village(0,0);
	
	private Logger log = new Logger();
	
	@Before
	public void setUp() throws Exception {
		this.log.setOffAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
	}
	
	@Test
	public void testTraque() {// Je t'ai trouvé
		System.out.println();
		System.out.println("Lancement du test Traque");
		System.out.println();
		this.village = new Village(1, 1);
		MontreurDOurs montreurDOurs = new MontreurDOurs();
		this.village.ajouterPersonnage(montreurDOurs);
		montreurDOurs.setVoisins();
		montreurDOurs.traquerLoupGarous();
		Assert.assertTrue(montreurDOurs.aTrouverUnLoup());
		Assert.assertTrue(montreurDOurs.getEnnemies().size() == 2);
		
	}
	
	
	@Test
	public void testCramer() {// Si il a détecter un loups parmis ses voisins depuis l'arrivé d'un nouveau voisin 
		System.out.println();
		System.out.println("Lancement du test Cramer");
		System.out.println();
		this.village = new Village(1, 0);
		SimpleVillageois sv = new SimpleVillageois();
		MontreurDOurs montreurDOurs = new MontreurDOurs();
		this.village.ajouterPersonnage(montreurDOurs);
		this.village.ajouterPersonnage(sv);
		montreurDOurs.setVoisins();
		montreurDOurs.traquerLoupGarous();
		Assert.assertFalse(montreurDOurs.aTrouverUnLoup());
		LoupGarouSimple lg = new LoupGarouSimple();
		sv.meurt();
		this.village.ajouterPersonnage(lg);
		montreurDOurs.traquerLoupGarous();
		Assert.assertTrue(montreurDOurs.aTrouverUnLoup());
		Assert.assertTrue(montreurDOurs.getEnnemies().contains(lg));
		Assert.assertTrue(montreurDOurs.getEnnemies().size() == 1);
		Assert.assertTrue(montreurDOurs.getAlliés().size() == 3);
	}
	
	
	@Test
	public void testCramer2() {//  Si il avait trouver un loup-garou parmis ses voisins et que un des ses ancien(=mort) voisin était innocent
		System.out.println();
		System.out.println("Lancement du test Cramer2");
		System.out.println();
		
		this.village = new Village(0, 1);
		SimpleVillageois sv = new SimpleVillageois();
		MontreurDOurs montreurDOurs = new MontreurDOurs();
		this.village.ajouterPersonnage(montreurDOurs);
		this.village.ajouterPersonnage(sv);
		montreurDOurs.setVoisins();
		montreurDOurs.traquerLoupGarous();
		
		
		Assert.assertTrue(montreurDOurs.aTrouverUnLoup());
		LoupGarouSimple lg = new LoupGarouSimple();
		sv.meurt();
		
		SimpleVillageois sv2 = new SimpleVillageois();
		this.village.ajouterPersonnage(sv2);
		montreurDOurs.traquerLoupGarous();
		Assert.assertTrue(montreurDOurs.aTrouverUnLoup());
		Assert.assertTrue(montreurDOurs.getEnnemies().contains(lg));
		Assert.assertFalse(montreurDOurs.getEnnemies().contains(sv2));
		Assert.assertTrue(montreurDOurs.getAlliés().size() == 1);
	}
	
	
	
	
	
	

}
