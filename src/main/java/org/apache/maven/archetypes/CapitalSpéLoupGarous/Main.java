package org.apache.maven.archetypes.CapitalSpéLoupGarous;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Chasseur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Cupidon;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Maire;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Salvateur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Sorcière;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Voyante;



public class Main {
	
	
	private static int compteur = 0;

	public static void main(String[] args) {
		int nbVillageois = 3;
		int nbLoupGarous = 2;
		int nbPartie = 100000;//100000
		
		Village village = new Village(nbVillageois,nbLoupGarous);
		Logger logger = new Logger();
		
		
		//logger.setDetailVoteVillage(true);
		//log.setOnFichierOutput();
		logger.setOffAfficherLogDetailsPartie();
		logger.setOnAfficherLogStats();
		//logger.setOnAfficherLogDetailsPourcentage();
		//logger.setOnAfficherLogDetailsRoleAction();
		
		Cupidon cupidon = new Cupidon();
		village.ajouterPersonnage(cupidon);
		Chasseur chasseur = new Chasseur();
		village.ajouterPersonnage(chasseur);
		Sorcière sorcière = new Sorcière();
		village.ajouterPersonnage(sorcière);
		Salvateur salvateur = new Salvateur();
		village.ajouterPersonnage(salvateur);
		Voyante voyante = new Voyante();
		village.ajouterPersonnage(voyante);
		village.onMaire();
		
		Partie partie = new Partie(village, logger);
		
		/*
		 * Sur 100000 parties, les villageois ont eu un taux de victoire de 31.477001190185547%
Sur 100000 parties, les loups-garous ont eu un taux de victoire de 43.145999908447266%
Sur 100000 parties, les amoureux ont eu un taux de victoire de 25.376998901367188%
		 */
		
		partie.simulation(nbPartie);
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


	
	public void test(String mot,String mot2, String[] tab, int compteur, int instance) {
		for(int i =0 ; i < tab.length ; i++) {
			//System.out.println(" instance " +  instance);
			mot += tab[i];
			//System.out.println("etape : " + mot + " compteur "+  compteur);
			while(mot.length() <  tab.length ) {
				mot2 += tab[i];
				test(mot,mot2, tab,compteur, instance +1);
			}
			//System.out.println("mot = " + mot);
			compteur--;
			// mot = mot2;
			if(compteur ==0 ) {
				return ;
			}
		}
		
	}

}
