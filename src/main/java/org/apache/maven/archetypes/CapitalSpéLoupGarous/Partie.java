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
	private float pourcentWinVillage = 0;
	private float pourcentWinLoupGarous = 0;
	private int compteur;
	
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
	
	private int startSimulation () {
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
			this.listeTours.add(this.nbTour) ;
			return -1 ;  
		}
		else if (this.village.getNbLoupGarou() == 0) {  
			System.out.println("Victoire des villageois en " + this.nbTour + " tours");
			System.out.println(this.village.getNbVillageois() + " villageois survivant(s)");
			listeTours.add(this.nbTour) ;
			return 1;
		}   
		else {
			System.out.println("Erreur");
			return 0;
		}
	}
	
	public void startExploration (String branche) {
		init();
		float pourcentVictoire = 1;
		System.out.println();
		System.out.println("Lancement de la partie suivant la branche " + branche + " avec " + this.village.getNbVillageois() + " villageois et " + this.village.getNbLoupGarou() + " loup-garous");
		System.out.println();
		this.compteur++;
		this.village.getMeute().attaquerVillage();
		while(this.village.getNbLoupGarou() != 0 && this.village.getNbLoupGarou() * 2 < this.village.getNbPersonnage()) {
			if(branche.charAt(this.nbTour) == '0') {
				pourcentVictoire *= (float) this.village.getNbVillageois() / this.village.getNbPersonnage();
			}
			else {
				pourcentVictoire *= (float) this.village.getNbLoupGarou() / this.village.getNbPersonnage();
			}
			System.out.println("pourcentVictoire = " + pourcentVictoire);
			this.village.voter(branche.charAt(this.nbTour));
			if(this.village.getNbLoupGarou() != 0 && this.village.getNbLoupGarou() * 2 < this.village.getNbPersonnage()) {
				this.village.getMeute().attaquerVillage();
			}
			this.nbTour++;
		}
		System.out.println("pourcentVictoire final = " + pourcentVictoire);
		System.out.println();
		if(this.village.getNbLoupGarou() * 2 >= this.village.getNbPersonnage() ) {
			System.out.println("Victoire des Loups-Garous en " + this.nbTour + " tours");
			System.out.println(this.village.getNbLoupGarou() + " Loup(s)-Garou(s) survivant(s)");
			this.listeTours.add(this.nbTour) ;
			this.nbVictoireLoupGarou++;
			this.pourcentWinLoupGarous += pourcentVictoire;
			
		}
		else if (this.village.getNbLoupGarou() == 0) {  
			System.out.println("Victoire des villageois en " + this.nbTour + " tours");
			System.out.println(this.village.getNbVillageois() + " villageois survivant(s)");
			listeTours.add(this.nbTour) ;
			this.nbVictoireVillage++;
			this.pourcentWinVillage += pourcentVictoire;
		}   
		/*else {
			System.out.println("Erreur");
			return 0;
		}*/
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
		
		if(this.savegardeVillage.getNbLoupGarou() >= 2) {
			TourMaximal += this.savegardeVillage.getNbLoupGarou() / 2 ;
		}
		
		
		
		
		explorationBranche(tab, "", TourMaximal);
		
		
		System.out.println();
		System.out.println("Sur " + compteur + " parties, les villageois ont eu un taux de victoire de " + this.pourcentWinVillage * 100 + "%");
		System.out.println("Sur " + compteur + " parties, les loups-garous ont eu un taux de victoire de " + this.pourcentWinLoupGarous * 100+ "%");
		System.out.println();
		System.out.println("Le nombre minimun de tour est de " +  this.listeTours.stream().reduce(Integer::min).get());
		System.out.println("Le nombre maximun de tour est de " +  this.listeTours.stream().reduce(Integer::max).get());
		System.out.println("Le nombre moyen de tour est de " +  this.listeTours.stream().mapToInt(e -> e).average().getAsDouble());
		System.out.println();
		
	}
	
	
	private void explorationBranche(String tab[], String branche, int compteur) {
		compteur--;
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
					// 1,6 en 00
					/*if(this.savegardeVillage.getNbVillageois() - countVimort * 2 -1 - this.savegardeVillage.getNbLoupGarou() ==0 && this.savegardeVillage.getNbLoupGarou() - countLgmort != 0  ) {
						System.out.println("true");
						this.startExploration(point);
						return;
					}*/
					if(compteur !=0) {
						explorationBranche(tab, point, compteur);
					}
					else {
						this.startExploration(point);
					}
					point = branche;
				//}
					
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
		float nbVictoireVillage = 0;
		float nbVictoireLoupGarou = 0 ;
		int resultat = 0;
		
		for (compteur = 0; compteur < nb ; compteur ++) {
			resultat = this.startSimulation();
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
