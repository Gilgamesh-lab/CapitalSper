package org.apache.maven.archetypes.CapitalSperLoupGarou;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Maire;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.MontreurDOurs;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class MaireTest {
	private Village village;
	
	private Logger log = new Logger();
	
	@Rule
	public TestName name = new TestName();
	
	private Maire maire;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("");
		System.out.println("Lancement de la méthode " + name.getMethodName());
		System.out.println("");
		this.log.setOnAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
		this.log.setDetailVoteVillage(true);
	}
	
	@Test
	public void testElection() {// A moi le pouvoir
		this.village = new Village(2,1);
		this.village.onMaire();
		SimpleVillageois vi = new SimpleVillageois();
		this.village.ajouterPersonnage(vi);
		this.village.getPersonnageParId(0).ajouterPersoListeDeVote(vi); // pour truquer les elections en faveur de vi
		this.village.getPersonnageParId(1).ajouterPersoListeDeVote(vi);
		this.village.getPersonnageParId(2).ajouterPersoListeDeVote(vi);
		this.village.getMaire().election();
		Assert.assertTrue(vi.estMaire());
	}
	
	@Test
	public void testSuccessionAllié() {// Mon amis, je te laisse la suite
		this.village = new Village(2,1);
		this.village.onMaire();
		SimpleVillageois vi = new SimpleVillageois();
		this.village.ajouterPersonnage(vi);

		this.village.getMaire().entrerEnFonction(vi);
		vi.ajouterAlliés(this.village.getPersonnageParId(0));
		vi.meurt();
		Assert.assertTrue(this.village.getPersonnageParId(0).estMaire());
	}
	
	@Test
	public void testSuccessionAleatoire() {// Puisse cette insigne tombé entre de bonne mains
		this.village = new Village(2,1);
		this.village.onMaire();
		SimpleVillageois vi = new SimpleVillageois();
		this.village.ajouterPersonnage(vi);

		this.village.getMaire().entrerEnFonction(vi);
		vi.meurt();
		Assert.assertTrue(this.village.getHabitantsEnVie().stream().anyMatch(x->x.estMaire()));
	}
	
	@Test
	public void testDeuxVotes() {// C'est moi la loi
		this.village = new Village(2,1);
		SimpleVillageois vi = new SimpleVillageois();
		this.village.onMaire();
		this.village.getMaire().entrerEnFonction(vi);
		this.village.ajouterPersonnage(vi);
		this.village.getPersonnageParId(0).ajouterEnnemies(this.village.getPersonnageParId(1));
		this.village.getPersonnageParId(1).ajouterEnnemies(this.village.getPersonnageParId(0));
		this.village.getPersonnageParId(2).ajouterEnnemies(this.village.getPersonnageParId(1));
		vi.ajouterEnnemies(this.village.getPersonnageParId(0));
		this.village.tribunal();
		Assert.assertEquals(3,this.village.getNbPersonnageEnVie());
		Assert.assertFalse(this.village.getPersonnageParId(0).estEnvie());
	}
	
	@Test
	public void testEgalite() {// J'aurais le dernier mot
		this.village = new Village(2,2);
		SimpleVillageois vi = new SimpleVillageois();
		this.village.onMaire();
		this.village.getMaire().entrerEnFonction(vi);
		this.village.ajouterPersonnage(vi);
		this.village.getPersonnageParId(0).ajouterEnnemies(this.village.getPersonnageParId(1));
		this.village.getPersonnageParId(1).ajouterEnnemies(this.village.getPersonnageParId(0));
		this.village.getPersonnageParId(2).ajouterEnnemies(this.village.getPersonnageParId(0));
		this.village.getPersonnageParId(3).ajouterEnnemies(this.village.getPersonnageParId(0));
		vi.ajouterEnnemies(this.village.getPersonnageParId(1));
		this.village.tribunal();
		Assert.assertEquals(4,this.village.getNbPersonnageEnVie());
		Assert.assertTrue(this.village.getPersonnageParId(0).estEnvie());
		Assert.assertFalse(this.village.getPersonnageParId(1).estEnvie());
	}
	
	
}
