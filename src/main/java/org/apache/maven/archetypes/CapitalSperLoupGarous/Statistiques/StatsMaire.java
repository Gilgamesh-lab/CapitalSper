package org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois.SimpleVillageois;

public class StatsMaire extends Statistiques {
	private float nbVote;
	private float nbEgaliter;
	private float nbMaire;
	private float nbVoteDecisifDuMaire;
	private float nbSperMaire;
	private float nbLoupGarouMaire;
	
	
	
	

	public StatsMaire() {
		this.nbVote = 0;
		this.nbEgaliter = 0;
		this.nbMaire = 0;
		this.nbVoteDecisifDuMaire = 0;
		this.nbSperMaire = 0;
		this.nbLoupGarouMaire = 0;
	}
	

	public void vote(Map<Integer, Integer> tableauVotes, int voteMaire) {
		this.nbVote++;
		Integer plusGrandNombreDeVotesPourUnePersonne = tableauVotes.entrySet().stream()
				  .map(Map.Entry::getValue)
				  .reduce(Integer::max)
				  .get();
		
		List<Integer> listeIdPersonneAyantPlusDeVotes = tableauVotes.entrySet().stream().filter(x->x.getValue() == plusGrandNombreDeVotesPourUnePersonne).map(Map.Entry::getKey).collect(Collectors.toList());
		
		if(listeIdPersonneAyantPlusDeVotes.contains(voteMaire)) {
			try {
				if(tableauVotes.entrySet().stream().map(Map.Entry::getKey).anyMatch(x-> (tableauVotes.get(x) >= ((tableauVotes.get(voteMaire)) -1) && x != voteMaire))){
					this.nbVoteDecisifDuMaire++;
				}
			}
			catch (Exception e) {
				tableauVotes.entrySet().stream().map(Map.Entry::getKey).forEach(x->System.out.println(x));
				System.out.println(tableauVotes);
				System.out.println(voteMaire);
				e.printStackTrace();
				throw e;
			}
				

		}
	}
	
	public void incrementerNbEgaliter() {
		this.nbEgaliter++;
	}
	
	public void nouveauMaire(Personnage personnage) {
		this.nbMaire++;
		if(personnage.estUnVillageois()) {
			if(personnage.getIdDeRole() != SimpleVillageois.IDROLE) {
				this.nbSperMaire++;
			}
		}
		else {
			this.nbLoupGarouMaire++;
		}
	}
	
	
	
	@Override
	public String getStats() {
		String stats1 = "Sur " + (int) super.getNbPartie() +  " parties, le maire a participer en moyenne " + ((double) ((this.nbVote / super.getNbPartie()))) + " vote par partie" ;
		String stats2 = "Sur " + (int) super.getNbPartie() + " parties, il y a eu en moyenne " + ((double) (this.nbMaire / super.getNbPartie())) + " maire par partie" ;
		String stats3 = "Sur " + (int) this.nbMaire + " maires,  " + ((double) (this.nbLoupGarouMaire / this.nbMaire) * 100) + "% d'entre eux étaient des loups-garous" ;
		String stats4 = "Sur " + (int) this.nbMaire + " maires,  " + ((double) (this.nbSperMaire / this.nbMaire) * 100) + "% d'entre eux étaient des villageois spéciaux" ;
		String stats5 = "Sur " + (int) this.nbVote + " votes du village,  " + ((double) (this.nbEgaliter / this.nbVote) * 100) + "% des votes se sont terminés sur une égalité" ;
		String stats6 = "Sur " + (int) this.nbVote +  " votes du village, la présence du maire a changé l'issue de "  + ((double) (this.nbVoteDecisifDuMaire / this.nbVote) * 100) + "% d'entre eux";
		return stats1 + "\n" + stats2 + "\n" + stats3 + "\n" + stats4 + "\n" + stats5 + "\n" + stats6;
	}
	

	

	public float getNbVote() {
		return nbVote;
	}



	public float getNbEgaliter() {
		return nbEgaliter;
	}



	public float getNbMaire() {
		return nbMaire;
	}



	public float getNbVoteDecisifDuMaire() {
		return nbVoteDecisifDuMaire;
	}



	public float getNbSperMaire() {
		return nbSperMaire;
	}



	public float getNbLoupGarouMaire() {
		return nbLoupGarouMaire;
	}


	
	

}
