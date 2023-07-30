package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Objects;

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
	
	public Personnage(Boolean estUnVillageois, int idDeRole, boolean aUnPouvoirSpecial) {
		this.estUnVillageois = estUnVillageois;
		this.idDeRole = idDeRole;
		this.listeDeVote = new ArrayList<Personnage>();
		this.statut = new Statut(this);
		this.alliés = new ArrayList<Personnage>();
		this.aUnPouvoirSpecial = aUnPouvoirSpecial;
		this.alliés.add(this);
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
	
	public abstract void agir();
	
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
		this.statut.setMortRecemment(true);
		if(this.estAmoureux() && this.statut.getAmoureux().getStatut().estEnVie()) {
			//this.statut.setMortRecemment(false);
			Logger.log("Suite à la mort de " + this + " , " + this.statut.getAmoureux() +  " fut emporté par le chagrin");
			this.statut.getAmoureux().meurt();
		}
		
		//this.village.getHabitants().remove(this);
	}

	public void setVillage(Village village) {
		this.village = village;
	}
	
	@SuppressWarnings("finally")
	public int voter() {
		this.getListeDeVote().clear();
		for(int i = 0; i < this.getVillage().getNbPersonnage(); i++) {
			this.getListeDeVote().add(this.getVillage().getHabitants().get(i));
		}
		for(int i = 0 ; i < this.getAlliés().size() ; i++) {
			this.getListeDeVote().remove(this.getAlliés().get(i));
		}
		int nb = (int) (Math.random() * ( this.getListeDeVote().size()    - 0 ));
		Logger.log(this + " a voté contre " + this.getListeDeVote().get(nb), "vote");
		
		return this.getListeDeVote().get(nb).getId();	
	}
	
	

	public void setEstUnVillageois(boolean estUnVillageois) {
		this.estUnVillageois = estUnVillageois;
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
