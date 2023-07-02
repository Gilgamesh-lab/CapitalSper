package org.apache.maven.archetypes.CapitalSpéLoupGarous;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Log {
	private static boolean detailVoteVillage;
	private static boolean fichierOutput;
	private static PrintWriter writer;
	private static boolean afficherLogDetailsPartie;
	
	
	

	public Log() {
		this.detailVoteVillage = false;
		this.fichierOutput = false;
		this.afficherLogDetailsPartie = true;
	}

	public boolean isDetailVoteVillageOn() {
		return detailVoteVillage;
	}

	public void setDetailVoteVillage(boolean voteVillage) {
		this.detailVoteVillage = voteVillage;
	}
	
	public void setOnFichierOutput() {
		this.fichierOutput = true;
	}
	
	public boolean isFichierOutput() {
		return this.fichierOutput;
	}
	
	public void setOnAfficherLogDetailsPartie() {
		this.afficherLogDetailsPartie = true;
	}
	
	public void setOffAfficherLogDetailsPartie() {
		this.afficherLogDetailsPartie = false;
	}
	
	public void start (String mode, Village village) {
		int tab[] = referentiel(village);
		try {
			this.writer = new PrintWriter("log/" + mode + " avec " + tab[0] + " simpleVillageois et " + tab[1] + " loups-garous.txt" , "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int[] referentiel(Village village) {
		int tab[] = new int[2];
		tab[0] = village.getNbVillageois();
		tab[1] = village.getNbLoupGarou();
		
		return tab;
		
	}
	
	public static void  println(String log) {
		if(afficherLogDetailsPartie) {
			System.out.println(log);
			if(fichierOutput) {
				writer.println(log);
			}
		}
	}
	
	public static void  println(String log, int nb) {
		if(nb == 1) {
			if(detailVoteVillage) {
				System.out.println(log);
				
			}
		}
	
		if(fichierOutput) {
			writer.println(log);
		}
	}
	

	
	public void finish() {
		if(writer != null) {
			writer.close();
		}
	}
	

}
