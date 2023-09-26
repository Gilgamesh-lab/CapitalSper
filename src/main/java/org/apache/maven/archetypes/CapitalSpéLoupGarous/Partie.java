package org.apache.maven.archetypes.CapitalSpéLoupGarous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Chasseur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Cupidon;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.TypeDeLog;

public class Partie {
	private static Village savegardeVillage;
	private Village village ;
	private Integer nbTour;
	private Map<String ,Integer > tabResult = new HashMap<>();
	private List<Integer> listeTours = new ArrayList<Integer>();
	public static  Logger log;
	private float nbVictoireVillage = 0;
	private float nbVictoireLoupGarou = 0 ;
	private float nbÉgalité = 0 ;
	private float nbVictoireAmoureux = 0 ;
	private double pourcentWinVillage = 0;
	private double pourcentWinLoupGarous = 0;
	private double pourcentÉgalité = 0;
	private double pourcentWinAmoureux = 0;
	private int compteur;
	private List<String> listeBranches = new ArrayList<String>();
	private Référentiel référentiel;
	
	public Partie(Village village) {
		this.savegardeVillage = village;
		this.log = new Logger();
		this.nbTour = 0;
		this.référentiel = new Référentiel();
	}
	
	public Partie(Village village, Logger log) {
		this.savegardeVillage = village;
		this.log = log;
		this.nbTour = 0;
		this.référentiel = new Référentiel();
	}
	
	public boolean conditionFinPartie() {
		return (this.village.getNbLoupGarou() != 0 && this.village.getNbLoupGarou() * 2 < this.village.getNbPersonnage() && !this.village.getHabitantsEnVie().stream().allMatch(x->x.estAmoureux()));
	}
	
	public boolean conditionVictoireAmoureux() {
		return this.village.getHabitantsEnVie().stream().allMatch(x->x.estAmoureux()) && this.village.getHabitantsEnVie().stream().anyMatch(x->x.estUnVillageois())  && this.village.getHabitantsEnVie().stream().anyMatch(y->!y.estUnVillageois());
	}
	
	public boolean conditionVictoireVillageois() {
		return this.village.getNbLoupGarou() == 0 && this.village.getNbVillageois() != 0;
	}
	
	public boolean conditionVictoireLoupGarous() {
		return this.village.getNbLoupGarou() * 2 >= this.village.getNbPersonnage() && this.village.getNbLoupGarou() != 0 && !(this.village.getNbLoupGarou() == 1 && this.village.getHabitantsEnVie().stream().anyMatch(x -> x.getIdDeRole() == 5) && this.village.getNbVillageois() == 1 );
	}
	
	public boolean conditionEgaliterChasseur() {
		return this.village.getNbLoupGarou() == 1 && this.village.getHabitantsEnVie().stream().anyMatch(x -> x.getIdDeRole() == 5) && this.village.getNbVillageois() == 1 ;
	}
	
	private void startSimulation () {
		init();
		Logger.log("");
		Logger.log("Lancement de la partie avec " +  this.référentiel.messageDebutPartie(this.village));
		Logger.log("");
		if(this.conditionFinPartie()) {
			this.village.premièreNuit();
			this.nbTour++;
			while(this.conditionFinPartie()) {
				this.village.voter();
				if(this.conditionFinPartie()){
					this.village.nuit();
				}
				this.nbTour++;
			}
		}
		Logger.log("");
		
		
		if(this.conditionVictoireAmoureux()) {
			Logger.log("Victoire des amoureux en " + this.nbTour + " tours");
			Logger.log(this.village.getNbVillageois() + " villageois et " +  this.village.getNbLoupGarou() + " Loup(s)-Garou(s) survivants");
			this.listeTours.add(this.nbTour);
			this.nbVictoireAmoureux++;
		}
		
		else if(this.conditionVictoireLoupGarous()) {
			Logger.log("Victoire des Loups-Garous en " + this.nbTour + " tours");
			Logger.log(this.village.getNbLoupGarou() + " Loup(s)-Garou(s) survivant(s)");
			this.listeTours.add(this.nbTour) ;
			this.nbVictoireLoupGarou++;
		}
		else if (this.conditionVictoireVillageois()) { 
			Logger.log("Victoire des villageois en " + this.nbTour + " tours");
			Logger.log(this.village.getNbVillageois() + " villageois survivant(s)");
			this.listeTours.add(this.nbTour);
			this.nbVictoireVillage++;
		}
		
		else {
			if(this.conditionEgaliterChasseur()) {
				Logger.log("Le Chasseur et le loups-garous survivant s'entretuent");
			}
			Logger.log("Égalité en " + this.nbTour + " tours");
			this.listeTours.add(this.nbTour) ;
			this.nbÉgalité++;
		}
	}
	
	public void injection (String branche) {
		init();
		double pourcentageBranche = 1;
		Logger.log("");
		Logger.log("Lancement de la partie suivant la branche " + branche + " avec " + this.village.getNbVillageois() + " villageois et " + this.village.getNbLoupGarou() + " loup-garous");
		Logger.log("");
		this.compteur++;
		//this.village.premièreNuit();
		while(this.conditionFinPartie()) {
			if(branche.charAt(this.nbTour) == '0') {
				pourcentageBranche *= (double) (this.village.getNbPersonnage() -1) / this.village.getNbPersonnage();
				Logger.log("Pourcentage entrant = " + (double) this.village.getNbVillageois() / this.village.getNbPersonnage(), TypeDeLog.pourcentage);
			}
			else {
				pourcentageBranche *= (double) 1 / this.village.getNbPersonnage();
				Logger.log("Pourcentage entrant = " + (double) this.village.getNbLoupGarou() / this.village.getNbPersonnage(), TypeDeLog.pourcentage);
				//this.village.getNbLoupGarou()
			}
			
			Logger.log("Pourcentage actuel = " + pourcentageBranche, TypeDeLog.pourcentage);
			this.village.voter(branche.charAt(this.nbTour));
			if(this.village.getNbLoupGarou() != 0 && this.village.getNbLoupGarou() * 2 < this.village.getNbPersonnage()) {
				this.village.nuit();
			}
			this.nbTour++;
			
		}
		
		if(this.conditionVictoireLoupGarous()) {
			Logger.log("Victoire des Loups-Garous en " + this.nbTour + " tours");
			Logger.log(this.village.getNbLoupGarou() + " Loup(s)-Garou(s) survivant(s)");
			this.listeTours.add(this.nbTour) ;
			this.nbVictoireLoupGarou++;
			this.pourcentWinLoupGarous += pourcentageBranche;
			
		}
		else if (this.conditionVictoireVillageois()) {  
			Logger.log("Victoire des villageois en " + this.nbTour + " tours");
			Logger.log(this.village.getNbVillageois() + " villageois survivant(s)");
			listeTours.add(this.nbTour) ;
			this.nbVictoireVillage++;
			this.pourcentWinVillage += pourcentageBranche;
		}   
		else {
			Logger.log("Égalité");
			listeTours.add(this.nbTour) ;
			this.nbÉgalité++;
			this.pourcentÉgalité += pourcentageBranche;
		}
	}
	
	
	public void exploration() {
		this.reset();
		if (log.isFichierOutput()) {
			log.écrireFichier("Exploration", savegardeVillage);
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
		this.pourcentÉgalité = this.pourcentÉgalité * 100;
		
		Logger.log("");
		Logger.log("Sur " + compteur + " parties, les villageois ont eu un taux de victoire de " + this.pourcentWinVillage + "%");
		Logger.log("Sur " + compteur + " parties, les loups-garous ont eu un taux de victoire de " + this.pourcentWinLoupGarous  + "%");
		if(this.nbÉgalité > 0) {
			Logger.log("Sur " + compteur + " parties, les villageois et les loups-garous ont terminés sur une égalité avec un taux de " + this.pourcentÉgalité + "%");
		}
		Logger.log("");
		Logger.log("Le nombre minimun de tour est de " +  this.listeTours.stream().reduce(Integer::min).get());
		Logger.log("Le nombre maximun de tour est de " +  this.listeTours.stream().reduce(Integer::max).get());
		Logger.log("Le nombre moyen de tour est de " +  this.listeTours.stream().mapToInt(e -> e).average().getAsDouble());
		Logger.log("");
		
		log.finish();
		
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
							this.injection(point);
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
		/*this.village = this.savegardeVillage;
		this.village.reset();
		this.village.getMeute().reset();*/
		
		if(this.savegardeVillage.getHabitantsEnVie().stream().anyMatch(x -> x.getIdDeRole() > 1)) { // Pas d'ajouts directe des persoonages car les status ne s'effacent et causes des erreurs
			ArrayList<Personnage> personnages = this.référentiel.conversionDeVillageVersListePersonnagesSeulementSpecial(this.savegardeVillage);
			this.village = new Village(this.savegardeVillage.getNbVillageois() - personnages.size(), this.savegardeVillage.getNbLoupGarou(), personnages);
		}
		else {
			this.village = new Village(this.savegardeVillage.getNbVillageois(), this.savegardeVillage.getNbLoupGarou());
		}
		if(this.savegardeVillage.isaUnmaire()) {
			this.village.onMaire();
		}
	}
	
	private void reset() {
		this.pourcentWinLoupGarous = 0;
		this.pourcentWinVillage = 0;
		this.nbVictoireLoupGarou = 0;
		this.nbVictoireVillage = 0;
		this.nbVictoireAmoureux = 0;
	}
	
	
	
	public void simulation(int nb) {
		long startTime = System.currentTimeMillis();
		this.reset();
		if(!log.isAfficherLogDetailsPartie()) {
			System.out.println("Chargement en cours");
		}
		if (log.isFichierOutput()) {
			String mode = "Simultation sur " + nb + " parties";
			log.écrireFichier(mode, savegardeVillage);
		}
		int compteur ;

		for (compteur = 0; compteur < nb ; compteur ++) {
			this.startSimulation();
			Logger.log("", TypeDeLog.pourcentage);
			Logger.log("Nombre de victoire des villageois = " + (int) this.nbVictoireVillage , TypeDeLog.pourcentage);
			Logger.log("Nombre de victoire des loups-garous = " + (int) this.nbVictoireLoupGarou , TypeDeLog.pourcentage);
			Logger.log("", TypeDeLog.pourcentage);
		}
		
		this.pourcentWinLoupGarous = (double) ((this.nbVictoireLoupGarou / compteur) * 100 );
		this.pourcentWinVillage = (double) ((this.nbVictoireVillage / compteur) * 100 );
		this.pourcentWinAmoureux = (double) ((this.nbVictoireAmoureux / compteur) * 100 );
		this.pourcentÉgalité = (double) ((this.nbÉgalité / compteur) * 100 );
		Collections.sort(this.listeTours);   
		
		
		log.setOnAfficherLogDetailsPartie();
		Logger.log("");
		Logger.log("Sur " + compteur + " parties, les villageois ont eu un taux de victoire de " + this.pourcentWinVillage  + "%");
		Logger.log("Sur " + compteur + " parties, les loups-garous ont eu un taux de victoire de " + this.pourcentWinLoupGarous + "%");
		savegardeVillage.reset();
		if(this.savegardeVillage.getHabitantsEnVie().stream().anyMatch(x -> x.getIdDeRole() == 4)) {
			Logger.log("Sur " + compteur + " parties, les amoureux ont eu un taux de victoire de " + this.pourcentWinAmoureux  + "%");
		}
		if(this.nbÉgalité > 0) {
			Logger.log("Sur " + compteur + " parties, les villageois et les loups-garous ont terminés sur une égalité avec un taux de " + this.pourcentÉgalité + "%");
		}
		Logger.log("");
		Logger.log("Le nombre minimun de tour est de " +  this.listeTours.stream().reduce(Integer::min).get());
		Logger.log("Le nombre maximun de tour est de " +  this.listeTours.stream().reduce(Integer::max).get());
		Logger.log("Le nombre moyen de tour est de " +  this.listeTours.stream().mapToInt(e -> e).average().getAsDouble());
		Logger.log("");
		Logger.log("Au moins 25% des parties se sont terminés en " +  this.listeTours.get((int) this.listeTours.size() / 4) + " tours ou moins"); // premier quartile
		Logger.log("Au moins 50% des parties se sont terminés en " +  this.listeTours.get((int) this.listeTours.size() / 2) + " tours ou moins"); // deuxième quartile ou médiane
		Logger.log("Au moins 75% des parties se sont terminés en " +  this.listeTours.get((int) (this.listeTours.size() *  3) / 4) + " tours ou moins"); // troisième quartile
		Logger.log("");
		
		// stat perso "simple" ex : taux de survie ?
		// stat persoSpé : nb lg kill by chasseur, utilisation potion soso, couple traitre ...
		long endTime = System.currentTimeMillis();
		long minute = (endTime-startTime) / 60000;
		long seconde = (  (endTime-startTime) -(minute*60000)) / 1000;
		Logger.log("");
		Logger.log("Le temps d'exécution de la simulation est de : " + minute +  " minutes et " + seconde + " secondes ", TypeDeLog.statistique );
		log.setOffAfficherLogDetailsPartie();
		log.finish();
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
