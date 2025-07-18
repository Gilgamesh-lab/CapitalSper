package org.apache.maven.archetypes.CapitalSperLoupGarou;

import static org.junit.Assert.assertEquals;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Maire;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Voyante;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsMaire;
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
	
	
	private double delta = 0.0;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("");
		System.out.println("Lancement de la méthode " + name.getMethodName());
		System.out.println("");
		this.log.setOnAfficherLogDetailsPartie();
		this.log.setOnAfficherLogDetailsRoleAction();
		this.log.setDetailVoteVillage(true);
		this.village = new Village(2,1);
		this.village.onMaire();
		Maire.setStatsMaire(new StatsMaire());
	}
	
	@Test
	public void testElection() {// A moi le pouvoir
		Voyante voyante = new Voyante();
		this.village.ajouterPersonnage(voyante);
		this.village.getPersonnageParId(0).ajouterPersoListeDeVote(voyante); // pour truquer les elections en faveur de vi
		this.village.getPersonnageParId(1).ajouterPersoListeDeVote(voyante);
		this.village.getPersonnageParId(2).ajouterPersoListeDeVote(voyante);
		this.village.getMaire().election();
		Assert.assertTrue(voyante.estMaire());
		assertEquals(0, Maire.getStatsMaire().getNbVote(), delta);
		assertEquals(1, Maire.getStatsMaire().getNbMaire(), delta);
		assertEquals(1, Maire.getStatsMaire().getNbSperMaire(), delta);
		
	}
	
	@Test
	public void testSuccessionAllié() {// Mon amis, je te laisse la suite
		SimpleVillageois vi = new SimpleVillageois();
		this.village.ajouterPersonnage(vi);

		this.village.getMaire().entrerEnFonction(vi);
		vi.ajouterAllié(this.village.getPersonnageParId(0));
		vi.meurt();
		Assert.assertTrue(this.village.getPersonnageParId(0).estMaire());
		assertEquals(2, Maire.getStatsMaire().getNbMaire(), delta);
		assertEquals(0, Maire.getStatsMaire().getNbSperMaire(), delta);
	}
	
	@Test
	public void testSuccessionAleatoire() {// Puisse cette insigne tombé entre de bonne mains
		this.village.ajouterPersonnage(LoupGarouSimple.IDROLE);
		this.village.ajouterPersonnage(LoupGarouSimple.IDROLE);
		this.village.getMaire().entrerEnFonction(this.village.getPersonnageParId(0));
		this.village.getPersonnageParId(0).ajouterAllié(this.village.getPersonnageParId(1));
		this.village.getPersonnageParId(0).meurt();
		Assert.assertTrue(this.village.getHabitantsEnVie().stream().anyMatch(x->x.estMaire()));
		this.village.getPersonnageParId(1).meurt();
		
		//System.out.println(this.village.get);
		assertEquals(3, Maire.getStatsMaire().getNbMaire(), delta);
		assertEquals(1, Maire.getStatsMaire().getNbLoupGarouMaire(), delta);
	}
	
	@Test
	public void testDeuxVotes() {// C'est moi la loi
		this.village = new Village(0,2);
		SimpleVillageois vi = new SimpleVillageois();
		this.village.onMaire();
		this.village.getMaire().entrerEnFonction(vi);
		this.village.ajouterPersonnage(vi);
		this.village.getPersonnageParId(0).ajouterEnnemie(this.village.getPersonnageParId(1));
		this.village.getPersonnageParId(1).ajouterEnnemie(this.village.getPersonnageParId(0));
		this.village.getPersonnageParId(2).ajouterEnnemie(this.village.getPersonnageParId(1));
		vi.ajouterEnnemie(this.village.getPersonnageParId(0));
		this.village.tribunal();
		Assert.assertEquals(2,this.village.getNbPersonnageEnVie());
		Assert.assertTrue(vi.estEnvie());
		assertEquals(1, Maire.getStatsMaire().getNbMaire(), delta);
		assertEquals(1, Maire.getStatsMaire().getNbVote(), delta);
		assertEquals(1, Maire.getStatsMaire().getNbVoteDecisifDuMaire(), delta);
	}
	
	@Test
	public void testEgalite() {// J'aurais le dernier mot
		this.village = new Village(2,2);
		SimpleVillageois vi = new SimpleVillageois();
		this.village.onMaire();
		this.village.getMaire().entrerEnFonction(vi);
		this.village.ajouterPersonnage(vi);
		this.village.getPersonnageParId(0).ajouterEnnemie(this.village.getPersonnageParId(1));
		this.village.getPersonnageParId(1).ajouterEnnemie(this.village.getPersonnageParId(0));
		this.village.getPersonnageParId(2).ajouterEnnemie(this.village.getPersonnageParId(0));
		this.village.getPersonnageParId(3).ajouterEnnemie(this.village.getPersonnageParId(0));
		vi.ajouterEnnemie(this.village.getPersonnageParId(1));
		this.village.tribunal();
		Assert.assertEquals(4,this.village.getNbPersonnageEnVie());
		Assert.assertTrue(this.village.getPersonnageParId(0).estEnvie());
		Assert.assertFalse(this.village.getPersonnageParId(1).estEnvie());
		assertEquals(1, Maire.getStatsMaire().getNbMaire(), delta);
		assertEquals(1, Maire.getStatsMaire().getNbVote(), delta);
		assertEquals(1, Maire.getStatsMaire().getNbEgaliter(), delta);
	}
	
	
}
