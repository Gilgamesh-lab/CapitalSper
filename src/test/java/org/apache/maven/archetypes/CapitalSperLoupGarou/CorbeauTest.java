package org.apache.maven.archetypes.CapitalSperLoupGarou;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois.Corbeau;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.StatsCorbeau;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class CorbeauTest {

private Village village = new Village(0,0);
	
	private Logger log = new Logger();
	
	@Rule
	public TestName name = new TestName();
	
	private Corbeau corbeau;
	
	private double delta = 0.0;
	
	@Before
	public void setUp() throws Exception {
		this.log.setOnAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
		this.log.setDetailVoteVillage(true);
		System.out.println("");
		System.out.println("Lancement de la méthode " + name.getMethodName());
		System.out.println("");
		this.corbeau = new Corbeau();
		Corbeau.setStatsCorbeau(new StatsCorbeau());
	}
	
	@Test
	public void testCorbeautage() {// a ce qu'il parait tu sens le loup-garou
		this.village = new Village(0, 1);
		this.village.ajouterPersonnage(corbeau);
		corbeau.agir();
		this.village.tribunal();
		
		assertEquals(1 ,this.village.getNbPersonnageEnVie());
		assertEquals(0 ,this.village.getNbLoupGarou());
		
		assertEquals(1, Corbeau.getStatsCorbeau().getNbCorbeautage(), delta);
		assertEquals(1, Corbeau.getStatsCorbeau().getNbCorbeautageSurLoupGarou(), delta);
		assertEquals(1, Corbeau.getStatsCorbeau().getNbCorbeautageDecisif(), delta);
		
		
	}
	
	@Test
	public void testCorbeautageDecisif() {// zut raté
		this.village = new Village(10, 0);
		this.village.ajouterPersonnage(corbeau);
		ArrayList<Personnage> perso = new ArrayList<Personnage>();
		perso.add(this.village.getHabitantsEnVie().get(0));
		this.village.setPersoDevoilerCommeEnnemieParMontreursDOurs(perso);
		corbeau.agir();
		this.village.tribunal();
		
		assertEquals(10 ,this.village.getNbPersonnageEnVie());
		assertTrue(this.village.getHabitantsEnVie().get(0).estEnvie());
		
		assertEquals(1, Corbeau.getStatsCorbeau().getNbCorbeautage(), delta);
		assertEquals(0, Corbeau.getStatsCorbeau().getNbCorbeautageSurLoupGarou(), delta);
		assertEquals(0, Corbeau.getStatsCorbeau().getNbCorbeautageDecisif(), delta);
		
		
	}
}
