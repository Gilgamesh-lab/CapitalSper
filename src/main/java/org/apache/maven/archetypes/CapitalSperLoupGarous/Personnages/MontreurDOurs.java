package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsMontreursDOurs;

public class MontreurDOurs extends VillageoisSpecial {
	private Personnage voisinDeDroite;
	private Personnage voisinDeGauche;
	private Boolean aTrouverUnLoup;
	public final static int IDROLE = 11;
	private static StatsMontreursDOurs statsMontreursDOurs = new StatsMontreursDOurs();

	public MontreurDOurs() {
		super(IDROLE, statsMontreursDOurs);
		this.voisinDeDroite = null;
		this.voisinDeGauche = null;
		this.aTrouverUnLoup = null;
	}
	
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Voyance));
	}
	
	@Override
	public int voter() {
		int persoVoter = super.voter();
		statsMontreursDOurs.voter(this.getVillage().getPersonnageParId(persoVoter));
		return persoVoter;
	}
	
	public Personnage[] initVoisins() {
		ArrayList<Personnage> liste = new ArrayList<Personnage>(this.getVillage().getHabitantsEnVie().stream().filter(x-> x != this).collect(Collectors.toList()));
		int nbVoisinDeDroite = (int) (Math.random() * ( liste.size()    - 0 ));
		int nbVoisinDeGauche = (int) (Math.random() * ( liste.size()    - 0 ));
		while(nbVoisinDeDroite == nbVoisinDeGauche) {
			nbVoisinDeGauche = (int) (Math.random() * ( liste.size()    - 0 ));
		}
		Personnage[] nums = {liste.get(nbVoisinDeDroite),liste.get(nbVoisinDeGauche) };
		return nums ;
		
		
		
	}
	
	public static StatsMontreursDOurs getStatsMontreursDOurs() {
		return statsMontreursDOurs;
	}

	public static void setStatsMontreursDOurs(StatsMontreursDOurs statsMontreursDOurs) {
		MontreurDOurs.statsMontreursDOurs = statsMontreursDOurs;
	}

	public void setVoisins(Personnage[] liste ) {
		this.voisinDeDroite = liste[0];
		this.voisinDeGauche = liste[1];
	}
	
	public ArrayList<Personnage> getVoisins() {
		ArrayList<Personnage> liste = new ArrayList<Personnage>(2);
		liste.add(this.voisinDeDroite);
		liste.add(this.voisinDeGauche);
		return liste;
	}
	
	public void traquerLoupGarous() {
		boolean nouveauVoisinADroite = false;
		boolean nouveauVoisinAGauche = false;
		boolean voisinGaucheCoupableSure = false;
		boolean voisinDroitCoupableSure = false;
		
		if(this.aTrouverUnLoup == null) {
			this.aTrouverUnLoup = false;
		}
		
		if(!this.voisinDeDroite.estEnvie()) {
			if(this.aTrouverUnLoup && this.voisinDeDroite.estUnVillageois() && this.voisinDeGauche.estEnvie() ) {// Si il avait trouver un loup-garou parmis ses voisins et que son ancien(=mort) voisin de droite était innocent
				//System.out.println("droite");
				voisinGaucheCoupableSure = true;
			}
			ArrayList<Personnage> liste = new ArrayList<Personnage>(this.getVillage().getHabitantsEnVie().stream().filter(x-> x != this &&x != this.voisinDeGauche).collect(Collectors.toList()));
			int nbVoisinDeDroite = (int) (Math.random() * ( liste.size()    - 0 ));
			this.voisinDeDroite = liste.get(nbVoisinDeDroite);
			nouveauVoisinADroite = true;
			this.getStatsMontreursDOurs().incrementerNbVoisinDifférent();
			
		}
		if(!this.voisinDeGauche.estEnvie()) {
			if(this.aTrouverUnLoup && this.voisinDeGauche.estUnVillageois() && this.voisinDeDroite.estEnvie() ) {// Si il avait trouver un loup-garou parmis ses voisins et que son ancien(=mort) voisin de gauche était innocent
				//System.out.println("gauche");
				voisinDroitCoupableSure = true;
			}
			ArrayList<Personnage> liste = new ArrayList<Personnage>(this.getVillage().getHabitantsEnVie().stream().filter(x-> x != this && x != this.voisinDeDroite).collect(Collectors.toList()));
			int nbVoisinDeGauche = (int) (Math.random() * ( liste.size()    - 0 ));
			this.voisinDeGauche = liste.get(nbVoisinDeGauche);
			nouveauVoisinAGauche = true;
			this.getStatsMontreursDOurs().incrementerNbVoisinDifférent();
		}
		
		this.getStatsMontreursDOurs().incrementerNbLoupGarouVoisin(voisinDeGauche, voisinDeDroite);
		
		if(!this.voisinDeDroite.estUnVillageois() || !this.voisinDeGauche.estUnVillageois()) {
			this.getStatsMontreursDOurs().incrementerNbGrognement();
			if(!voisinDroitCoupableSure && !voisinGaucheCoupableSure) {
				if(!this.aTrouverUnLoup &&  nouveauVoisinADroite && !nouveauVoisinAGauche ) {// Si il a détecter un loups parmis ses voisins depuis l'arrivé d'un nouveau voisin à sa droite
					this.ajouterEnnemie(this.voisinDeDroite);
				}
				
				else if(!this.aTrouverUnLoup  &&  nouveauVoisinAGauche && !nouveauVoisinADroite ) {// Si il a détecter un loups parmis ses voisins depuis l'arrivé d'un nouveau voisin à sa droite
					this.ajouterEnnemie(this.voisinDeGauche);
				}
				else {
					this.ajouterEnnemie(this.voisinDeDroite);
					this.ajouterEnnemie(this.voisinDeGauche);
				}
				this.aTrouverUnLoup = true;
				
				
			}
			else {
				//System.out.println("ok");
			}
		}
		else {
			this.ajouterAllié(voisinDeDroite);
			this.ajouterAllié(voisinDeGauche);
			if(this.getEnnemies().contains(voisinDeDroite)) {// Si un innocent a été considéré comme un ennemies car l'autre voisin l'était
				this.getEnnemies().remove(voisinDeDroite);
			}
			if(this.getEnnemies().contains(voisinDeGauche)) {// Si un innocent a été considéré comme un ennemies car l'autre voisin l'était
				this.getEnnemies().remove(voisinDeGauche);
			}
			this.aTrouverUnLoup = false;
		}
	}
	
	public boolean aTrouverUnLoup() {
		Logger.log("Le montreurs d'ours a pour voisins " + this.getVoisins() + "." , TypeDeLog.role );
		return this.aTrouverUnLoup;
	}
	
	public void meurt() {
		ArrayList<Personnage> listeVoisinsEnVie = new ArrayList<Personnage>(this.getVoisins().stream().filter(x->x.estEnvie()).collect(Collectors.toList()));
		if(this.aTrouverUnLoup != null) {
			if(this.aTrouverUnLoup) { // si un loups parmis ses voisins
				Logger.log("Suite à la mort du montreurs d'ours, " + listeVoisinsEnVie + " sont considérer comme potentiellement coupables." );
				this.getVillage().setPersoDevoilerCommeEnnemieParMontreursDOurs(listeVoisinsEnVie);
				
			}
			else {// sinon
				Logger.log("Suite à la mort du montreurs d'ours, " + listeVoisinsEnVie + " sont considérer comme innocent." );
				this.getVillage().setPersoDevoilerCommeAlliéeParMontreursDOurs(listeVoisinsEnVie);
			}
		}
		super.meurt();
		this.getStatsMontreursDOurs().incrementerNbMort();
	}
	
	public void reset() {
		super.reset();
		this.voisinDeDroite = null;
		this.voisinDeGauche = null;
		this.aTrouverUnLoup = null;
	}

	@Override
	public void agirPremiereNuit() {
		this.setVoisins(initVoisins());
	}

	@Override
	public void agirAprèsNuit() {
		this.traquerLoupGarous();
		if(this.aTrouverUnLoup()) {
			Logger.log("Le montreur d'ours a trouvé au moins un loup garous parmis ses voisins qui sont " + this.getVoisins() + ".");
		}
		
	}
	
	@Override
	public String toString() {
		if(this.getVillage() != null && this.getVillage().getVillage().stream().anyMatch(x->x.getIdDeRole() == this.getIdDeRole() && x != this)) {
			return "le montreur d'ours" + this.getId();
		}
		else {
			return "le montreur d'ours";
		}
		
	}

}
