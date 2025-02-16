package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsVoleur;

public class Voleur extends VillageoisSpecial {
	public final static int IDROLE = 1;
	private static StatsVoleur statsVoleur = new StatsVoleur();
	private Personnage personnageChoisie;
	public ArrayList<Personnage> personnageNonMisEnJeu;
	
	public Voleur() {
		super(IDROLE, statsVoleur);
		this.personnageNonMisEnJeu = new ArrayList<Personnage>(2);
	}
	
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Metamorphose));
	}
	
	public static void setStatsVoleur(StatsVoleur statsVoleur) {
		Voleur.statsVoleur = statsVoleur;
	}
	
	public StatsVoleur getStatsVoleur() {
		return Voleur.statsVoleur;
	}
	
	public void initPartie(Personnage perso1, Personnage perso2) {
		this.personnageNonMisEnJeu.add(perso1);
		this.personnageNonMisEnJeu.add(perso2);
		Logger.log("Les personnages non mis en jeu sont " + perso1 + " et " + perso2 + ".", TypeDeLog.role);
		Logger.log("", TypeDeLog.role);
		statsVoleur.initPartie(perso1, perso2);
	}
	
	@Override
	public void agirPremiereNuit() {
		Personnage perso1 = this.personnageNonMisEnJeu.get(0);
		Personnage perso2 = this.personnageNonMisEnJeu.get(1);
		
		if(!perso1.aUnPouvoirSpecial() && !perso2.aUnPouvoirSpecial()) { // deux simples villageois
			int nb = (int) (Math.random() * ( this.personnageNonMisEnJeu.size() + 1    - 0 ));
			if(nb != this.personnageNonMisEnJeu.size()  ) {
				Logger.log("Le voleur a choisie la carte " + this.personnageNonMisEnJeu.get(nb) + " parmis ces cartes  "  + this.personnageNonMisEnJeu + "." , TypeDeLog.role);
				this.personnageChoisie = this.personnageNonMisEnJeu.get(nb);
			}
			else {
				Logger.log("Le voleur n'a choisie aucune carte entre "  + perso1 + " et " + perso2 + ".", TypeDeLog.role);
			}
		}
		
		else if(perso1.aUnPouvoirSpecial() && perso2.aUnPouvoirSpecial()) { // deux spés
			int nb = (int) (Math.random() * ( this.personnageNonMisEnJeu.size()  - 0 ));
			if(nb != this.personnageNonMisEnJeu.size()  ) {
				Logger.log("Le voleur a choisie la carte " + this.personnageNonMisEnJeu.get(nb) + " parmis ces cartes  "  + this.personnageNonMisEnJeu + "." , TypeDeLog.role);
				this.personnageChoisie = this.personnageNonMisEnJeu.get(nb);
			}
			
		}
		
		else if(perso1.aUnPouvoirSpecial() && !perso2.aUnPouvoirSpecial()) {
			Logger.log("Le Voleur a choisie la carte " + perso1 + " entre cette carte et  "  + perso2 + "." , TypeDeLog.role);
			this.personnageChoisie = perso1;
			perso2.meurt();
		}
		else if(perso2.aUnPouvoirSpecial() && !perso1.aUnPouvoirSpecial()) {
			Logger.log("Le Voleur a choisie la carte " + perso2 + " entre cette carte et  "  + perso1 + "." , TypeDeLog.role);
			this.personnageChoisie = perso2;
			perso1.meurt();
		}
		
		
		
		if(this.personnageChoisie == null ) {
			statsVoleur.voler(new SimpleVillageois());
		}
		else {
			this.personnageChoisie.ajouterAllié(this);
			if(!this.personnageChoisie.estUnVillageois()) {
				this.getVillage().getMeute().enrolerUnLoupGarou( (LoupGarou) this.personnageChoisie);
				this.getVillage().getMeute().getMeute().stream().forEach(x->x.ajouterAllié(this)); // corriger
			}
			
			statsVoleur.voler(this.personnageChoisie);
		}
		
		
	}
	
	@Override
	public void agir() {
		if(this.personnageChoisie != null) {
			this.personnageChoisie.agir();
		}
	}
	
	@Override
	public void agirAprèsNuit() {
		if(this.personnageChoisie != null) {
			this.personnageChoisie.agirAprèsNuit();
		}
	}
	
	@Override
	public int getIdDeRole() {
		if(this.personnageChoisie != null) {
			return this.personnageChoisie.getIdDeRole();
		}
		else {
			return super.getIdDeRole();
		}
	}
	
	@Override
	public void meurt() {
		if(this.personnageChoisie != null) {
			 this.personnageChoisie.meurt();
		}
		super.meurt();
	}
	
	@Override
	public int getId() {
		if(this.personnageChoisie != null) {
			return this.personnageChoisie.getId();
		}
		else {
			return super.getId();
		}
	}
	
	@Override
	public ArrayList<Personnage> getAlliés() {
		if(this.personnageChoisie != null) {
			return this.personnageChoisie.getAlliés();
		}
		else {
			return super.getAlliés();
		}
	}
	
	@Override
	public boolean estUnVillageois() {
		if(this.personnageChoisie != null) {
			return this.personnageChoisie.estUnVillageois();
		}
		else {
			return super.estUnVillageois();
		}
	}
	
	@Override
	public List<TypeDePouvoir> getTypeDePouvoir() {
		if(this.personnageChoisie != null && this.personnageChoisie.aUnPouvoirSpecial()) {
			if(this.personnageChoisie.estUnVillageois()) {
				return ((VillageoisSpecial) this.personnageChoisie).getTypeDePouvoir() ;
			}
			else {
				return new ArrayList<>(Arrays.asList(TypeDePouvoir.Mort));
			}
		}
		else {
			return new ArrayList<>(Arrays.asList(TypeDePouvoir.Metamorphose));
		}
	}
	
	@Override
	public ArrayList<Personnage> getEnnemies() {
		if(this.personnageChoisie != null) {
			return this.personnageChoisie.getEnnemies();
		}
		else {
			return super.getEnnemies();
		}
	}
	
	public void ajouterAllié(Personnage allié) {
		if(this.personnageChoisie != null) {
			this.personnageChoisie.ajouterAllié(allié);
		}
		else {
			super.ajouterAllié(allié);
		}
	}
	
	
	public void resetListeDeVote() {
		if(this.personnageChoisie != null) {
			this.personnageChoisie.resetListeDeVote();
		}
		else {
			super.resetListeDeVote();
		}
	}
	
	
	public int voter() {
		if(this.personnageChoisie != null) {
			return this.personnageChoisie.voter();
		}
		else {
			return super.voter();
		}
	}
	
	public int elire() {
		if(this.personnageChoisie != null) {
			return this.personnageChoisie.elire();
		}
		else {
			return super.elire();
		}
	}
	
	public void tuer(Personnage personnage) {
		if(this.personnageChoisie != null) {
			this.personnageChoisie.tuer(personnage);
		}
		else {
			super.tuer(personnage);
		}
	}
	
	
	public ArrayList<Personnage> getPersonnageNonMisEnJeu() {
		return personnageNonMisEnJeu;
	}
	
	

	public Personnage getPersonnageChoisie() {
		return personnageChoisie;
	}

	@Override
	public String toString() {
		if(this.personnageChoisie != null) {
			return this.personnageChoisie.toString() + "(voleur)";
		}
		else {
			if(this.getVillage() != null && this.getVillage().getVillage().stream().anyMatch(x->x.getIdDeRole() == this.getIdDeRole() && x != this)) {
				return "le voleur" + this.getId();
			}
			else {
				return "le voleur";
			}
		}
		
		
	}
	
	public void reset() {
		super.reset();
		this.personnageChoisie = null;
		this.personnageNonMisEnJeu = new ArrayList<Personnage>(2);
	}
	
}
