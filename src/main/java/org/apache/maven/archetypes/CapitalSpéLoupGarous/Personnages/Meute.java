package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.StatsPersonnages.StatsSalvateur;

public class Meute {
	
	private ArrayList<LoupGarou> meute;
	private Village village;
	private Boolean estRassasier;
	
	public Meute() {
		this.meute = new ArrayList<LoupGarou>();
		this.estRassasier = false;
	}
	


	public ArrayList<LoupGarou> getMeute() {
		return (ArrayList<LoupGarou>) this.meute.stream().filter(x->x.estEnvie()).collect(Collectors.toList());
	}
	
	public int getNbLgEnVie() {
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
	
	public void devorer(Personnage personnage) {
		personnage.getStatut().setTueur(LoupGarouSimple.IDROLE);
		personnage.meurt();
	}
	
	public void attaquerVillage() {
		if (this.estRassasier) {// pour mettre plusieur spé identique
			return;
		}
		Logger.log("");
		LoupGarou votant;
		Map<Integer, Integer> tableauDeVotes = new HashMap<>();
		int vote;
		for(int i = 0 ; i < this.village.getHabitantsEnVie().stream().map(x->x.getId()).reduce(Integer::max).get() + 1 ; i++) {
			tableauDeVotes.put(i, 0);
		}
		
		for(int i = 0 ; i < this.getNbLgEnVie() ; i++) {
			votant = this.getMeute().get(i);
			vote  = votant.voter();
			if(votant.getId() == vote) {
				System.out.println("erreur détecté : " + votant + " a voté contre lui-même");
			}
			votant.resetListeDeVote();
			Logger.log(votant + " a voté contre " + this.village.getPersonnageParId(vote), TypeDeLog.vote);
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
		}
		else {
			idPersonneAyantPlusDeVotes = listeIdPersonneAyantPlusDeVotes.get(0);
		}
		Personnage personnageDevorer = this.village.getHabitantsEnVie().stream().filter(x-> x.getId() == idPersonneAyantPlusDeVotes ).findAny().get();
		Logger.log("", TypeDeLog.vote);
		Logger.log("Le village est composé de : " + this.village.getHabitantsEnVie(), TypeDeLog.vote);
		
		if(listeIdPersonneAyantPlusDeVotes.size() > 1) {
			Logger.log(personnageDevorer +  " a été choisi par la meute pour leurs servirent de repas sur une égalité avec  " + plusGrandNombreDeVotesPourUnePersonne + " vote contre lui ", TypeDeLog.vote);
		}
		else {
			Logger.log(personnageDevorer +  " a été choisi par la meute pour leurs servirent de repas avec  " + plusGrandNombreDeVotesPourUnePersonne + " vote contre lui ", TypeDeLog.vote);
		}
		
		if(!personnageDevorer.getStatut().isProtéger()) {
			personnageDevorer.getStatut().setAEteAttaqueParLaMeute(true);
		}
		
		else {
			Logger.log("Les loups-garous se sont heurtés à la protection du salvateur et n'ont pas pu dévorer " + personnageDevorer, TypeDeLog.role);
			Salvateur.getStatsSalvateur().incrementerNbProtectionReussie();
		}
		this.estRassasier = true;
	}

	public void setEstRassasier(Boolean estRassasier) {
		this.estRassasier = estRassasier;
	}
	
	

}
