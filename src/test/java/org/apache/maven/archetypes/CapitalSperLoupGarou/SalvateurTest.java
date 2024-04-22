package org.apache.maven.archetypes.CapitalSperLoupGarou;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Salvateur;
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
		this.salvateur.salvater();
		
		if(this.salvateur.getStatut().isProtéger()) {// si le salvateur s'est protégé
			this.village.getMeute().attaquerVillage();
			this.village.bilanTuerLaNuit();
			this.salvateur.finSalvation();
			Assert.assertEquals(2, this.village.getHabitantsEnVie().size());
			this.salvateur.salvater();
			this.village.getMeute().attaquerVillage();
			this.village.bilanTuerLaNuit();
			Assert.assertEquals(1, this.village.getHabitantsEnVie().size());
			Assert.assertEquals(1, this.village.getNbLoupGarou());
		}
		else {
			this.salvateur.finSalvation();
			this.salvateur.salvater();
			this.village.getMeute().attaquerVillage();
			this.village.bilanTuerLaNuit();
			Assert.assertEquals(2, this.village.getHabitantsEnVie().size());
			this.salvateur.finSalvation();
			this.salvateur.salvater();
			this.village.getMeute().attaquerVillage();
			this.village.bilanTuerLaNuit();
			Assert.assertEquals(1, this.village.getHabitantsEnVie().size());
			Assert.assertEquals(1, this.village.getNbLoupGarou());
		}
		
		
		
		
	}
	
	@Test
	public void testReset() {// La vie n'est qu'une boucle sans fin
		this.village = new Village(0,0);
		this.village.ajouterPersonnage(this.salvateur);
		this.salvateur.salvater();
		Assert.assertNotEquals(new Salvateur(), this.salvateur);
		Assert.assertEquals(this.salvateur, this.salvateur.getDernierPersonnageProtéger());
		this.salvateur.reset();
		Assert.assertEquals(new Salvateur(), this.salvateur);
	}

}
