package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.StatsPersonnages.StatPersonnage;

public abstract class Personnage  implements Cloneable {
	
	private boolean estUnVillageois;
	private int id;
	private int idDeRole;
	private Village village;
	private ArrayList<Personnage> listeDeVote;
	private Statut statut;
	private ArrayList<Personnage> alliés;
	private boolean aUnPouvoirSpecial;
	private ArrayList<Personnage> listeEnnemie;
	private int nbVote = 1;
	private Fonction fonction = null;
	private StatPersonnage statPersonnage;
	
	
	protected Personnage(Boolean estUnVillageois, int idDeRole, boolean aUnPouvoirSpecial, StatPersonnage statPersonnage) {
		this.estUnVillageois = estUnVillageois;
		this.idDeRole = idDeRole;
		this.listeDeVote = new ArrayList<>();
		this.statut = new Statut(this);
		this.alliés = new ArrayList<>();
		this.aUnPouvoirSpecial = aUnPouvoirSpecial;
		this.listeEnnemie = new ArrayList<>();
		this.statPersonnage  = statPersonnage;
	}
	
	protected Personnage(Boolean estUnVillageois, int idDeRole, boolean aUnPouvoirSpecial) {
		this.estUnVillageois = estUnVillageois;
		this.idDeRole = idDeRole;
		this.listeDeVote = new ArrayList<>();
		this.statut = new Statut(this);
		this.alliés = new ArrayList<>();
		this.aUnPouvoirSpecial = aUnPouvoirSpecial;
		this.listeEnnemie = new ArrayList<>();
		this.statPersonnage  = null;
	}
	
	public void tuer(Personnage personnage) {
		personnage.getStatut().setTueur(this.getIdDeRole());
		personnage.meurt();
	}
	
	
	


	public void setStatPersonnage(StatPersonnage statPersonnage) {
		this.statPersonnage = statPersonnage;
	}

	public String getStats() {
		if(this.statPersonnage != null) {
			return this.statPersonnage.getStats();
		}
		else {
			return "";
		}
		
	}
	
	
	
	public StatPersonnage getStatPersonnage() {
		return statPersonnage;
	}

	public void agirPremiereNuit() {}

	public void agir() {}

	public void agirAprèsNuit() {}
	
	
	public boolean aUnPouvoirSpecial() {
		return aUnPouvoirSpecial;
	}
	
	public void reset() {
		this.statut = new Statut(this);
		this.listeDeVote.clear();
		this.listeEnnemie.clear();
		this.alliés.clear();
		this.nbVote = 1;
		this.fonction = null;
		this.alliés.add(this);
	}
	
	
	public ArrayList<Personnage> getEnnemies() {
		return this.listeEnnemie;
	}

	public void setEnnemies(ArrayList<Personnage> ennemies) {
		this.listeEnnemie = ennemies;
	}
	
	public void ajouterEnnemies(Personnage ennemies) {
		if(!this.alliés.contains(ennemies)) {
			Logger.log( this + " considère maintenant " + ennemies +  " comme un ennemie", TypeDeLog.vote);
			this.listeEnnemie.add(ennemies);
		}
		
	}


	public ArrayList<Personnage> getAlliés() {
		return alliés ; 
				
	}
	

	public void setAlliés(ArrayList<Personnage> alliés) {
		this.alliés = alliés;
	}
	
	public void ajouterAlliés(Personnage allié) {
		if(!this.alliés.contains(allié)) {
			Logger.log( this + " considère maintenant " + allié +  " comme un allié", TypeDeLog.vote);
			this.alliés.add(allié);
		}
	}


	public ArrayList<Personnage> getListeDeVote() {
		return listeDeVote;
	}

	public void setListeDeVote(ArrayList<Personnage> listeDeVote) {
		this.listeDeVote = listeDeVote;
	}
	
	public void ajouterPersoListeDeVote(Personnage personnage) {
		this.listeDeVote.add(personnage);
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void tomberAmoureux(Personnage amoureux) {
		this.statut.setAmoureux(amoureux);
		this.ajouterAlliés(amoureux);
	}
	
	public void resetListeDeVote() {
		this.listeDeVote.clear();
	}
	

	

	public boolean estUnVillageois() {
		return estUnVillageois;
	}
	
	public boolean estAmoureux() {
		return this.statut.isEstAmoureux();
	}
	
	public Personnage getAmoureux() {
		return this.statut.getAmoureux();
	}
	
	public boolean estEnvie() {
		return this.statut.estEnVie();
	}
	
	public Statut getStatut() {
		return this.statut;
	}
	
	
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	
	public void meurt() {
		this.statut.estMort();
		if(this.getVillage().getPersoDevoilerCommeEnnemieParMontreursDOurs().contains(this)) {
			this.getVillage().getPersoDevoilerCommeEnnemieParMontreursDOurs().remove(this);
			if(!this.estUnVillageois()) {
				if(!this.getVillage().getPersoDevoilerCommeEnnemieParMontreursDOurs().isEmpty()) {
					Logger.log("Vue que "+ this + " était un loup-garous, les soupçons sur " + this.getVillage().getPersoDevoilerCommeEnnemieParMontreursDOurs().get(0) +  " suite aux révélations du Montreurs d' Ours sont lévées");
				}
				this.getVillage().getPersoDevoilerCommeEnnemieParMontreursDOurs().clear();
			}
			
		}
		
		if(this.estAmoureux() && this.statut.getAmoureux().getStatut().estEnVie()) {
			Logger.log("Suite à la mort de " + this + " , " + this.statut.getAmoureux() +  " fut emporté par le chagrin");
			this.statut.getAmoureux().getStatut().setTueur(Cupidon.IDROLE);
			this.statut.getAmoureux().meurt();
		}
		if(this.getFonction() != null && this.getVillage().getNbPersonnageEnVie() > 2) {
			this.village.getMaire().election();
		}
	}

	public void setVillage(Village village) {
		this.village = village;
	}
	
 // à améliorer
	public int voter() {
		int nb ;
		if(this.getListeDeVote().isEmpty() &&  this.getEnnemies().size() > 0) {
			for(int i = 0; i < this.getEnnemies().size(); i++) {
				this.getListeDeVote().add(this.village.getPersonnageParId(this.getEnnemies().get(i).getId()));
			}
			this.getEnnemies().stream().filter(x-> !this.village.getPersonnageParId(x.getId()).estEnvie() ||  x == this ).forEach(x->this.getListeDeVote().remove(x));// impossible d'itérer sur soi-même

			
			if(!this.getListeDeVote().isEmpty()) {
				nb = (int) (Math.random() * ( this.getListeDeVote().size()    - 0 ));
				return this.getListeDeVote().get(nb).getId();
			}
		}
		if(!this.village.getPersoDevoilerCommeEnnemieParMontreursDOurs().isEmpty()) {
			this.listeDeVote = new ArrayList<Personnage>(this.village.getPersoDevoilerCommeEnnemieParMontreursDOurs().stream().filter(x-> x!= this).collect(Collectors.toList()));
			if(!this.listeDeVote.isEmpty()) {
				nb = (int) (Math.random() * ( this.listeDeVote.size()    - 0 ));
				return this.listeDeVote.get(nb).getId();
			}
			
		}
			
		if(this.getListeDeVote().isEmpty()) {// on remplit la liste des personnes présentes dans le village
			for(int i = 0; i < this.getVillage().getAutreHabitantsEnVie(this).size() ; i++) {
				if(!this.alliés.contains(this.getVillage().getAutreHabitantsEnVie(this).get(i)) && (!this.getVillage().getPersoDevoilerCommeAlliéeParMontreursDOurs().contains(this.getVillage().getAutreHabitantsEnVie(this).get(i)))){
					this.getListeDeVote().add(this.getVillage().getAutreHabitantsEnVie(this).get(i));
				}
				
			}
		}
		if(this.getListeDeVote().isEmpty()) {// si pas de vote possible
			if(this.estAmoureux()) {// si amoureux on vire tout le monde sauf amoureux "alliés" compris
				this.listeDeVote = new ArrayList<Personnage>(this.village.getAutreHabitantsEnVie(this).stream().filter(x->this.getAmoureux() != x).collect(Collectors.toList()));
				nb = (int) (Math.random() * ( this.getListeDeVote().size()    - 0 ));
				return this.getListeDeVote().get(nb).getId();	
			}
			else {// sinon au hasard parmis tout le monde "alliés" compris
				nb = (int) (Math.random() * ( this.village.getAutreHabitantsEnVie(this).size()   - 0 ));
				return this.village.getAutreHabitantsEnVie(this).get(nb).getId();	
			}
		}
		else {// vote normale
			if(this.getListeDeVote().contains(this)) {// Cas égaliter quand le maire est présent dans les coupables
				this.getListeDeVote().remove(this);
			}
			nb = (int) (Math.random() * ( this.getListeDeVote().size()   - 0 ));
			return this.getListeDeVote().get(nb).getId();
		}
		
		
			
	}
	
// à améliorer
	public int elire() {
		int nb ;
		if (!this.village.getHabitantsEnVie().isEmpty()){ // a modifier
			if(this.getListeDeVote().isEmpty() && !this.getAlliés().isEmpty() && this.getAlliés().stream().anyMatch(x->  x.estEnvie()) && this.estUnVillageois()) {// contrer lg qui vote tout le temps pour eux
				this.listeDeVote = new ArrayList<Personnage>(this.village.getHabitantsEnVie().stream().filter(x->this.getAlliés().contains(x)).collect(Collectors.toList()));
				this.listeDeVote.add(this);
				nb = (int) (Math.random() * ( this.listeDeVote.size()   - 0 ));
				return this.listeDeVote.get(nb).getId();
			}
			else if(!this.getListeDeVote().isEmpty()) {
				nb = (int) (Math.random() * ( this.getListeDeVote().size()    - 0 ));
				return this.getListeDeVote().get(nb).getId();
			}
			else {
				this.listeDeVote = new ArrayList<Personnage>(this.village.getHabitantsEnVie().stream().filter(x->!this.getEnnemies().contains(x)).collect(Collectors.toList()));// retirer ennemie
				if (this.listeDeVote.isEmpty()){
					nb = (int) (Math.random() * ( this.village.getHabitantsEnVie().size()    - 0 ));
					return this.village.getHabitantsEnVie().get(nb).getId();
				}
				else {
					nb = (int) (Math.random() * ( this.getListeDeVote().size()    - 0 ));
					return this.getListeDeVote().get(nb).getId();
				}
				
			}
		}
		
		return 0;
		
		
		
		
			
	}


	public int getId() {
		return id;
	}

	public Village getVillage() {
		return village;
	}
	
	public int getIdDeRole() {
		return idDeRole;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personnage other = (Personnage) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getNbVote() {
		return nbVote;
	}

	public void setNbVote(int vote) {
		this.nbVote = vote;
	}
	
	public void incrementerNbVote(int vote) {
		this.nbVote += vote;
	}
	
	

	public Fonction getFonction() {
		return fonction;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}
	
	public boolean estMaire() {
		if(this.fonction != null && this.fonction.estMaire()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + this.getId();
	}
	
	


	
	
	


	
}
