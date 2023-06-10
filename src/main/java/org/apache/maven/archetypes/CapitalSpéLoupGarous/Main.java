package org.apache.maven.archetypes.CapitalSpéLoupGarous;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		int nbVillageois = 5;
		int nbLoupGarous = 1;
		int nbPartie = 1;//100000
		
		Village village = new Village(nbVillageois,nbLoupGarous);
		Log log = new Log();
		//log.setDetailVoteVillage(true);
		Partie partie = new Partie(village, log);
		
		partie.simulation(nbPartie);
		
	}

}
