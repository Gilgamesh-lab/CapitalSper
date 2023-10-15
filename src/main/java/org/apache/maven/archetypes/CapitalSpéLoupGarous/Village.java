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
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Maire;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Meute;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.MontreurDOurs;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Salvateur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Sorcière;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.TypeDeLog;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Voyante;

public  class Village  implements Cloneable {

	private ArrayList<Personnage> village;
	private  Meute meute ;
	private Maire maire = null;
	
	public Village() {
		this.village = new ArrayList<Personnage>();
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
		personnage.setId(this.village.size());
		this.village.add(personnage);
		personnage.setVillage(this);
		if(!personnage.estUnVillageois()) {
			this.meute.enrolerUnLoupGarou((LoupGarou) personnage);
		}
	}
	
	
	
	public int getNbPersonnage() {
		return (int) this.village.stream().filter(x->x.estEnvie()).count();
	}
	
	public ArrayList<Personnage> getVillageois() {
		return new ArrayList<Personnage>(this.village.stream().filter(x->x.estEnvie() && x.estUnVillageois()).collect(Collectors.toList()));
	}
	
	public ArrayList<Personnage> getHabitantsEnVie() {
		return new ArrayList<Personnage>(this.village.stream().filter(x->x.estEnvie()).collect(Collectors.toList()));
	}
	
	public Meute getMeute() {
		return this.meute ;
	}
	
	public int getNbVillageois() {
		return this.getNbPersonnage() - this.getNbLoupGarou() ;
	}
	
	
	public Personnage getRandomPerso() {
		int nb = (int) (Math.random() * ( this.getHabitantsEnVie().size()    - 0 ));
		return this.getHabitantsEnVie().get(nb);
	}
	
	public int getNbLoupGarou() {
		return (int) this.getMeute().getMeute().stream().filter(x->x.estEnvie()).count();
	}
	
	
	public Personnage getPersonnageParId(int id) {
		return this.getHabitantsEnVie().stream().filter(x->x.getId() == id).findFirst().get();
	}
	
	
	public Personnage getPersonnage(int nb) {
		return this.getHabitantsEnVie().get(nb);
	}
	
	public void premièreNuit() {
		if(this.getHabitantsEnVie().stream().anyMatch(x -> x.getIdDeRole() == 4)) {
			Cupidon cupidon = (Cupidon) this.getHabitantsEnVie().stream().filter(x -> x.getIdDeRole() == 4).findFirst().get();
			cupidon.flecheDeLAmour();
		}
		if(this.getHabitantsEnVie().stream().anyMatch(x -> x.getIdDeRole() == 9)) {
			MontreurDOurs montreurDOurs = (MontreurDOurs) this.getHabitantsEnVie().stream().filter(x -> x.getIdDeRole() == 9).findFirst().get();
			montreurDOurs.setVoisins();
		}
		this.nuit();
	}
	
	public void nuit() {
		int nbHabitantAvant = this.getNbPersonnage();
		if(this.getHabitantsEnVie().stream().anyMatch(x -> x.getIdDeRole() == 8)) {
			Voyante voyante = (Voyante) this.getHabitantsEnVie().stream().filter(x -> x.getIdDeRole() == 8).findFirst().get();
			voyante.sonder();
		}
		if(this.getHabitantsEnVie().stream().anyMatch(x -> x.getIdDeRole() == 7)) {
			Salvateur salvateur = (Salvateur) this.getHabitantsEnVie().stream().filter(x -> x.getIdDeRole() == 7).findFirst().get();
			salvateur.salvater();
		}
		this.meute.attaquerVillage();
		if(this.getHabitantsEnVie().stream().anyMatch(x -> x.getIdDeRole() == 6)) {
			Sorcière sorcière = (Sorcière) this.getHabitantsEnVie().stream().filter(x -> x.getIdDeRole() == 6).findFirst().get();
			sorcière.agir();
		}
		this.decompteMort();
		
		this.getHabitantsEnVie().stream().filter(x->x.getStatut().isProtéger()).forEach(x->x.getStatut().setProtéger(false));
		int nbHabitantAprès = this.getNbPersonnage();
		if(nbHabitantAvant == nbHabitantAprès) {
			Logger.log("Personne n'a été tué durant la nuit ");
		}
		
		if(this.getNbPersonnage() >= 3) {
			if(this.getHabitantsEnVie().stream().anyMatch(x -> x.getIdDeRole() == 9)) {
				MontreurDOurs montreurDOurs = (MontreurDOurs) this.getHabitantsEnVie().stream().filter(x -> x.getIdDeRole() == 9).findFirst().get();
				montreurDOurs.traquerLoupGarous();
				if(montreurDOurs.aTrouverUnLoup()) {
					Logger.log("Le montreur d'ours a trouvé au moins un loup garous parmis ses voisins qui sont " + montreurDOurs.getVoisins() );
				}
			}
		}
		
		
	}
	
	public void decompteMort() {
		String messageMort;
		if(Logger.isAfficherLogDetailsRoleActionOn()) {
			messageMort =  " a été tué la nuit par les loups-garous";
		}
		else {
			messageMort= " a été tué durant la nuit";
		}
		
		
		this.getHabitantsEnVie().stream().filter(x->x.getStatut().estAttaquerParLg()).forEach(z->{Logger.log(z + messageMort);z.meurt();z.getStatut().setAttaquerParLg(false);});
		Logger.log("", TypeDeLog.vote);
		//.peek(e -> System.out.println(e + " a été tué la nuit par les loups-garous"))
	}
	
	public void voteEnnemie() {
		Personnage votant;
		Map<Integer, Integer> tableauDeVotes = new HashMap<>();
		int vote;
		for(int i = 0 ; i < this.getHabitantsEnVie().stream().map(x->x.getId()).reduce(Integer::max).get() + 1 ; i++) {
			tableauDeVotes.put(i, 0);
		}
		
		Logger.log("", TypeDeLog.vote);
		int voteMaire = 0;
		for(int i = 0 ; i < this.getNbPersonnage() ; i++) {
			votant = this.getHabitantsEnVie().get(i);
			vote  = votant.voter();
			if(this.aUnMaire() && votant == this.maire.getPersonnage()) {
				voteMaire  = vote;
			}
			votant.resetListeDeVote();
			Logger.log(votant + " a voté contre " + this.getPersonnageParId(vote), TypeDeLog.vote);
			tableauDeVotes.put(vote, tableauDeVotes.get(vote) + votant.getNbVote());
		}
		
		Integer plusGrandNombreDeVotesPourUnePersonne = tableauDeVotes.entrySet().stream()
				  .map(Map.Entry::getValue)
				  .reduce(Integer::max)
				  .get();
		
		List<Integer> listeIdPersonneAyantPlusDeVotes = tableauDeVotes.entrySet().stream().filter(x->x.getValue() == plusGrandNombreDeVotesPourUnePersonne).map(Map.Entry::getKey).collect(Collectors.toList());
		int idPersonneAyantPlusDeVotes;
		Personnage personnageCondamner;
		if(listeIdPersonneAyantPlusDeVotes.size() > 1) {
			// si plusieurs personnes à égaliter
			if(maire != null) {
				ArrayList<Personnage> coupables = new ArrayList<Personnage>(listeIdPersonneAyantPlusDeVotes.stream().map(id-> this.getPersonnageParId(id)).collect(Collectors.toList()));
				if(listeIdPersonneAyantPlusDeVotes.contains(voteMaire)) {
					idPersonneAyantPlusDeVotes = voteMaire;
				}
				else {
					maire.getPersonnage().setListeDeVote(coupables);
					idPersonneAyantPlusDeVotes = maire.getPersonnage().voter();
					maire.getPersonnage().resetListeDeVote();
				}
				personnageCondamner = this.getHabitantsEnVie().stream().filter(x-> x.getId() == idPersonneAyantPlusDeVotes   ).findAny().get();
				Logger.log("", TypeDeLog.vote);
				if(coupables.contains(maire.getPersonnage())) {
					Logger.log("Le maire(" + maire.getPersonnage() + ") a voté contre " + personnageCondamner + " suite à l'égalité entre " + coupables + " et lui");
				}
				else {
					Logger.log("Le maire(" + maire.getPersonnage() + ") a voté contre " + personnageCondamner + " suite à l'égalité entre " + coupables);
				}
					
				Logger.log("", TypeDeLog.vote);
				
			}
			else {
				idPersonneAyantPlusDeVotes = listeIdPersonneAyantPlusDeVotes.get((int) (Math.random() * ( listeIdPersonneAyantPlusDeVotes.size() - 0 )));
				personnageCondamner = this.getHabitantsEnVie().stream().filter(x-> x.getId() == idPersonneAyantPlusDeVotes   ).findAny().get();
				Logger.log(personnageCondamner + " a été éliminer à l'issue du vote sur une égalité");
			}
			
		}
		else {
			idPersonneAyantPlusDeVotes = listeIdPersonneAyantPlusDeVotes.get(0);
			personnageCondamner = this.getHabitantsEnVie().stream().filter(x-> x.getId() == idPersonneAyantPlusDeVotes   ).findAny().get();
			Logger.log(personnageCondamner + " a été éliminer à l'issue du vote");
		}
		Logger.log("");
		Logger.log("Le village est composé de : " + this.getHabitantsEnVie(), TypeDeLog.vote);
		Logger.log(personnageCondamner +  " est envoyé au buché avec  " + plusGrandNombreDeVotesPourUnePersonne + " vote contre lui ", TypeDeLog.vote);
		personnageCondamner.meurt();
	}
	
	public Personnage election() {
		Personnage votant;
		Map<Integer, Integer> tableauDeVotes = new HashMap<>();
		int vote;
		for(int i = 0 ; i < this.getHabitantsEnVie().stream().map(x->x.getId()).reduce(Integer::max).get() + 1 ; i++) {
			tableauDeVotes.put(i, 0);
		}
		
		Logger.log("", TypeDeLog.vote);
		for(int i = 0 ; i < this.getNbPersonnage() ; i++) {
			votant = this.getHabitantsEnVie().get(i);
			vote  = votant.élire();
			votant.resetListeDeVote();
			Logger.log(votant + " a voté pour " + this.getPersonnageParId(vote), TypeDeLog.vote);
			tableauDeVotes.put(vote, tableauDeVotes.get(vote) + votant.getNbVote());
		}
		Logger.log("", TypeDeLog.vote);
		Integer plusGrandNombreDeVotesPourUnePersonne = tableauDeVotes.entrySet().stream()
				  .map(Map.Entry::getValue)
				  .reduce(Integer::max)
				  .get();
		
		List<Integer> listeIdPersonneAyantPlusDeVotes = tableauDeVotes.entrySet().stream().filter(x->x.getValue() == plusGrandNombreDeVotesPourUnePersonne).map(Map.Entry::getKey).collect(Collectors.toList());
		int idPersonneAyantPlusDeVotes;
		Personnage personnageChoisie;
		String message = "";
		if(listeIdPersonneAyantPlusDeVotes.size() > 1) {
			idPersonneAyantPlusDeVotes = listeIdPersonneAyantPlusDeVotes.get((int) (Math.random() * ( listeIdPersonneAyantPlusDeVotes.size() - 0 )));
			personnageChoisie = this.getHabitantsEnVie().stream().filter(x-> x.getId() == idPersonneAyantPlusDeVotes   ).findAny().get();
			message = personnageChoisie +  " a été élue maire du village sur une égalité";
		
		}
		else {
			idPersonneAyantPlusDeVotes = listeIdPersonneAyantPlusDeVotes.get(0);
			personnageChoisie = this.getHabitantsEnVie().stream().filter(x-> x.getId() == idPersonneAyantPlusDeVotes   ).findAny().get();
			message = personnageChoisie +  " a été élue maire du village";
			
		}
		if(Logger.isDetailVoteVillageOn()) {
			message += " avec  " + plusGrandNombreDeVotesPourUnePersonne + " vote pour lui ";
		}
		Logger.log(message);
		return personnageChoisie;
	}
	
	public void voter(char vote) {
		Personnage personnageCondamner;
		if(vote == '0') {
			personnageCondamner = this.getHabitantsEnVie().stream().filter(x->x.estUnVillageois()).findAny().get();
			Logger.log("Un villageois a été tué lors du vote");
		}
		else {
			personnageCondamner = this.getHabitantsEnVie().stream().filter(x->!x.estUnVillageois()).findAny().get();
			Logger.log("Un loup-garou a été tué lors du vote");
		}
		personnageCondamner.meurt();
	}
	
	public void reset() {
		this.village.stream().forEach(x->x.reset());
		if(maire != null) {
			this.maire.reset();
		}
		
	}
	
	public void onMaire() {
		this.maire = new Maire(); 
		this.maire.setVillage(this);
	}
	
	
	public Maire getMaire() {
		return this.maire;
	}


	
	public Object clone() {
		Village v = new Village();
		this.village.stream().forEach(x->v.ajouterPersonnage((Personnage) x.clone()));

		v.maire = this.maire;
		return v;
	}

	
	
	public boolean aUnMaire() {
		return this.maire != null;
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
