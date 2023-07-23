package org.apache.maven.archetypes.CapitalSpéLoupGarous;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.Optional;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Cupidon;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarou;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Meute;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois;

public  class Village {

	private ArrayList<Personnage> village;
	private ArrayList<Villageois> villageois;
	private  Meute meute ;
	
	public Village() {
		this.village = new ArrayList<Personnage>();
		this.villageois = new ArrayList<Villageois>();
		this.meute = new Meute();
		this.meute.setVillage(this);
	}
	
	public Village(int nbVillageois, int nbLoupGarous) {
		this();
		for(int i = 0 ; i < nbVillageois ; i++) {
			this.ajouterPersonnage(new SimpleVillageois());
		}
		for(int i = 0 ; i < nbLoupGarous ; i++) {
			this.ajouterPersonnage(new LoupGarouSimple());
		}
	}
	
	public Village(ArrayList<Personnage> personnages) {
		this();
		for(int i = 0 ; i < personnages.size() ; i++) {
			this.ajouterPersonnage(personnages.get(i));
		}
	}
	
	public Village(int nbVillageois, int nbLoupGarous, ArrayList<Personnage> personnages) {
		this(nbVillageois,  nbLoupGarous);
		for(int i = 0 ; i < personnages.size() ; i++) {
			this.ajouterPersonnage(personnages.get(i));
		}
	}
	
	
	
	
	public void ajouterPersonnage(Personnage personnage) {
		this.inscrire(personnage);
		this.village.add(personnage);
		personnage.setVillage(this);
		personnage.setId(this.village.size());
	}
	
	public void inscrire(Personnage personnage) {
		if(personnage.estUnVillageois()) {
			this.villageois.add((Villageois) personnage);
		}
		else {
			this.meute.enrolerUnLoupGarou((LoupGarou) personnage);
		}
	}
	
	
	public int getNbPersonnage() {
		return this.village.size();
		//return (int) this.village.stream().filter(x->x.estEnvie()).count();
	}
	
	public ArrayList<Villageois> getVillageois() {
		return this.villageois ;
	}
	
	public ArrayList<Personnage> getHabitants() {
		return this.village ;
	}
	
	public Meute getMeute() {
		return this.meute ;
	}
	
	public int getNbVillageois() {
		return this.getNbPersonnage() - this.getNbLoupGarou() ;
	}
	
	
	public int getNbLoupGarou() {
		return this.meute.getMeute().size() ;
	}
	
	
	
	public Personnage getPersonnage(int nb) {
		return this.village.get(nb);
	}
	
	public void premièreNuit() {
		if(this.getHabitants().stream().anyMatch(x -> x.getIdDeRole() == 4)) {
			Cupidon cupidon = (Cupidon) this.getHabitants().stream().filter(x -> x.getIdDeRole() == 4).findFirst().get();
			cupidon.flecheDeLAmour();
		}
		if(this.meute.estEnVie()) {
			this.meute.attaquerVillage();
		}
	}
	
	public void voter() {
		
		Personnage votant;
		Map<Integer, Integer> tableauDeVotes = new HashMap<>();
		int vote;
		for(int i = 0 ; i < this.getHabitants().stream().map(x->x.getId()).reduce(Integer::max).get() + 1 ; i++) {
			tableauDeVotes.put(i, 0);
		}
		
		for(int i = 0 ; i < this.getNbPersonnage() ; i++) {
			votant = this.getHabitants().get(i);
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
		Personnage personnageCondamner = this.village.stream().filter(x-> x.getId() == idPersonneAyantPlusDeVotes ).findAny().get();
		Logger.log("");
		Logger.log("Le village est composé de : " + this.village, "vote");
		Logger.log(personnageCondamner +  " est envoyé au buché avec  " + plusGrandNombreDeVotesPourUnePersonne + " vote contre lui ", "vote");

		Logger.log(personnageCondamner + " a été tué lors du vote");


		personnageCondamner.meurt();
	}
	
	public void voter(char vote) {
		Personnage personnageCondamner;
		if(vote == '0') {
			personnageCondamner = this.village.stream().filter(x->x.estUnVillageois()).findAny().get();
			Logger.log("Un villageois a été tué lors du vote");
		}
		else {
			personnageCondamner = this.village.stream().filter(x->!x.estUnVillageois()).findAny().get();
			Logger.log("Un loup-garou a été tué lors du vote");
		}
		personnageCondamner.meurt();
	}
	
	
	
	@Override
	public String toString() {
		String nom = " ";
		for(int i = 0 ; i < this.village.size() ; i++) {
			nom += this.village.get(i).toString() +  " , ";
		}
		return nom;
	}
	
}
