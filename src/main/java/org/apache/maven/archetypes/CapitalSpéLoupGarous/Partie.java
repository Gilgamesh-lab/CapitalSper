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
	private List<Integer> listeTours = new ArrayList<Integer>();
	public static  Log log;
	private float nbVictoireVillage = 0;
	private float nbVictoireLoupGarou = 0 ;
	private float nbÉgalité = 0 ;
	private float pourcentWinVillage = 0;
	private float pourcentWinLoupGarous = 0;
	private float pourcentÉgalité = 0;
	private int compteur;
	private List<String> listeBranches = new ArrayList<String>();
	
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
	
	private void startSimulation () {
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
		if(this.village.getNbLoupGarou() * 2 >= this.village.getNbPersonnage() && this.village.getNbLoupGarou() != 0 ) {
			System.out.println("Victoire des Loups-Garous en " + this.nbTour + " tours");
			System.out.println(this.village.getNbLoupGarou() + " Loup(s)-Garou(s) survivant(s)");
			this.listeTours.add(this.nbTour) ;
			this.nbVictoireLoupGarou++;
		}
		else if (this.village.getNbLoupGarou() == 0 && this.village.getNbVillageois() != 0) { 
			System.out.println("Victoire des villageois en " + this.nbTour + " tours");
			System.out.println(this.village.getNbVillageois() + " villageois survivant(s)");
			this.listeTours.add(this.nbTour);
			this.nbVictoireVillage++;
		}   
		else {
			System.out.println("Égalité");
			this.listeTours.add(this.nbTour) ;
			this.nbÉgalité++;
		}
	}
	
	public void startExploration (String branche) {
		init();
		float pourcentageBranche = 1;
		System.out.println();
		System.out.println("Lancement de la partie suivant la branche " + branche + " avec " + this.village.getNbVillageois() + " villageois et " + this.village.getNbLoupGarou() + " loup-garous");
		System.out.println();
		this.compteur++;
		this.village.getMeute().attaquerVillage();
		while(this.village.getNbLoupGarou() != 0 && this.village.getNbLoupGarou() * 2 < this.village.getNbPersonnage()) {
			if(branche.charAt(this.nbTour) == '0') {
				pourcentageBranche *= (float) this.village.getNbVillageois() / this.village.getNbPersonnage();
			}
			else {
				pourcentageBranche *= (float) this.village.getNbLoupGarou() / this.village.getNbPersonnage();
			}
			System.out.println("pourcentVictoire = " + pourcentageBranche);
			this.village.voter(branche.charAt(this.nbTour));
			if(this.village.getNbLoupGarou() != 0 && this.village.getNbLoupGarou() * 2 < this.village.getNbPersonnage()) {
				this.village.getMeute().attaquerVillage();
			}
			this.nbTour++;
		}
		System.out.println("pourcentVictoire final = " + pourcentageBranche);
		System.out.println();
		
		if(this.village.getNbLoupGarou() >= this.village.getNbVillageois() && this.village.getNbLoupGarou() != 0 ) {
			System.out.println("Victoire des Loups-Garous en " + this.nbTour + " tours");
			System.out.println(this.village.getNbLoupGarou() + " Loup(s)-Garou(s) survivant(s)");
			this.listeTours.add(this.nbTour) ;
			this.nbVictoireLoupGarou++;
			this.pourcentWinLoupGarous += pourcentageBranche;
			
		}
		else if (this.village.getNbLoupGarou() == 0 && this.village.getNbVillageois() != 0) {  
			System.out.println("Victoire des villageois en " + this.nbTour + " tours");
			System.out.println(this.village.getNbVillageois() + " villageois survivant(s)");
			listeTours.add(this.nbTour) ;
			this.nbVictoireVillage++;
			this.pourcentWinVillage += pourcentageBranche;
		}   
		else {
			System.out.println("Égalité");
			listeTours.add(this.nbTour) ;
			this.nbÉgalité++;
			this.pourcentÉgalité += pourcentageBranche;
		}
	}
	
	
	public void exploration() {
		String tab[] = new String[2];
		tab[0] = "0";
		tab[1] = "1";
		
		int TourMaximal = this.savegardeVillage.getNbVillageois();
		if(TourMaximal % 2 == 0) {
			TourMaximal -= 2;
		}
		else {
			TourMaximal -= 1;
		}
		TourMaximal /= 2;
		
		if(this.savegardeVillage.getNbLoupGarou() >= 3) {
			TourMaximal += this.savegardeVillage.getNbLoupGarou() / 2 ;
		}
		
		if(this.savegardeVillage.getNbLoupGarou() % 2 == 0 && this.savegardeVillage.getNbVillageois() % 2 == 0) {
			TourMaximal++;
		}
		//System.out.println(TourMaximal);
		this.explorationBranche(tab, "", TourMaximal, 0);
		
		
		System.out.println();
		System.out.println("Sur " + compteur + " parties, les villageois ont eu un taux de victoire de " + this.pourcentWinVillage * 100 + "%");
		System.out.println("Sur " + compteur + " parties, les loups-garous ont eu un taux de victoire de " + this.pourcentWinLoupGarous * 100+ "%");
		System.out.println();
		System.out.println("Le nombre minimun de tour est de " +  this.listeTours.stream().reduce(Integer::min).get());
		System.out.println("Le nombre maximun de tour est de " +  this.listeTours.stream().reduce(Integer::max).get());
		System.out.println("Le nombre moyen de tour est de " +  this.listeTours.stream().mapToInt(e -> e).average().getAsDouble());
		System.out.println();
		
	}
	
	
	private void explorationBranche(String tab[], String branche, int compteur, int tour) {
		compteur--;
		tour ++ ;
		String point = branche;
		
		for (int i=0; i < tab.length; i++) {
			long countLgmort = branche.chars().filter(ch -> ch == '1').count();
			
			
			if(countLgmort < this.savegardeVillage.getNbLoupGarou() || tab[i] != "1") { // pour pas loup mort soit compter
				//if(this.savegardeVillage.getNbVillageois() - countVimort - 1 > this.savegardeVillage.getNbLoupGarou() ||  tab[i] == "0") {
				//if(countVimort < this.savegardeVillage.getNbVillageois() - this.savegardeVillage.getNbLoupGarou() - 1 && tab[i] == "1") {
					point +=  tab[i];
					//System.out.println("point = " + point );
					long countVimort = point.chars().filter(ch -> ch == '0').count();
					countLgmort = branche.chars().filter(ch -> ch == '1').count();
					
					if (!listeBranches.contains(branche)){
						if(this.savegardeVillage.getNbVillageois() - countVimort - (branche.length() )  - (this.savegardeVillage.getNbLoupGarou() - countLgmort)  <= 0 || this.savegardeVillage.getNbLoupGarou() - countLgmort <= 0) { // compteur <= 0 remplacer cette condition par condition de victoire
							listeBranches.add(branche);
							this.startExploration(point);
						}
						else {
							explorationBranche(tab, point, compteur, tour);
						}
					point = branche;
				}
					
			}
		}
    }
	
	
	public void init() {
		this.nbTour = 0;
		this.village = new Village();
		for(int i = 0; i < this.savegardeVillage.getNbPersonnage(); i++) {
			this.village.ajouterPersonnage(this.savegardeVillage.getPersonnage(i));
		}
	}
	
	
	
	public void simulation(int nb) {
		int compteur ;

		
		for (compteur = 0; compteur < nb ; compteur ++) {
			this.startSimulation();
		}
		
		if(nb > 1 ) {
			System.out.println();
			System.out.println("Sur " + compteur + " parties, les villageois ont eu un taux de victoire de " + ((this.nbVictoireVillage / compteur) * 100 ) + "%");
			System.out.println("Sur " + compteur + " parties, les loups-garous ont eu un taux de victoire de " + ((this.nbVictoireLoupGarou / compteur) * 100 ) + "%");
			System.out.println();
			System.out.println("Le nombre minimun de tour est de " +  this.listeTours.stream().reduce(Integer::min).get());
			System.out.println("Le nombre maximun de tour est de " +  this.listeTours.stream().reduce(Integer::max).get());
			System.out.println("Le nombre moyen de tour est de " +  this.listeTours.stream().mapToInt(e -> e).average().getAsDouble());
			System.out.println();
		}
		/*System.out.println("Les villageois ont gagnés " + nbVictoireVillage + " sur " + compteur + " parties" );
		System.out.println("Les loup-garous ont gagnés " + nbVictoireLoupGarou + " sur " + compteur + " parties" );*/
	}
	


}
