package org.apache.maven.archetypes.CapitalSperLoupGarous;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.TypeDeLog;

public class Logger {
	private static boolean detailVoteVillage;
	private static boolean fichierOutput;
	private static PrintWriter writer;
	private static boolean afficherLogDetailsPartie;
	private static boolean afficherLogDetailsPourcentage;
	private static boolean afficherLogDetailsRoleAction;
	private static boolean afficherLogStats;
	private static boolean sauvegardePartie;
	private static String logPartie;
	
	
	

	public Logger() {
		this.detailVoteVillage = false;
		this.fichierOutput = false;
		this.afficherLogDetailsPartie = true;
		this.afficherLogDetailsPourcentage = false;
		this.afficherLogDetailsRoleAction = false ;
		this.afficherLogStats = true;
		this.sauvegardePartie = false;
		this.logPartie = "";
	}
	
	public void setModeSpectateurOn() {
		this.setOnAfficherLogDetailsRoleAction();
	}
	
	public void setModeStatistiqueOn() {
		this.setOffAfficherLogDetailsPartie();
		this.setOnAfficherLogStats();
	}
	
	
	
	
	
	
	
	public String getLogPartie() {
		return logPartie;
	}

	public static void setSauvegardePartie(boolean sauvegardePartie) {
		Logger.sauvegardePartie = sauvegardePartie;
	}
	
	

	public static boolean isSauvegardePartie() {
		return sauvegardePartie;
	}

	public static boolean isAfficherLogStats() {
		return afficherLogStats;
	}


	public  void setOnAfficherLogStats() {
		this.afficherLogStats = true;
	}
	
	public  void setOffAfficherLogStats() {
		this.afficherLogStats = false;
	}



	public void setOnAfficherLogDetailsRoleAction() {
		this.afficherLogDetailsRoleAction = true;
	}
	
	public void setOffAfficherLogDetailsRoleAction() {
		this.afficherLogDetailsRoleAction = false;
	}
	
	public static boolean isAfficherLogDetailsRoleActionOn() {
		return afficherLogDetailsRoleAction;
	}

	public static boolean isDetailVoteVillageOn() {
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
	
	public boolean isAfficherLogDetailsPartie() {
		return this.afficherLogDetailsPartie ;
	}
	
	public void setOnAfficherLogDetailsPourcentage() {
		this.afficherLogDetailsPourcentage = true;
	}
	
	public void setOffAfficherLogDetailsPourcentage() {
		this.afficherLogDetailsPourcentage = false;
	}
	
	public void écrireFichier (String mode, Village village) {
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
	
	public static void  log (String log) {
		if(afficherLogDetailsPartie) {
			if(sauvegardePartie) {
				logPartie += log + "\n";
			}
			else {
				System.out.println(log);
				if(fichierOutput) {
					writer.println(log);
				}
			}
		}
	}
	
	public static void  log (String messageDeLog, TypeDeLog typeDeLog) {
		
		if(TypeDeLog.vote.equals(typeDeLog)) {
			if(detailVoteVillage) {
				if(sauvegardePartie) {
					logPartie += messageDeLog + "\n";
				}
				else {
					System.out.println(messageDeLog);
					if(fichierOutput) {
						writer.println(messageDeLog);
					}
				}
			}
		}
		
		else if (TypeDeLog.pourcentage.equals(typeDeLog)) {
			if(afficherLogDetailsPourcentage) {
				if(sauvegardePartie) {
					logPartie += messageDeLog + "\n";
				}
				else{
					System.out.println(messageDeLog);
					if(fichierOutput) {
						writer.println(messageDeLog);
					}
				}
			}
		}
		
		else if (TypeDeLog.role.equals(typeDeLog)) {
			if(afficherLogDetailsRoleAction) {
				if(sauvegardePartie) {
					logPartie += messageDeLog + "\n";
				}
				else {
					System.out.println(messageDeLog);
					if(fichierOutput) {
						writer.println(messageDeLog);
					}
				}
			}
		}
		
		else if (TypeDeLog.statistique.equals(typeDeLog)) {
			if(afficherLogStats) {
				if(sauvegardePartie) {
					logPartie += messageDeLog + "\n";
				}
				else {
					System.out.println(messageDeLog);
					if(fichierOutput) {
						writer.println(messageDeLog);
					}
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
