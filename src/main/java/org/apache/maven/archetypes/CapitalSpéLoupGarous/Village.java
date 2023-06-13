package org.apache.maven.archetypes.CapitalSpéLoupGarous;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.Optional;

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
		return this.village.size()  ;
	}
	
	public ArrayList<Villageois> getVillageois() {
		return this.villageois ;
	}
	
	public ArrayList<Personnage> getVillage() {
		return this.village ;
	}
	
	public Meute getMeute() {
		return this.meute ;
	}
	
	public int getNbVillageois() {
		return this.getNbPersonnage() - this.getNbLoupGarou() ;
	}
	
	/*public void perdreHabitant() {
		this.getNbPersonnage-- ;
	}*/
	
	public int getNbLoupGarou() {
		return this.meute.getMeute().size() ;
	}
	
	
	
	public Personnage getPersonnage(int nb) {
		return this.village.get(nb);
	}
	
	public void voter() {
		
		Personnage votant;
		Map<Integer, Integer> tableauDeVote = new HashMap<>();
		int vote;
		for(int i = 0 ; i < this.getVillage().stream().map(x->x.getId()).reduce(Integer::max).get() + 1 ; i++) {
			tableauDeVote.put(i, 0);
		}
		
		for(int i = 0 ; i < this.getNbPersonnage() ; i++) {
			votant = this.getVillage().get(i);
			 vote  = votant.voter();
			 
			tableauDeVote.put(vote, tableauDeVote.get(vote) + 1);
		}
		
		Integer plusGrandNombreDeVote = tableauDeVote.entrySet().stream()
				  .map(Map.Entry::getValue)
				  .reduce(Integer::max).get();
		
		List<Integer> listeMaxVoteId = tableauDeVote.entrySet().stream().filter(x->x.getValue() == plusGrandNombreDeVote).map(Map.Entry::getKey).collect(Collectors.toList());
		int maxVoteId = listeMaxVoteId.get((int) (Math.random() * ( listeMaxVoteId.size()    - 0 )));// si plusieurs maxVote
				//.reduce(Integer::max).get();
		Personnage personnageCondamner = this.village.stream().filter(x-> x.getId() == maxVoteId ).findFirst().get();
		if(Partie.log.isDetailVoteVillageOn()) {
			System.out.println();
			System.out.println("Le village est composé de : " + this.village);
			System.out.println(personnageCondamner +  " est envoyé au buché avec  " + plusGrandNombreDeVote + " vote contre lui ");
		}
		if(personnageCondamner.estUnVillageois()) {
			System.out.println("Un villageois a été tué lors du vote");
			
		}
		else {
			System.out.println("Un loup-garou a été tué lors du vote");
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
