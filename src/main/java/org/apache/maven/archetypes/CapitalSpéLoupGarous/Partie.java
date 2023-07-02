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
	private double pourcentWinVillage = 0;
	private double pourcentWinLoupGarous = 0;
	private double pourcentÉgalité = 0;
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
		Log.println("");
		Log.println("Lancement de la partie avec " +  this.village.getNbVillageois() + " villageois et " + this.village.getNbLoupGarou() + " loup-garous");
		Log.println("");
		if(this.village.getNbLoupGarou() != 0) {
			this.village.getMeute().attaquerVillage();
		}
		while(this.village.getNbLoupGarou() != 0 && this.village.getNbLoupGarou() * 2 < this.village.getNbPersonnage()) {
			this.village.voter();
			if(this.village.getNbLoupGarou() != 0 && this.village.getNbLoupGarou() * 2 < this.village.getNbPersonnage()) {
				this.village.getMeute().attaquerVillage();
			}
			this.nbTour++;
		}
		Log.println("");
		if(this.village.getNbLoupGarou() * 2 >= this.village.getNbPersonnage() && this.village.getNbLoupGarou() != 0 ) {
			Log.println("Victoire des Loups-Garous en " + this.nbTour + " tours");
			Log.println(this.village.getNbLoupGarou() + " Loup(s)-Garou(s) survivant(s)");
			this.listeTours.add(this.nbTour) ;
			this.nbVictoireLoupGarou++;
		}
		else if (this.village.getNbLoupGarou() == 0 && this.village.getNbVillageois() != 0) { 
			Log.println("Victoire des villageois en " + this.nbTour + " tours");
			Log.println(this.village.getNbVillageois() + " villageois survivant(s)");
			this.listeTours.add(this.nbTour);
			this.nbVictoireVillage++;
		}   
		else {
			Log.println("Égalité");
			this.listeTours.add(this.nbTour) ;
			this.nbÉgalité++;
		}
	}
	
	public void startExploration (String branche) {
		init();
		double pourcentageBranche = 1;
		Log.println("");
		Log.println("Lancement de la partie suivant la branche " + branche + " avec " + this.village.getNbVillageois() + " villageois et " + this.village.getNbLoupGarou() + " loup-garous");
		Log.println("");
		this.compteur++;
		if(this.village.getNbLoupGarou() != 0) {
			this.village.getMeute().attaquerVillage();
		}
		while(this.village.getNbLoupGarou() != 0 && this.village.getNbLoupGarou() * 2 < this.village.getNbPersonnage()) {
			if(branche.charAt(this.nbTour) == '0') {
				pourcentageBranche *= (double) this.village.getNbVillageois() / this.village.getNbPersonnage();
			}
			else {
				pourcentageBranche *= (double) this.village.getNbLoupGarou() / this.village.getNbPersonnage();
			}
			this.village.voter(branche.charAt(this.nbTour));
			if(this.village.getNbLoupGarou() != 0 && this.village.getNbLoupGarou() * 2 < this.village.getNbPersonnage()) {
				this.village.getMeute().attaquerVillage();
			}
			this.nbTour++;
		}
		
		if(this.village.getNbLoupGarou() >= this.village.getNbVillageois() && this.village.getNbLoupGarou() != 0 ) {
			Log.println("Victoire des Loups-Garous en " + this.nbTour + " tours");
			Log.println(this.village.getNbLoupGarou() + " Loup(s)-Garou(s) survivant(s)");
			this.listeTours.add(this.nbTour) ;
			this.nbVictoireLoupGarou++;
			this.pourcentWinLoupGarous += pourcentageBranche;
			
		}
		else if (this.village.getNbLoupGarou() == 0 && this.village.getNbVillageois() != 0) {  
			Log.println("Victoire des villageois en " + this.nbTour + " tours");
			Log.println(this.village.getNbVillageois() + " villageois survivant(s)");
			listeTours.add(this.nbTour) ;
			this.nbVictoireVillage++;
			this.pourcentWinVillage += pourcentageBranche;
		}   
		else {
			Log.println("Égalité");
			listeTours.add(this.nbTour) ;
			this.nbÉgalité++;
			this.pourcentÉgalité += pourcentageBranche;
		}
	}
	
	
	public void exploration() {
		this.reset();
		if (log.isFichierOutput()) {
			log.start("Exploration", savegardeVillage);
		}
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
		//Log.println(TourMaximal);
		this.explorationBranche(tab, "", TourMaximal, 0);
		
		this.pourcentWinVillage = this.pourcentWinVillage * 100;
		this.pourcentWinLoupGarous = this.pourcentWinLoupGarous * 100;
		
		Log.println("");
		Log.println("Sur " + compteur + " parties, les villageois ont eu un taux de victoire de " + this.pourcentWinVillage + "%");
		Log.println("Sur " + compteur + " parties, les loups-garous ont eu un taux de victoire de " + this.pourcentWinLoupGarous  + "%");
		Log.println("");
		Log.println("Le nombre minimun de tour est de " +  this.listeTours.stream().reduce(Integer::min).get());
		Log.println("Le nombre maximun de tour est de " +  this.listeTours.stream().reduce(Integer::max).get());
		Log.println("Le nombre moyen de tour est de " +  this.listeTours.stream().mapToInt(e -> e).average().getAsDouble());
		Log.println("");
		
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
					//Log.println("point = " + point );
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
	
	
	private void init() {
		this.nbTour = 0;
		this.village = new Village();
		for(int i = 0; i < this.savegardeVillage.getNbPersonnage(); i++) {
			this.village.ajouterPersonnage(this.savegardeVillage.getPersonnage(i));
		}
	}
	
	private void reset() {
		this.pourcentWinLoupGarous = 0;
		this.pourcentWinVillage = 0;
		this.nbVictoireLoupGarou = 0;
		this.nbVictoireVillage = 0;
	}
	
	
	
	public void simulation(int nb) {
		this.reset();
		if (log.isFichierOutput()) {
			String mode = "Simultation sur " + nb + " parties";
			log.start(mode, savegardeVillage);
		}
		int compteur ;

		
		for (compteur = 0; compteur < nb ; compteur ++) {
			this.startSimulation();
		}
		this.pourcentWinLoupGarous = (double) ((this.nbVictoireLoupGarou / compteur) * 100 );
		this.pourcentWinVillage = (double) ((this.nbVictoireVillage / compteur) * 100 );
		
		log.setOnAfficherLogDetailsPartie();
		Log.println("");
		Log.println("Sur " + compteur + " parties, les villageois ont eu un taux de victoire de " + this.pourcentWinVillage  + "%");
		Log.println("Sur " + compteur + " parties, les loups-garous ont eu un taux de victoire de " + this.pourcentWinLoupGarous + "%");
		Log.println("");
		Log.println("Le nombre minimun de tour est de " +  this.listeTours.stream().reduce(Integer::min).get());
		Log.println("Le nombre maximun de tour est de " +  this.listeTours.stream().reduce(Integer::max).get());
		Log.println("Le nombre moyen de tour est de " +  this.listeTours.stream().mapToInt(e -> e).average().getAsDouble());
		Log.println("");
		log.setOffAfficherLogDetailsPartie();
		
		/*Log.println("Les villageois ont gagnés " + nbVictoireVillage + " sur " + compteur + " parties" );
		Log.println("Les loup-garous ont gagnés " + nbVictoireLoupGarou + " sur " + compteur + " parties" );*/
	}

	

	public double getPourcentÉgalité() {
		return pourcentÉgalité;
	}

	public double getPourcentWinVillage() {
		return pourcentWinVillage;
	}

	public double getPourcentWinLoupGarous() {
		return pourcentWinLoupGarous;
	}
	
	
	
	
	
	


}
