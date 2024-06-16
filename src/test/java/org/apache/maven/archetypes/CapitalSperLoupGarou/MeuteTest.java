package org.apache.maven.archetypes.CapitalSperLoupGarou;

import static org.junit.Assert.assertEquals;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarous.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarous.Meute;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois.Salvateur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois.Sorciere;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois.Voyante;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.StatsMeute;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class MeuteTest {
	@Rule
	public TestName name = new TestName();
	
	private Logger log = new Logger();
	private Village village;
	
	private double delta = 0.0;
	
	@Before
	public void setUp() throws Exception {
		this.log.setOnAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
		this.log.setDetailVoteVillage(true);
		System.out.println("");
		System.out.println("Lancement de la méthode " + name.getMethodName());
		System.out.println("");
		village = new Village(0,0);
		Meute.setStatsMeute(new StatsMeute());
	}
	
	@Test
	public void ajouterPersonnageTest()  {
		this.village.ajouterPersonnage(new LoupGarouSimple());
		Assert.assertEquals(1, this.village.getNbLoupGarou());
		Assert.assertEquals(1, this.village.getMeute().getNbLgEnVie());
	}
	
	@Test
	public void attaquerVillageTest()  {
		
		this.village.ajouterPersonnage(new SimpleVillageois());
		this.village.ajouterPersonnage(new LoupGarouSimple());
		
		Assert.assertEquals(1, this.village.getNbVillageois());
		Assert.assertEquals(1, this.village.getNbLoupGarou());
		this.village.getMeute().attaquerVillage();
		this.village.bilanTuerLaNuit();
		Assert.assertEquals(0, this.village.getNbVillageois());
		Assert.assertEquals(1, this.village.getNbLoupGarou());
		
		assertEquals(0, Meute.getStatsMeute().getNbSperVoter(), delta);
		assertEquals(0, Meute.getStatsMeute().getNbSurvivants(), delta);
		assertEquals(1, Meute.getStatsMeute().getNbVote(), delta);
		assertEquals(this.village.getPersonnageParId(0).getId(), Meute.getStatsMeute().getDernierePersonneDevorer(), delta);
		
		this.village.ajouterPersonnage(new Voyante());
		this.village.getMeute().attaquerVillage();
		this.village.bilanTuerLaNuit();
		
		assertEquals(1, Meute.getStatsMeute().getNbSperVoter(), delta);
		assertEquals(0, Meute.getStatsMeute().getNbSurvivants(), delta);
		assertEquals(2, Meute.getStatsMeute().getNbVote(), delta);
		assertEquals(this.village.getPersonnageParId(2).getId(), Meute.getStatsMeute().getDernierePersonneDevorer(), delta);
		
		Salvateur salvateur = new Salvateur();
		this.village.ajouterPersonnage(salvateur);
		salvateur.salvater(salvateur);
		this.village.getMeute().attaquerVillage();
		this.village.bilanTuerLaNuit();
		
		assertEquals(1, Meute.getStatsMeute().getNbSperVoter(), delta);
		assertEquals(1, Meute.getStatsMeute().getNbSurvivants(), delta);
		assertEquals(3, Meute.getStatsMeute().getNbVote(), delta);
		
		
		salvateur.meurt();
		Sorciere sorciere = new Sorciere();
		this.village.ajouterPersonnage(sorciere);
		sorciere.setAction(0);
		this.village.getMeute().attaquerVillage();
		sorciere.agir();
		this.village.bilanTuerLaNuit();
		assertEquals(this.village.getPersonnageParId(4).getId(), Meute.getStatsMeute().getDernierePersonneDevorer(), delta);
		Meute.getStatsMeute().ASurvecu(this.village.getVillage());
		assertEquals(2, Meute.getStatsMeute().getNbSperVoter(), delta);
		assertEquals(2, Meute.getStatsMeute().getNbSurvivants(), delta);
		assertEquals(4, Meute.getStatsMeute().getNbVote(), delta);
		
		
	}

}
