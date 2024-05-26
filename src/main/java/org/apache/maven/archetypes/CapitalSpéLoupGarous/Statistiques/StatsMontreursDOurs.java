package org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;

public class StatsMontreursDOurs extends Statistiques {
	private float nbGrognement;
	private float nbVoisinDifférent;
	private float nbZeroLoupGarouVoisin;
	private float nbUnLoupGarouVoisin;
	private float nbDeuxLoupGarouVoisin;
	private float nbMort;
	private float nbVoisinLoupGarouTrouverDuPremierCoups;
	
	
	
	

	public StatsMontreursDOurs() {
		this.nbGrognement = 0;
		this.nbVoisinDifférent = 0;
		this.nbZeroLoupGarouVoisin = 0;
		this.nbUnLoupGarouVoisin = 0;
		this.nbDeuxLoupGarouVoisin = 0;
		this.nbMort = 0;
		this.nbVoisinLoupGarouTrouverDuPremierCoups = 0;
	}
	
	public void incrementerNbGrognement() {
		this.nbGrognement++;
	}

	public void incrementerNbVoisinDifférent() {
		this.nbVoisinDifférent++;
	}

	public void incrementerNbLoupGarouVoisin(Personnage voisins1 , Personnage voisins2) {
		if(!voisins1.estUnVillageois() || !voisins2.estUnVillageois()) {
			if(!voisins1.estUnVillageois() && !voisins2.estUnVillageois()) {
				this.nbDeuxLoupGarouVoisin++;
			}
			else {
				this.nbUnLoupGarouVoisin++;
			}
		}
		else {
			this.nbZeroLoupGarouVoisin++;
		}
		
	}

	public void incrementerNbMort() {
		this.nbMort++;
	}


	public void incrementerNbVoisinLoupGarouTrouverDuPremierCoups() {
		this.nbVoisinLoupGarouTrouverDuPremierCoups++;
		
	}


	public float getNbGrognement() {
		return nbGrognement;
	}

	public float getNbVoisinDifférent() {
		return nbVoisinDifférent;
	}

	public float getNbZeroLoupGarouVoisin() {
		return nbZeroLoupGarouVoisin;
	}

	public float getNbUnLoupGarouVoisin() {
		return nbUnLoupGarouVoisin;
	}

	public float getNbDeuxLoupGarouVoisin() {
		return nbDeuxLoupGarouVoisin;
	}

	public float getNbMort() {
		return nbMort;
	}

	public float getNbVoisinLoupGarouTrouverDuPremierCoups() {
		return nbVoisinLoupGarouTrouverDuPremierCoups;
	}

	@Override
	public String getStats() {
		String stats1 = "Sur les " + (int) super.getNbToursTotale() +  " tours de jeu, le montreurs d'ours a été en vie durant " + ((double) (((this.nbGrognement + this.nbZeroLoupGarouVoisin) / super.getNbToursTotale()) * 100)) + "% d'entre eux" ;// impact de la sorcière à prendre en compte
		String stats2 = "Sur les " + (int) (this.nbGrognement + this.nbZeroLoupGarouVoisin) +  " tours où il a été en vie, le montreurs d'ours a grogné dans " + ((double) ((this.nbGrognement / (this.nbGrognement + this.nbZeroLoupGarouVoisin)) * 100)) + "% des cas" ;
		String stats3 = "Sur les " + (int) (this.nbGrognement + this.nbZeroLoupGarouVoisin) +  " tours où il a été en vie, dans " + ((double) (((this.nbZeroLoupGarouVoisin / (this.nbGrognement + this.nbZeroLoupGarouVoisin))) * 100))+ "% des cas le montreurs d'ours n'a pas trouvé de loup-garou parmis ses voisins" ;
		String stats4 = "Sur les " + (int) this.nbGrognement + " grognements, le montreurs d'ours a trouvé un loup garous parmis ses voisins dans " + ((double) ((this.nbUnLoupGarouVoisin / this.nbGrognement) * 100 )) + "% des cas";
		String stats5 = "Sur les " + (int) this.nbGrognement + " grognements, le montreurs d'ours a trouvé deux loup garous parmis ses voisins dans " + ((double) ((this.nbDeuxLoupGarouVoisin / this.nbGrognement) * 100 )) + "% des cas";
		String stats6 = "Sur " + (int) super.getNbPartie() +  " parties, le montreurs d'ours a eu en moyenne " + ((double) ((this.nbGrognement / super.getNbPartie()))) + " nouveaux voisins par partie" ;
		String stats7 = "Sur " + (int) super.getNbPartie() +  " parties, le montreurs d'ours est mort dans " + ((double) ((this.nbMort / super.getNbPartie()) * 100 )) + "% d'entre elles" ;
		String stats8 = "Sur les " + (int) this.nbMort +  " morts du montreurs d'ours, le village a trouvé le coupable parmis les voisins du montreurs d'ours du premier coups dans " + ((double) ((this.nbVoisinLoupGarouTrouverDuPremierCoups / this.nbMort) * 100 )) + "% des cas" ;
		
		return stats1 + "\n" + stats2 + "\n" + stats3 + "\n" + stats4 + "\n" + stats5 + "\n" + stats6 + "\n" + stats7 + "\n" + stats8;
	}

}
