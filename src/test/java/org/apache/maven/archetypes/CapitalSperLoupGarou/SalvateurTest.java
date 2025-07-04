package org.apache.maven.archetypes.CapitalSperLoupGarou;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Salvateur;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Sorciere;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsSalvateur;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class SalvateurTest {

private Village village;
	
	private Logger log = new Logger();
	
	private Salvateur salvateur;
	
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
		this.village = new Village(1,1);
		this.salvateur = new Salvateur();
		Salvateur.setStatsSalvateur(new StatsSalvateur());
	}
	
	@Test
	public void testSalvation() {// La mort n'est qu'un état temporaire
		this.village = new Village(0,0);
		LoupGarouSimple lg = new LoupGarouSimple();
		this.village.ajouterPersonnage(this.salvateur);
		this.village.ajouterPersonnage(lg);
		this.salvateur.agir();
		
		if(this.salvateur.getStatut().isProtéger()) {// si le salvateur s'est protégé
			this.village.getMeute().attaquerVillage();
			this.village.bilanTuerLaNuit();
			this.salvateur.finSalvation();
			Assert.assertEquals(2, this.village.getHabitantsEnVie().size());
			this.salvateur.agir();
			this.village.getMeute().attaquerVillage();
			this.village.bilanTuerLaNuit();
			Assert.assertEquals(1, this.village.getHabitantsEnVie().size());
			Assert.assertEquals(1, this.village.getNbLoupGarou());
			Assert.assertTrue(Salvateur.getStatsSalvateur().getNbSalvation() == 2);// Car Equals déprécié pour float, trouver une autre alternative
			Assert.assertTrue(Salvateur.getStatsSalvateur().getNbVillageoisSalvater() == 1);
			Assert.assertTrue(Salvateur.getStatsSalvateur().getNbProtectionReussie() == 1);
		}
		else {
			this.salvateur.finSalvation();
			this.salvateur.agir();
			this.village.getMeute().attaquerVillage();
			this.village.bilanTuerLaNuit();
			Assert.assertEquals(2, this.village.getHabitantsEnVie().size());
			this.salvateur.finSalvation();
			this.salvateur.agir();
			this.village.getMeute().attaquerVillage();
			this.village.bilanTuerLaNuit();
			Assert.assertEquals(1, this.village.getHabitantsEnVie().size());
			Assert.assertEquals(1, this.village.getNbLoupGarou());
			Assert.assertTrue(Salvateur.getStatsSalvateur().getNbSalvation() == 3);
			Assert.assertTrue(Salvateur.getStatsSalvateur().getNbVillageoisSalvater() == 1);
			Assert.assertTrue(Salvateur.getStatsSalvateur().getNbProtectionReussie() == 1);
		}
		
		
		
		
	}
	
	@Test
	public void testConfiance() {// Si je t'ai sauvé la vie alors cela vas devenir que tu n'est pas un loup-garous
		this.village = new Village(0,0);
		LoupGarouSimple lg = new LoupGarouSimple();
		Sorciere sorcière = new Sorciere();
		this.village.ajouterPersonnage(this.salvateur);
		this.village.ajouterPersonnage(sorcière);
		this.village.ajouterPersonnage(lg);
		lg.ajouterEnnemie(sorcière);
		
		this.village.getMeute().attaquerVillage();
		sorcière.setAction(0);
		sorcière.agir();
		this.village.bilanTuerLaNuit();
		sorcière.agirAprèsNuit();
		Assert.assertEquals(3, this.village.getNbPersonnageEnVie());
		
		this.salvateur.salvater(sorcière);
		this.village.getMeute().attaquerVillage();
		this.village.setNuitSansMort(true);// pour contrer le fait que je fais pas appel à la méthode nuit
		this.salvateur.agirAprèsNuit();
		Assert.assertEquals(3, this.village.getNbPersonnageEnVie());
		Assert.assertTrue(this.salvateur.getAlliés().contains(sorcière));
		Assert.assertTrue(Salvateur.getStatsSalvateur().getNbSalvation() == 1);
		Assert.assertTrue(Salvateur.getStatsSalvateur().getNbVillageoisSalvater() == 1);
		Assert.assertTrue(Salvateur.getStatsSalvateur().getNbProtectionReussie() == 1);
		Assert.assertTrue(Salvateur.getStatsSalvateur().getNbInnocentIdentiferGraceSalvation() == 1);
		
	}
	


}
