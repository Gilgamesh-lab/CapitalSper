package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;

public abstract class Personnage {
	
	private boolean estUnVillageois;
	private int id;
	private int idDeRole;
	private Village village;
	private ArrayList<Personnage> listeDeVote;
	private Statut statut;
	private ArrayList<Personnage> alliés;
	private boolean aUnPouvoirSpecial;
	private ArrayList<Personnage> listeEnnemie;
	
	public Personnage(Boolean estUnVillageois, int idDeRole, boolean aUnPouvoirSpecial) {
		this.estUnVillageois = estUnVillageois;
		this.idDeRole = idDeRole;
		this.listeDeVote = new ArrayList<Personnage>();
		this.statut = new Statut(this);
		this.alliés = new ArrayList<Personnage>();
		this.aUnPouvoirSpecial = aUnPouvoirSpecial;
		this.alliés.add(this);
		this.listeEnnemie = new ArrayList<Personnage>();
	}
	
	public void tuer(Personnage personnage) {
		personnage.getStatut().setTueur(this.getIdDeRole());
		personnage.meurt();
	}
	
	
	
	public boolean aUnPouvoirSpecial() {
		return aUnPouvoirSpecial;
	}
	
	public void reset() {
		this.alliés.clear();
		this.alliés.add(this);
		this.statut = new Statut(this);
		this.listeEnnemie.clear();
	}
	
	
	public ArrayList<Personnage> getEnnemies() {
		return this.listeEnnemie;
	}

	public void setEnnemies(ArrayList<Personnage> ennemies) {
		this.listeEnnemie = ennemies;
	}
	
	public void ajouterEnnemies(Personnage ennemies) {
		this.listeEnnemie.add(ennemies);
	}


	public ArrayList<Personnage> getAlliés() {
		return alliés;
	}

	public void setAlliés(ArrayList<Personnage> alliés) {
		this.alliés = alliés;
	}
	
	public void ajouterAlliés(Personnage allié) {
		this.alliés.add(allié);
	}


	public ArrayList<Personnage> getListeDeVote() {
		return listeDeVote;
	}

	public void setListeDeVote(ArrayList<Personnage> listeDeVote) {
		this.listeDeVote = listeDeVote;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void tomberAmoureux(Personnage amoureux) {
		this.statut.setAmoureux(amoureux);
		this.ajouterAlliés(amoureux);
	}
	
	public void resetListeDeVote() {
		this.listeDeVote = new ArrayList<Personnage>();
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
		if(this.estAmoureux() && this.statut.getAmoureux().getStatut().estEnVie()) {
			Logger.log("Suite à la mort de " + this + " , " + this.statut.getAmoureux() +  " fut emporté par le chagrin");
			this.statut.getAmoureux().meurt();
		}
	}

	public void setVillage(Village village) {
		this.village = village;
	}
	
	public Personnage clone() {
		return this.clone();
	}
	
	@SuppressWarnings("finally") // à améliorer
	public int voter() {
		int nb ;
		this.getListeDeVote().clear();
		if(this.getEnnemies().size() > 0) {
			for(int i = 0; i < this.getListeDeVote().size(); i++) {
				this.getListeDeVote().add(this.getEnnemies().get(i));
			}
		}
		this.getEnnemies().stream().filter(x-> x != null && !x.estEnvie()).forEach(x->this.getListeDeVote().remove(x));
		if(this.getListeDeVote().size() > 0) {
			nb = (int) (Math.random() * ( this.getListeDeVote().size()    - 0 ));
			Logger.log(this + " a voté contre " + this.getListeDeVote().get(nb), TypeDeLog.vote);
			return this.getListeDeVote().get(nb).getId();
		}
		else {
			this.getListeDeVote().clear();
			for(int i = 0; i < this.getVillage().getNbPersonnage(); i++) {
				if(!this.alliés.contains(this.getVillage().getHabitants().get(i))){
					this.getListeDeVote().add(this.getVillage().getHabitants().get(i));
				}
				
			}
			if(this.getListeDeVote().size() == 0) {
				if(this.estAmoureux()) {
					this.listeDeVote = new ArrayList<Personnage>(this.village.getHabitants().stream().filter(x->this.getAmoureux() != x).collect(Collectors.toList()));
					nb = (int) (Math.random() * ( this.getListeDeVote().size()    - 0 ));
					return this.getListeDeVote().get(nb).getId();	
				}
				else {
					nb = (int) (Math.random() * ( this.village.getNbPersonnage()   - 0 ));
					Logger.log(this + " a voté contre " + this.village.getPersonnage(nb), TypeDeLog.vote);
					return this.village.getPersonnage(nb).getId();	
				}
			}
			else {
				nb = (int) (Math.random() * ( this.getListeDeVote().size()   - 0 ));
				Logger.log(this + " a voté contre " + this.getListeDeVote().get(nb), TypeDeLog.vote);
				
				return this.getListeDeVote().get(nb).getId();
			}
		}
		
			
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


	

	

	



	/*@Override
	public int hashCode() {
		return Objects.hash(aUnPouvoirSpecial, alliés, estUnVillageois, id, idDeRole, listeDeVote, statut, village);
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
		return aUnPouvoirSpecial == other.aUnPouvoirSpecial && Objects.equals(alliés, other.alliés)
				&& estUnVillageois == other.estUnVillageois && id == other.id && idDeRole == other.idDeRole
				&& Objects.equals(listeDeVote, other.listeDeVote) && Objects.equals(statut, other.statut)
				&& Objects.equals(village, other.village);
	}*/



	@Override
	public String toString() {
		return this.getClass().getSimpleName() + this.getId();
	}
	
	


	
	
	


	
}
