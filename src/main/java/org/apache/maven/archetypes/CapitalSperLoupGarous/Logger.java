package org.apache.maven.archetypes.CapitalSpéLoupGarous;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.TypeDeLog;

public class Logger {
	private static boolean detailVoteVillage;
	private static boolean fichierOutput;
	private static PrintWriter writer;
	private static boolean afficherLogDetailsPartie;
	private static boolean afficherLogDetailsPourcentage;
	private static boolean afficherLogDetailsRoleAction;
	private static boolean afficherLogStats;
	
	
	

	public Logger() {
		detailVoteVillage = false;
		fichierOutput = false;
		afficherLogDetailsPartie = true;
		afficherLogDetailsPourcentage = false;
		afficherLogDetailsRoleAction = false ;
		afficherLogStats = true;
	}
	
	public void setModeSpectateurOn() {
		setOnAfficherLogDetailsRoleAction();
	}
	
	public void setModeStatistiqueOn() {
		setOffAfficherLogDetailsPartie();
		setOnAfficherLogStats();
	}
	
	
	
	public static boolean isAfficherLogStats() {
		return afficherLogStats;
	}


	public  void setOnAfficherLogStats() {
		afficherLogStats = true;
	}
	
	public  void setOffAfficherLogStats() {
		afficherLogStats = false;
	}



	public void setOnAfficherLogDetailsRoleAction() {
		afficherLogDetailsRoleAction = true;
	}
	
	public void setOffAfficherLogDetailsRoleAction() {
		afficherLogDetailsRoleAction = false;
	}
	
	public static boolean isAfficherLogDetailsRoleActionOn() {
		return afficherLogDetailsRoleAction;
	}

	public static boolean isDetailVoteVillageOn() {
		return detailVoteVillage;
	}

	public void setDetailVoteVillage(boolean voteVillage) {
		detailVoteVillage = voteVillage;
	}
	
	public void setOnFichierOutput() {
		fichierOutput = true;
	}
	
	public boolean isFichierOutput() {
		return fichierOutput;
	}
	
	public void setOnAfficherLogDetailsPartie() {
		afficherLogDetailsPartie = true;
	}
	
	public void setOffAfficherLogDetailsPartie() {
		afficherLogDetailsPartie = false;
	}
	
	public boolean isAfficherLogDetailsPartie() {
		return afficherLogDetailsPartie ;
	}
	
	public void setOnAfficherLogDetailsPourcentage() {
		afficherLogDetailsPourcentage = true;
	}
	
	public void setOffAfficherLogDetailsPourcentage() {
		afficherLogDetailsPourcentage = false;
	}
	
	public void écrireFichier (String mode, Village village) {
		int tab[] = referentiel(village);
		try {
			writer = new PrintWriter("log/" + mode + " avec " + tab[0] + " simpleVillageois et " + tab[1] + " loups-garous.txt" , "UTF-8");
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
	
	public static void  log (String log) {
		if(afficherLogDetailsPartie) {
			System.out.println(log);
			if(fichierOutput) {
				writer.println(log);
			}
		}
	}
	
	public static void  log (String messageDeLog, TypeDeLog typeDeLog) {
		
		if(TypeDeLog.vote.equals(typeDeLog)) {
			if(detailVoteVillage) {
				System.out.println(messageDeLog);
				if(fichierOutput) {
					writer.println(messageDeLog);
				}
				
			}
		}
		else if (TypeDeLog.pourcentage.equals(typeDeLog)) {
			if(afficherLogDetailsPourcentage) {
				System.out.println(messageDeLog);
				if(fichierOutput) {
					writer.println(messageDeLog);
				}
			}
		}
		
		else if (TypeDeLog.role.equals(typeDeLog)) {
			if(afficherLogDetailsRoleAction) {
				System.out.println(messageDeLog);
				if(fichierOutput) {
					writer.println(messageDeLog);
				}
			}
		}
		
		else if (TypeDeLog.statistique.equals(typeDeLog)) {
			if(afficherLogStats) {
				System.out.println(messageDeLog);
				if(fichierOutput) {
					writer.println(messageDeLog);
				}
			}
		}
	
		
	}
	

	
	public void finish() {
		if(writer != null) {
			writer.close();
		}
	}
	

}
