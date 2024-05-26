package org.apache.maven.archetypes.CapitalSpéLoupGarous;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;


import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarou;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Maire;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Meute;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.TypeDeLog;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.TypeDePouvoir;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.VillageoisSpecial;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.StatsChasseur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.StatsVillage;

public  class Village  implements Cloneable {

	private ArrayList<Personnage> village;
	private  Meute meute ;
	private Maire maire = null;
	private ArrayList<Personnage> persoDevoilerCommeEnnemieParMontreursDOurs;
	private ArrayList<Personnage> persoDevoilerCommeAlliéeParMontreursDOurs;
	private Boolean nuitSansMort;
	private static StatsVillage statsVillage = new StatsVillage();
	
	public Village() {
		this.village = new ArrayList<Personnage>();
		this.meute = new Meute();
		this.meute.setVillage(this);
		this.persoDevoilerCommeAlliéeParMontreursDOurs = new ArrayList<>();
		this.persoDevoilerCommeEnnemieParMontreursDOurs = new ArrayList<>();
		this.nuitSansMort = false;
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
	
	public boolean estPresent(int idRole) {
		return this.getVillage().stream().anyMatch(x->x.getIdDeRole() == idRole);
	}
	
	public Boolean getNuitSansMort() {
		return nuitSansMort;
	}
	
	

	public void setNuitSansMort(Boolean nuitSansMort) {
		this.nuitSansMort = nuitSansMort;
	}

	public void ajouterPersonnage(Personnage personnage) {
		personnage.setId(this.village.size());
		this.village.add(personnage);
		personnage.setVillage(this);
		if(!personnage.estUnVillageois()) {
			this.meute.enrolerUnLoupGarou((LoupGarou) personnage);
		}
	}
	
	public void ajouterPlusieursPersoIdentique(int idDeRole, int nb) {
		for(int i = 0 ; i < nb ; i++) {
			Personnage perso = Référentiel.conversionDeIdRoleVersPersonnage(idDeRole);
			this.ajouterPersonnage(perso);
		}
	}
	
	public void ajouterSpé(int idDeRole) {
		Personnage perso = Référentiel.conversionDeIdRoleVersPersonnage(idDeRole);
		this.ajouterPersonnage(perso);

	}
	
	
	public ArrayList<Personnage> getVillage() {
		return this.village;
	}
	
	public long getNbSpéEnVieACePouvoir(TypeDePouvoir typeDePouvoir) {
		return this.getHabitantsEnVie().stream().filter(VillageoisSpecial.class::isInstance).map(c -> (VillageoisSpecial) c).filter(x->x.aCePouvoir(typeDePouvoir)).count();
	}
	
	public boolean estEnVie(int idRole) {
		return this.getHabitantsEnVie().stream().anyMatch(x->x.getIdDeRole() == idRole);
	}
	
	
	public int getNbPersonnageEnVie() {
		return (int) this.village.stream().filter(x->x.estEnvie()).count();
	}
	
	public ArrayList<Personnage> getVillageois() {
		return new ArrayList<Personnage>(this.village.stream().filter(x->x.estEnvie() && x.estUnVillageois()).collect(Collectors.toList()));
	}
	
	public ArrayList<Personnage> getHabitantsEnVie() {
		return new ArrayList<>(this.village.stream().filter(x->x.estEnvie()).collect(Collectors.toList()));
	}
	
	public ArrayList<Personnage> getAutreHabitantsEnVie(Personnage personnage) {
		return new ArrayList<>(this.village.stream().filter(x->x.estEnvie() && x!= personnage).collect(Collectors.toList()));
	}
	
	public Meute getMeute() {
		return this.meute ;
	}
	
	public int getNbVillageois() {
		return this.getNbPersonnageEnVie() - this.getNbLoupGarou() ;
	}
	
	
	public Personnage getRandomPerso() {
		int nb = (int) (Math.random() * ( this.getHabitantsEnVie().size()    - 0 ));
		return this.getHabitantsEnVie().get(nb);
	}
	
	public int getNbLoupGarou() {
		return (int) this.getMeute().getMeute().stream().filter(x->x.estEnvie()).count();
	}
	
	
	public Personnage getPersonnageParId(int id) {
		return this.village.stream().filter(x->x.getId() == id).findFirst().get();
	}
	
	public Personnage getPersonnageParIdRole(int idRole) {
		return this.village.stream().filter(x->x.getIdDeRole() == idRole).findFirst().get();
	}
	
	
	public Personnage getPersonnage(int nb) {
		return this.getHabitantsEnVie().get(nb);
	}
	
	Comparator<Personnage> comparator = Comparator.comparing(obj -> obj.getIdDeRole());
	
	public void premièreNuit() {
		this.getHabitantsEnVie().stream().sorted(comparator).forEach(x->x.agirPremiereNuit());
		this.nuit();
	}
	
	public void nuit() {
		this.nuitSansMort = false;
		int nbHabitantAvant = this.getNbPersonnageEnVie();
		this.getHabitantsEnVie().stream().sorted(comparator).forEach(x->x.agir());
		
		this.bilanTuerLaNuit();
		
		this.getHabitantsEnVie().stream().filter(x->x.getStatut().isProtéger()).forEach(x->x.getStatut().setProtéger(false));
		int nbHabitantAprès = this.getNbPersonnageEnVie();
		if(nbHabitantAvant == nbHabitantAprès) {
			Logger.log("Personne n'a été tué durant la nuit ");
			this.nuitSansMort = true;
		}
		if((this.getNbPersonnageEnVie() > 2) ) {// si la partie est pas terminé
			this.getHabitantsEnVie().stream().sorted(comparator).distinct().forEach(x->x.agirAprèsNuit());
		}
	}
	
	public void bilanTuerLaNuit() {
		String messageMort;
		if(Logger.isAfficherLogDetailsRoleActionOn()) {
			messageMort =  " a été tué la nuit par les loups-garous";
		}
		else {
			messageMort= " a été tué durant la nuit";
		}
		
		
		this.getHabitantsEnVie().stream().filter(x->x.getStatut().aEteAttaquerParLaMeute()).forEach(z->{Logger.log(z + messageMort);this.getMeute().devorer(z);z.getStatut().setAEteAttaqueParLaMeute(false);});
		Logger.log("", TypeDeLog.vote);
		this.getMeute().setEstRassasier(false);
	}
	
	public void tribunal() {
		Personnage votant;
		Map<Integer, Integer> tableauDeVotes = new HashMap<>();
		int vote;
		for(int i = 0 ; i < this.getHabitantsEnVie().stream().map(x->x.getId()).reduce(Integer::max).get() + 1 ; i++) {
			tableauDeVotes.put(i, 0);
		}
		
		Logger.log("", TypeDeLog.vote);
		int voteMaire = 0;
		for(int i = 0 ; i < this.getNbPersonnageEnVie() ; i++) {
			votant = this.getHabitantsEnVie().get(i);
			vote  = votant.voter();
			if(this.aUnMaire() && votant == this.maire.getPersonnage()) {
				voteMaire  = vote;
			}
			if(votant.getId() == vote) {
				System.out.println("erreur détecté : " + votant + " a voté contre lui-même");
			}
			votant.resetListeDeVote();
			Logger.log(votant + " a voté contre " + this.getPersonnageParId(vote) + " avec " + votant.getNbVote() + " voix ", TypeDeLog.vote);
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
					
				}
				
				personnageCondamner = this.getHabitantsEnVie().stream().filter(x-> x.getId() == idPersonneAyantPlusDeVotes   ).findAny().get();
				Logger.log("", TypeDeLog.vote);
				if(coupables.isEmpty()) {
					System.out.println("Erreur maire égalité"+listeIdPersonneAyantPlusDeVotes + this.getPersonnageParId(listeIdPersonneAyantPlusDeVotes.get(0)));
				}
				if(coupables.contains(maire.getPersonnage())) {
					coupables.remove(maire.getPersonnage());
					Logger.log("Le maire(" + maire.getPersonnage() + ") a voté contre " + personnageCondamner + " suite à l'égalité entre " + coupables + " et lui");
				}
				else {
					Logger.log("Le maire(" + maire.getPersonnage() + ") a voté contre " + personnageCondamner + " suite à l'égalité entre " + coupables);
				}
				maire.getPersonnage().resetListeDeVote();
				Logger.log(personnageCondamner + " a été éliminer à l'issue du vote");
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
			Logger.log("", TypeDeLog.vote);
			Logger.log(personnageCondamner + " a été éliminer à l'issue du vote");
		}
		Logger.log("");
		Logger.log("Le village est composé de : " + this.getHabitantsEnVie(), TypeDeLog.vote);
		Logger.log(personnageCondamner +  " est envoyé au buché avec  " + plusGrandNombreDeVotesPourUnePersonne + " vote contre lui ", TypeDeLog.vote);
		this.executer(personnageCondamner);
		this.getStatsVillage().vote(personnageCondamner);
	}
	
	public Personnage election() {
		Personnage votant;
		Map<Integer, Integer> tableauDeVotes = new HashMap<>();
		int vote;
		for(int i = 0 ; i < this.getHabitantsEnVie().stream().map(x->x.getId()).reduce(Integer::max).get() + 1 ; i++) {
			tableauDeVotes.put(i, 0);
		}
		
		Logger.log("", TypeDeLog.vote);
		for(int i = 0 ; i < this.getNbPersonnageEnVie() ; i++) {
			votant = this.getHabitantsEnVie().get(i);
			vote  = votant.elire();
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
	
	
	
	public ArrayList<Personnage> getPersoDevoilerCommeEnnemieParMontreursDOurs() {
		return persoDevoilerCommeEnnemieParMontreursDOurs;
	}

	public void setPersoDevoilerCommeEnnemieParMontreursDOurs(
			ArrayList<Personnage> persoDevoilerCommeEnnemieParMontreursDOurs) {
		this.persoDevoilerCommeEnnemieParMontreursDOurs = persoDevoilerCommeEnnemieParMontreursDOurs;
	}

	public ArrayList<Personnage> getPersoDevoilerCommeAlliéeParMontreursDOurs() {
		return persoDevoilerCommeAlliéeParMontreursDOurs;
	}

	public void setPersoDevoilerCommeAlliéeParMontreursDOurs(
			ArrayList<Personnage> persoDevoilerCommeAlliéeParMontreursDOurs) {
		this.persoDevoilerCommeAlliéeParMontreursDOurs = persoDevoilerCommeAlliéeParMontreursDOurs;
	}
	
	

	public static StatsVillage getStatsVillage() {
		return statsVillage;
	}

	public static void setStatsVillage(StatsVillage statsVillage) {
		Village.statsVillage = statsVillage;
	}

	public void executer(Personnage personnage) {
		personnage.getStatut().setTueur(SimpleVillageois.IDROLE);
		personnage.meurt();
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

	
	
	public boolean aUnMaire() {
		return this.maire != null;
	}
	
	public void finVillage() {
		this.statsVillage.decompteNbSurvivants(village);
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
