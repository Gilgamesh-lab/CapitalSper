package org.apache.maven.archetypes.CapitalSperLoupGarou;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Salvateur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Sorcière;
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
		}
		
		
		
		
	}
	
	@Test
	public void testConfiance() {// Si je t'ai sauvé la vie alors cela vas devenir que tu n'est pas un loup-garous
		this.village = new Village(0,0);
		LoupGarouSimple lg = new LoupGarouSimple();
		Sorcière sorcière = new Sorcière();
		this.village.ajouterPersonnage(this.salvateur);
		this.village.ajouterPersonnage(sorcière);
		this.village.ajouterPersonnage(lg);
		lg.ajouterEnnemies(sorcière);
		
		this.village.getMeute().attaquerVillage();
		sorcière.setAction(0);
		sorcière.agir();
		this.village.bilanTuerLaNuit();
		sorcière.agirAprèsNuit();
		Assert.assertEquals(3, this.village.getNbPersonnageEnVie());
		
		this.salvateur.salvater(sorcière);
		this.village.getMeute().attaquerVillage();
		this.salvateur.agirAprèsNuit();
		Assert.assertEquals(3, this.village.getNbPersonnageEnVie());
		Assert.assertTrue(this.salvateur.getAlliés().contains(sorcière));
		
	}
	


}
