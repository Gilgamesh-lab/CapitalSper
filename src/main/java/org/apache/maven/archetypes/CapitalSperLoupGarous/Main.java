package org.apache.maven.archetypes.CapitalSperLoupGarous;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Chasseur;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Corbeau;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Cupidon;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.DeuxSoeurs;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.MontreurDOurs;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Salvateur;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Sorciere;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Voleur;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Voyante;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsVoleur;



public class Main {
	
	

	public static void main(String[] args) {
		
		int nbVillageois = 5;
		int nbLoupGarous = 3;
		int nbPartie = 1;//100000
		
		Village village = new Village(nbVillageois,nbLoupGarous);
		Logger logger = new Logger();
		//System.out.println(Referentiel.getIdPersonnageDisponible());
		
		//logger.setDetailVoteVillage(true);
		//logger.setOnFichierOutput();
		//logger.setOffAfficherLogDetailsPartie();
		//logger.setOffAfficherLogStats();
		//logger.setOnAfficherLogDetailsPourcentage();
		//logger.setOnAfficherLogDetailsRoleAction();
		
		logger.setModeSpectateurOn();
		//logger.setModeStatistiqueOn();
		
		//village.ajouterPersonnage(Voleur.IDROLE);
		//System.out.println(StatsVoleur.);
		Cupidon cupidon = new Cupidon();
		village.ajouterPersonnage(cupidon);
		Chasseur chasseur = new Chasseur();
		village.ajouterPersonnage( chasseur);
		Sorciere sorcière = new Sorciere();
		village.ajouterPersonnage(sorcière);
		Voyante voyante = new Voyante();
		village.ajouterPersonnage(voyante);
		
		Salvateur salvateur = new Salvateur();
		village.ajouterPersonnage(salvateur);
		/*village.ajouterPersonnage(Corbeau.IDROLE);
		village.ajouterPersonnage(DeuxSoeurs.IDROLE);
		
		*/
		//village.ajouterPlusieursPersoIdentique(Voyante.IDROLE, 6);
		//village.onMaire();
		//village.ajouterPlusieursPersoIdentique(Cupidon.IDROLE, 4);
		MeneurDeJeu meneurDeJeu = new MeneurDeJeu(village, logger);
		//meneurDeJeu.exploration();
		
		
		
		meneurDeJeu.lancerDesParties(nbPartie);
		/*double lg = partie.getPourcentWinLoupGarous();
		double vi = partie.getPourcentWinVillage();*/
		
		
		//partie.exploration();
		
		
		
		
		
		 // corriger bug lorsque nb loup sup > 1 (cause : dupplication des branches,
		/*double lg2 = partie.getPourcentWinLoupGarous();
		double vi2 = partie.getPourcentWinVillage();
		
		System.out.println(lg);
		System.out.println(vi);
		System.out.println(lg2);
		System.out.println(vi2);*/
		
		/*50.78125%
Sur 5 parties, les loups-garous ont eu un taux de victoire de 49.218742%*/
		// partie.startExploration("111");
		
		/*
		 palier 1 = 1 (+ 0)
		 palier 2 = 3 (+1) 3 = 2*1 +1 
		 palier 3 = 5( +2) 5 = 2*2 +1
		 */
		/*int t = 7;
		int b = t /2 ;
		
		System.out.println(b);*/
		/*String test = "00011";
		long count = test.chars().filter(ch -> ch == '1').count();*/

		
		
		// certaine branches continue même après la victoire des loups-garous
		
		
		
		
	}

	
	
	
	
	
	
	/*
	 * // Current combination is ready to be printed, print it
        if (Array_Index == r) {
            for (int x=0; x<r; x++) {
                System.out.print(Empty_Array[x]+" ");
            }
            System.out.println("");
            return;
        }

        for (int y=Start_Element; y<=End_Element && End_Element-y+1 >= r-Array_Index; y++) {
        	Empty_Array[Array_Index] = Input_Array[y];
        	CombinationPossible(Input_Array, Empty_Array, y+1, End_Element, Array_Index+1, r);
        }
	 */

    /*static void Print_Combination(int Input_Arrary[], int n, int r) {
        int Empty_Array[]=new int[r];
        CombinationPossible(Input_Arrary, Empty_Array, 0, n-1, 0, r);
	}*/

	
	


}
