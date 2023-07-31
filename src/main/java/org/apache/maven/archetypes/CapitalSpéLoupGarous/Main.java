package org.apache.maven.archetypes.CapitalSpéLoupGarous;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Chasseur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Cupidon;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Sorcière;



public class Main {
	
	private static List<String> listeBranches = new ArrayList<String>();
	private static int compteur = 0;

	public static void main(String[] args) {
		int nbVillageois = 0;
		int nbLoupGarous = 1;
		int nbPartie = 5;//100000
		
		Village village = new Village(nbVillageois,nbLoupGarous);
		Logger log = new Logger();
		
		
		//log.setDetailVoteVillage(true);
		//log.setOnFichierOutput();
		//log.setOffAfficherLogDetailsPartie();
		//log.setOnAfficherLogDetailsPourcentage();
		log.setOnAfficherLogDetailsRoleAction();
		Cupidon cupidon = new Cupidon();
		village.ajouterPersonnage(cupidon);
		Chasseur chasseur = new Chasseur();
		village.ajouterPersonnage(chasseur);
		Sorcière sorcière = new Sorcière();
		village.ajouterPersonnage(sorcière);
		Partie partie = new Partie(village, log);
		
		
		partie.simulation(nbPartie);
		double lg = partie.getPourcentWinLoupGarous();
		double vi = partie.getPourcentWinVillage();
		
		
		//partie.exploration();
		
		
		/*String[] tabVote = new String[2];
		tabVote[0] = "0";
		tabVote[1] = "1";
		
		String[] tabCupidon = new String[2];
		tabCupidon[0] = "a";
		tabCupidon[1] = "b";
		
		
		explorationBranche(tabVote, "", tabCupidon) ;*/
		
		
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
		
		
		/*String tab[] = {"0","1" };
		CombinationPossible(tab,"", 2);*/
		
	}	
	
	static void explorationBranche(String[] tab, String branche, String[] tabCupidon) {
		String point = branche;
		
		for (int i=0; i < tab.length; i++) {
			
			long countLgmort = branche.chars().filter(ch -> ch == '1').count();
			
			
			if(countLgmort < 1 || tab[i] != "1") { // pour pas loup mort soit compter
				//if(this.savegardeVillage.getNbVillageois() - countVimort - 1 > this.savegardeVillage.getNbLoupGarou() ||  tab[i] == "0") {
				//if(countVimort < this.savegardeVillage.getNbVillageois() - this.savegardeVillage.getNbLoupGarou() - 1 && tab[i] == "1") {
				point +=  tab[i];
				//System.out.println(point);
				//Log.println("point = " + point );
				long countVimort = point.chars().filter(ch -> ch == '0').count();
				countLgmort = branche.chars().filter(ch -> ch == '1').count();
				try {
					for (int n = 0; n < 2 ; n++) {
						if(branche.equals("")) {
							point +=  tabCupidon[n];
						}
						if (!listeBranches.contains(point)){
							if(point.length() >= 3){ // compteur <= 0 remplacer cette condition par condition de victoire
								listeBranches.add(point);
								System.out.println("point = " + point);
							}
							else {
								explorationBranche(tab, point, tabCupidon);
								
							}
						point = branche + tab[i];
						//System.out.println("point2 = " + point +  " taille = " + point.length());
						//System.out.println(branche);
						}
					}
					point = branche;
				}
				catch(StackOverflowError e){
					System.out.println("Branche = " + branche);
					System.out.println("Condition = " + (3 - countVimort - (branche.length() - 3 )  - (1 - countLgmort)  <= 0 || 1 - countLgmort <= 0));
					System.exit(0);
				}
			}
					
		}
	}
	
	static void CombinationPossible(String[] tab, String branche, int compteur) {
		compteur --;
		String branche2 = branche;
		/*String branche2 = "";
		for (int x=1; x<branche.length(); x++) {
			branche2 = b;
        }*/
		
		for (int i=0; i < tab.length; i++) {
			branche2 +=  tab[i];
			
			/*System.out.println();
			System.out.println("branche  = " + branche2);
			System.out.println("boucle = " + compteur);
			System.out.println("i = " + i);
			System.out.println("taille = " + tab.length);
			System.out.println();*/
			
			if(compteur !=0) {
				CombinationPossible(tab, branche2, compteur );
			}
			else {
				System.out.println("branche  = " + branche2);
			}
			branche2 = branche;
		}
		return ;
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
