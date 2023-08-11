package org.apache.maven.archetypes.CapitalSpéLoupGarous;

import java.util.ArrayList;
import java.util.List;

public class Test {
	private static List<String> listeBranches = new ArrayList<String>();
	private static int max = 4;

	public static void main(String[] args) {
		String[] tabVote = new String[2];
		tabVote[0] = "0";
		tabVote[1] = "1";
		
		String[] tabCupidon = new String[2];
		tabCupidon[0] = "a";
		tabCupidon[1] = "b";
		
		
		//explorationBranche(tabVote, "", tabCupidon) ;
		
		
		String tab[] = {"0","1"};
		String tab2[] = {"a","b"};
		CombinationPossible(tab,"", 4, tab2);

	}
	
	static void CombinationPossible(String[] tab, String branche, int compteur, String[] tab2) {
		//compteur --;
		String branche2 = branche;
		/*String branche2 = "";
		for (int x=1; x<branche.length(); x++) {
			branche2 = b;
        }*/
		
		for (int i=0; i < tab2.length; i++) {
			branche2 +=  tab2[i];
			for (int j=0; j < tab.length; j++) {
				branche2 +=  tab[j];
				
				/*System.out.println();
				System.out.println("branche  = " + branche2);
				System.out.println("boucle = " + compteur);
				System.out.println("i = " + i);
				System.out.println("taille = " + tab.length);
				System.out.println();*/
				
				if(branche2.length() <  compteur) {
					//branche2 = branche + tab2[i];
					if(branche2.length() % 2 == 1) {
						for (int t=0; t < branche2.length(); t += 2) {
							branche2 = branche2.substring(t, t+1);
						}
						
					}
					System.out.println("Récursion : " + branche2);
					CombinationPossible(tab, branche2, compteur, tab2 );
					//branche2 = branche;
				}
				else {
					System.out.println("branche  = " + branche2);
					branche2 = branche + tab2[i];
				}
				
			}
			branche2 = branche;
		}
		
    }
	
	
	static void explorationBranche(String[] tab, String branche, String[] tabCupidon) {
		String point;
		for (int n = 0; n < tabCupidon.length ; n++) {
			point = branche;
			if(branche.equals("")) {
				point +=  tabCupidon[n];
			}
			for (int i=0; i < tab.length; i++) {
				
				long countLgmort = branche.chars().filter(ch -> ch == '1').count();
				
				
				if(countLgmort < 1 || tab[i] != "1") { // pour pas loup mort soit compter
					//if(this.savegardeVillage.getNbVillageois() - countVimort - 1 > this.savegardeVillage.getNbLoupGarou() ||  tab[i] == "0") {
					//if(countVimort < this.savegardeVillage.getNbVillageois() - this.savegardeVillage.getNbLoupGarou() - 1 && tab[i] == "1") {
					if(point.length() <  max){
						point +=  tab[i];
					}
					
					//System.out.println(point);
					//Log.println("point = " + point );
					long countVimort = point.chars().filter(ch -> ch == '0').count();
					countLgmort = branche.chars().filter(ch -> ch == '1').count();
					try {
						
						if (!listeBranches.contains(point)){
							if(point.length() >= max){ // compteur <= 0 remplacer cette condition par condition de victoire
								listeBranches.add(point);
								System.out.println("point = " + point);
							}
							else {
								//System.out.println("récursion : " +  point);
								explorationBranche(tab, point, tabCupidon);
								
							}
						if(branche.equals("")) {
							point = branche + tabCupidon[n];
						}
						else {
							point = branche;
						}
						
						//System.out.println("point après récursion " + point);
						//System.out.println("point2 = " + point +  " taille = " + point.length());
						//System.out.println(branche);
						}
					//
					}
					catch(StackOverflowError e){
						System.out.println("Branche = " + branche);
						System.out.println("Condition = " + (3 - countVimort - (branche.length() - 3 )  - (1 - countLgmort)  <= 0 || 1 - countLgmort <= 0));
						System.exit(0);
					}
				}
			//point = branche;
			}
		}
	}
}
