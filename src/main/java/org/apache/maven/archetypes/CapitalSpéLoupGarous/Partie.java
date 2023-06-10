package org.apache.maven.archetypes.CapitalSpéLoupGarous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Partie {
	private Village savegardeVillage;
	private Village village ;
	private Integer nbTour;
	private Map<String ,Integer > tabResult = new HashMap<>();
	private List<Integer> donnée = new ArrayList<Integer>();
	public static  Log log;
	
	public Partie(Village village) {
		this.savegardeVillage = village;
		this.log = new Log();
		this.nbTour = 0;
	}
	
	public Partie(Village village, Log log) {
		this.savegardeVillage = village;
		this.log = log;
		this.nbTour = 0;
	}
	
	private int start () {
		init();
		System.out.println();
		System.out.println("Lancement de la partie avec " +  this.village.getNbVillageois() + " villageois et " + this.village.getNbLoupGarou() + " loup-garous");
		System.out.println();
		this.village.getMeute().attaquerVillage();
		while(this.village.getNbLoupGarou() != 0 && this.village.getNbLoupGarou() * 2 < this.village.getNbPersonnage()) {
			this.village.voter();
			if(this.village.getNbLoupGarou() != 0 && this.village.getNbLoupGarou() * 2 < this.village.getNbPersonnage()) {
				this.village.getMeute().attaquerVillage();
			}
			this.nbTour++;
		}
		System.out.println();
		if(this.village.getNbLoupGarou() * 2 >= this.village.getNbPersonnage() ) {
			System.out.println("Victoire des Loups-Garous en " + this.nbTour + " tours");
			System.out.println(this.village.getNbLoupGarou() + " Loup(s)-Garou(s) survivant(s)");
			this.donnée.add(this.nbTour) ;
			return -1 ;  
		}
		else if (this.village.getNbLoupGarou() == 0) {  
			System.out.println("Victoire des villageois en " + this.nbTour + " tours");
			System.out.println(this.village.getNbVillageois() + " villageois survivant(s)");
			donnée.add(this.nbTour) ;
			return 1;
		}   
		else {
			System.out.println("Erreur");
			return 0;
		}
	}
	
	public void init() {
		this.nbTour = 0;
		this.village = new Village();
		for(int i = 0; i < this.savegardeVillage.getNbPersonnage(); i++) {
			this.village.ajouterPersonnage(this.savegardeVillage.getPersonnage(i));
		}
	}
	
	// idée : générer un nombre binaire, dont chacun des nombres correspond à la mort d'un lg ou vi
	// qui explorait toutes les possiblilités d'une partie
	
	public void simulation(int nb) {
		int compteur ;
		float nbVictoireVillage = 0;
		float nbVictoireLoupGarou = 0 ;
		int resultat = 0;
		
		for (compteur = 0; compteur < nb ; compteur ++) {
			resultat = this.start();
			if(resultat == 1) {
				nbVictoireVillage++;
			}
			
			else if(resultat == -1) {
				nbVictoireLoupGarou++;
			}
		}
		if(nb > 1 ) {
			System.out.println();
			System.out.println("Sur " + compteur + " parties, les villageois ont eu un taux de victoire de " + ((nbVictoireVillage / compteur) * 100 ) + "%");
			System.out.println("Sur " + compteur + " parties, les loups-garous ont eu un taux de victoire de " + ((nbVictoireLoupGarou / compteur) * 100 ) + "%");
			System.out.println("Le nombre minimun de tour est de " +  this.donnée.stream().reduce(Integer::min).get());
			System.out.println("Le nombre maximun de tour est de " +  this.donnée.stream().reduce(Integer::max).get());
			System.out.println();
		}
		/*System.out.println("Les villageois ont gagnés " + nbVictoireVillage + " sur " + compteur + " parties" );
		System.out.println("Les loup-garous ont gagnés " + nbVictoireLoupGarou + " sur " + compteur + " parties" );*/
	}
	


}
