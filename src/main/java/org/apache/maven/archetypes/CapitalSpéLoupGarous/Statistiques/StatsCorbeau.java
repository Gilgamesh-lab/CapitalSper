package org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;

public class StatsCorbeau extends Statistiques {
	private float nbCorbeautage;
	private float nbCorbeautageSurLoupGarou;
	private static float nbCorbeautageDecisif;
	
	
	public StatsCorbeau() {
		this.nbCorbeautage = 0;
		this.nbCorbeautageSurLoupGarou = 0;
		nbCorbeautageDecisif = 0;
	}
	
	public void corbeauter(Personnage personnage) {
		this.nbCorbeautage++;
		if(!personnage.estUnVillageois()) {
			this.nbCorbeautageSurLoupGarou++;
		}
	}
	
	public void reset() {
		this.nbCorbeautage = 0;
		this.nbCorbeautageSurLoupGarou = 0;
		nbCorbeautageDecisif = 0;
	}
	
	public static void corbeautage  (int id, Map<Integer, Integer> tableauVotes) {
		Integer plusGrandNombreDeVotesPourUnePersonne = tableauVotes.entrySet().stream()
				  .map(Map.Entry::getValue)
				  .reduce(Integer::max)
				  .get();
		
		List<Integer> listeIdPersonneAyantPlusDeVotes = tableauVotes.entrySet().stream().filter(x->x.getValue() == plusGrandNombreDeVotesPourUnePersonne).map(Map.Entry::getKey).collect(Collectors.toList());
		if(listeIdPersonneAyantPlusDeVotes.contains(id)) {
			if(tableauVotes.entrySet().stream().map(Map.Entry::getKey).filter(x-> x != id ).anyMatch(x-> tableauVotes.get(x) >= ((tableauVotes.get(id)) -2))){
				nbCorbeautageDecisif++;
			}
				

		}
	}
	
	@Override
	public String getStats() {
		String stats1 = "Le corbeau a effectué en moyenne " + ((double) ((this.getNbCorbeautage() / super.getNbPartie()))) + " corbeautages par partie" ;
		String stats2 = "Sur les " + (int) this.getNbCorbeautage() + " corbeautages,  le corbeau a corbeauter un loup-garou dans " + ((double) ((this.getNbCorbeautageSurLoupGarou()  / this.getNbCorbeautage()) * 100 )) + "% d'entre eux";
		String stats3 = "Sur " + (int) this.getNbCorbeautage() +  " corbeautages, "  + ((double) (this.getNbCorbeautageDecisif() / this.getNbCorbeautage()) * 100) + "% d'entre eux ont changé l'issue du vote du village";
		return stats1 + "\n" + stats2 + "\n" + stats3;
	}

	public float getNbCorbeautage() {
		return nbCorbeautage;
	}

	public float getNbCorbeautageSurLoupGarou() {
		return nbCorbeautageSurLoupGarou;
	}

	public float getNbCorbeautageDecisif() {
		return nbCorbeautageDecisif;
	}
	
	

}
