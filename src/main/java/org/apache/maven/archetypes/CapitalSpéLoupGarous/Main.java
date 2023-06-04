package org.apache.maven.archetypes.CapitalSpéLoupGarous;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		int nbVillageois = 9;
		int nbLoupGarous = 1;
		Village village = new Village(nbVillageois,nbLoupGarous);
		
		Partie partie = new Partie(village);
		
		//partie.start();		
		partie.simulation(1000);

	}

}
