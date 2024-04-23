package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;

public class MontreurDOurs extends VillageoisSpecial {
	private Personnage voisinDeDroite;
	private Personnage voisinDeGauche;
	private Boolean aTrouverUnLoup;
	public final static int IDROLE = 11;

	public MontreurDOurs() {
		super(IDROLE, Arrays.asList(TypeDePouvoir.Voyance));
		this.voisinDeDroite = null;
		this.voisinDeGauche = null;
		this.aTrouverUnLoup = null;
	}
	
	public void setVoisins() {
		ArrayList<Personnage> liste = new ArrayList<Personnage>(this.getVillage().getHabitantsEnVie().stream().filter(x-> x != this).collect(Collectors.toList()));
		int nbVoisinDeDroite = (int) (Math.random() * ( liste.size()    - 0 ));
		int nbVoisinDeGauche = (int) (Math.random() * ( liste.size()    - 0 ));
		while(nbVoisinDeDroite == nbVoisinDeGauche) {
			nbVoisinDeGauche = (int) (Math.random() * ( liste.size()    - 0 ));
		}
		
		this.voisinDeDroite = liste.get(nbVoisinDeDroite);
		this.voisinDeGauche = liste.get(nbVoisinDeGauche);
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
		}
		
		if(!this.voisinDeDroite.estUnVillageois() || !this.voisinDeGauche.estUnVillageois()) {
			if(!voisinDroitCoupableSure && !voisinGaucheCoupableSure) {
				if(!this.aTrouverUnLoup &&  nouveauVoisinADroite && !nouveauVoisinAGauche ) {// Si il a détecter un loups parmis ses voisins depuis l'arrivé d'un nouveau voisin à sa droite
					this.ajouterEnnemies(this.voisinDeDroite);
				}
				
				else if(!this.aTrouverUnLoup  &&  nouveauVoisinAGauche && !nouveauVoisinADroite ) {// Si il a détecter un loups parmis ses voisins depuis l'arrivé d'un nouveau voisin à sa droite
					this.ajouterEnnemies(this.voisinDeGauche);
				}
				// a adapter ici
				else {
					this.ajouterEnnemies(this.voisinDeDroite);
					this.ajouterEnnemies(this.voisinDeGauche);
				}
				this.aTrouverUnLoup = true;
			}
			else {
				//System.out.println("ok");
			}
		}
		else {
			this.ajouterAlliés(voisinDeDroite);
			this.ajouterAlliés(voisinDeGauche);
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
		Logger.log("Le montreurs d'ours a pour voisins " + this.getVoisins() , TypeDeLog.role );
		return this.aTrouverUnLoup;
	}
	
	public void meurt() {
		ArrayList<Personnage> listeVoisinsEnVie = new ArrayList<Personnage>(this.getVoisins().stream().filter(x->x.estEnvie()).collect(Collectors.toList()));
		if(this.aTrouverUnLoup != null) {
			if(this.aTrouverUnLoup) { // si un loups parmis ses voisins
				this.getVillage().getHabitantsEnVie().stream().filter(x-> x.estUnVillageois()).forEach(x->x.ajouterEnnemies(this.voisinDeDroite));
				this.getVillage().getHabitantsEnVie().stream().filter(x-> x.estUnVillageois()).forEach(x->x.ajouterEnnemies(this.voisinDeGauche));
				Logger.log("Suite à la mort du montreurs d'ours, " + listeVoisinsEnVie + " sont considérer comme potentiellement coupables" );
				
			}
			else {// sinon
				this.getVillage().getHabitantsEnVie().stream().filter(x-> x.estUnVillageois()).forEach(x->x.ajouterAlliés(this.voisinDeDroite));
				this.getVillage().getHabitantsEnVie().stream().filter(x-> x.estUnVillageois()).forEach(x->x.ajouterAlliés(this.voisinDeGauche));
				Logger.log("Suite à la mort du montreurs d'ours, " + listeVoisinsEnVie + " sont considérer comme innocent" );
			}
		}
		super.meurt();
	}
	
	public void reset() {
		super.reset();
		this.voisinDeDroite = null;
		this.voisinDeGauche = null;
		this.aTrouverUnLoup = null;
	}
	
	@Override
	public String toString() {
		return "le montreur d'ours";
	}

	@Override
	public void agirPremiereNuit() {
		this.setVoisins();
		
	}

	@Override
	public void agir() {
		this.traquerLoupGarous();
		
	}

}
