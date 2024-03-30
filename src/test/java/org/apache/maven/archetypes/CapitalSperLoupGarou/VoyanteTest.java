package org.apache.maven.archetypes.CapitalSperLoupGarou;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Voyante;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VoyanteTest {

	private Village village;
	
	private Logger log = new Logger();
	
	private Voyante voyante;
	
	@Before
	public void setUp() throws Exception {
		this.log.setOffAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
		this.village = new Village(0,0);
		voyante = new Voyante();
	}
	
	@Test
	public void testVoyanceLg() {// Je vais te dénoncer
		System.out.println();
		System.out.println("Lancement du test VoyanceLg");
		System.out.println();
		this.village = new Village(0,0);
		LoupGarouSimple lg = new LoupGarouSimple();
		Voyante voyante = new Voyante();
		this.village.ajouterPersonnage(voyante);
		this.village.ajouterPersonnage(lg);
		voyante.sonder();
		Assert.assertTrue(voyante.getAlliés().size() == 1);//se compte elle même
		Assert.assertTrue(voyante.getEnnemies().size() == 1);
		Assert.assertTrue(voyante.getEnnemies().contains(lg));
	}
	
	@Test
	public void testVoyanceVi() {// Je te protégerais
		System.out.println();
		System.out.println("Lancement du test testVoyanceVi");
		System.out.println();
		this.village = new Village(0,0);
		Voyante voyante = new Voyante();
		SimpleVillageois vi = new SimpleVillageois();
		this.village.ajouterPersonnage(voyante);
		this.village.ajouterPersonnage(vi);
		voyante.sonder();
		Assert.assertTrue(voyante.getEnnemies().size() == 0);
		Assert.assertTrue(voyante.getAlliés().size() == 2);
		Assert.assertTrue(voyante.getAlliés().contains(vi));
	}
}
