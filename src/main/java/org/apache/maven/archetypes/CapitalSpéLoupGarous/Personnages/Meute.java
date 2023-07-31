package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;

public class Meute {
	
	private ArrayList<LoupGarou> meute;
	private Village village;
	
	public Meute() {
		this.meute = new ArrayList<LoupGarou>();
	}

	public ArrayList<LoupGarou> getMeute() {
		return (ArrayList<LoupGarou>) this.meute.stream().filter(x->x.estEnvie()).collect(Collectors.toList());
	}
	
	public int getNbLg() {
		return (int) this.meute.stream().filter(x->x.estEnvie()).count();
	}
	
	public void enrolerUnLoupGarou(LoupGarou loupGarou) {
		loupGarou.rejoindreUneMeute(this);
		for(int i = 0; i < this.meute.size(); i++) {
			loupGarou.ajouterAlliés(this.meute.get(i));
		}
		for(int i = 0; i < this.meute.size(); i++) {
			this.meute.get(i).ajouterAlliés(loupGarou);;
		}
		this.meute.add(loupGarou);
	}

	public void setVillage(Village village) {
		this.village = village;
	}
	
	public boolean estEnVie() {
		return this.meute.size() != 0;
	}
	
	public void attaquerVillage() {
		LoupGarou votant;
		Map<Integer, Integer> tableauDeVotes = new HashMap<>();
		int vote;
		for(int i = 0 ; i < this.village.getHabitants().stream().map(x->x.getId()).reduce(Integer::max).get() + 1 ; i++) {
			tableauDeVotes.put(i, 0);
		}
		
		for(int i = 0 ; i < this.getNbLg() ; i++) {
			votant = this.getMeute().get(i);
			vote  = votant.voter();
			tableauDeVotes.put(vote, tableauDeVotes.get(vote) + 1);
		}
		
		Integer plusGrandNombreDeVotesPourUnePersonne = tableauDeVotes.entrySet().stream()
				  .map(Map.Entry::getValue)
				  .reduce(Integer::max)
				  .get();
		
		List<Integer> listeIdPersonneAyantPlusDeVotes = tableauDeVotes.entrySet().stream().filter(x->x.getValue() == plusGrandNombreDeVotesPourUnePersonne).map(Map.Entry::getKey).collect(Collectors.toList());
		int idPersonneAyantPlusDeVotes;
		if(listeIdPersonneAyantPlusDeVotes.size() > 1) {
			idPersonneAyantPlusDeVotes = listeIdPersonneAyantPlusDeVotes.get((int) (Math.random() * ( listeIdPersonneAyantPlusDeVotes.size() - 0 )));// si plusieurs maxVote
				//.reduce(Integer::max).get();
		}
		else {
			idPersonneAyantPlusDeVotes = listeIdPersonneAyantPlusDeVotes.get(0);
		}
		Personnage personnageDevorer = this.village.getHabitants().stream().filter(x-> x.getId() == idPersonneAyantPlusDeVotes ).findAny().get();
		Logger.log("");
		Logger.log("Le village est composé de : " + this.village.getHabitants(), TypeDeLog.vote);
		Logger.log(personnageDevorer +  " a été choisi par la meute pour leurs servirent de repas avec  " + plusGrandNombreDeVotesPourUnePersonne + " vote contre lui ", TypeDeLog.vote);


		personnageDevorer.getStatut().setAttaquerParLg(true);
	}

}
