package org.apache.maven.archetypes.CapitalSperLoupGarou;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Voleur;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Voyante;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsChasseur;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class VoleurTest {
	private Village village = new Village(0,0);
	
	private Logger log = new Logger();
	
	@Rule
	public TestName name = new TestName();
	
	private Voleur voleur;
	
	@Before
	public void setUp() throws Exception {
		this.log.setOnAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
		this.log.setDetailVoteVillage(true);
		System.out.println("");
		System.out.println("Lancement de la méthode " + name.getMethodName());
		System.out.println("");
		this.voleur = new Voleur();
		//Chasseur.setStatsChasseur(new StatsChasseur());
		
	}
	
	@Test
	public void voler() { // On ne se refait pas
		this.village = new Village(0, 0);
		this.village.ajouterPersonnage(this.voleur);
		assertEquals(0, this.voleur.personnageNonMisEnJeu.size());
		this.village.premièreNuit();
		assertEquals(2, this.voleur.personnageNonMisEnJeu.size());
		assertEquals(SimpleVillageois.IDROLE, this.voleur.personnageNonMisEnJeu.get(0).getIdDeRole());
		assertEquals(SimpleVillageois.IDROLE, this.voleur.personnageNonMisEnJeu.get(1).getIdDeRole());
		
		
	}
	
	@Test
	public void metamorphose() { // Une nouvelle vie commence
		this.village = new Village(1, 1);
		this.village.ajouterPersonnage(this.voleur);
		this.voleur.initPartie(this.village.getPersonnageParIdRole(LoupGarouSimple.IDROLE), this.village.getPersonnageParIdRole(SimpleVillageois.IDROLE));
		this.voleur.agirPremiereNuit();
		assertTrue(this.voleur.getPersonnageChoisie().getIdDeRole() == LoupGarouSimple.IDROLE);
		
		this.village.getVillage().remove(0);
		this.village.getVillage().remove(1);
		Voyante vovo = new Voyante();
		this.village.ajouterPersonnage(vovo);
		assertEquals(2, this.village.getNbPersonnageEnVie());
		assertTrue(vovo.getEnnemies().isEmpty());
		vovo.sonder();
		assertFalse(vovo.getEnnemies().isEmpty());
		assertEquals(this.voleur.getPersonnageChoisie(), vovo.getEnnemies().get(0));
		
		
		
	}
	
	/*@Test
	public void jePrendToujoursCeQuiAsLePlusDeValeur() { // Avare est mon deuxième prénom
		this.village = new Village(1, 0);
		this.village.ajouterPersonnage(Voyante.IDROLE);
		this.village.ajouterPersonnage(this.voleur);
		this.voleur.initPartie(this.village.getPersonnageParIdRole(SimpleVillageois.IDROLE), this.village.getPersonnageParIdRole(Voyante.IDROLE));
		this.voleur.agirPremiereNuit();
		assertTrue(this.voleur.getPersonnageChoisie().getIdDeRole() == Voyante.IDROLE);
		
		assertTrue(this.voleur.getAlliés().isEmpty());
		this.voleur.agir(); //Voyante
		assertFalse(this.voleur.getAlliés().isEmpty());
	}*/
	
	@Test
	public void pasDeCannibalisme() { // Je suis l'un des votre maintenant
		this.village = new Village(1, 2);
		this.village.ajouterPersonnage(this.voleur);
		this.voleur.initPartie(this.village.getPersonnageParIdRole(LoupGarouSimple.IDROLE), this.village.getPersonnageParIdRole(SimpleVillageois.IDROLE));

		
		this.voleur.agirPremiereNuit();
		assertTrue(this.voleur.getAlliés().contains(this.village.getVillage().get(2)));
		assertTrue(this.village.getPersonnage(2).getAlliés().contains(this.voleur.getPersonnageChoisie()));
		
	}

}
