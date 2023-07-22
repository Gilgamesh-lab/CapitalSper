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
	
	
	
	public boolean aUnPouvoirSpecial() {
		return aUnPouvoirSpecial;
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
		if(this.estAmoureux() && this.statut.getAmoureux().getStatut().estEnVie()) {
			Logger.log("Suite à la mort de " + this + " , " + this.statut.getAmoureux() +  " fut emporté par le chagrin");
			this.statut.getAmoureux().meurt();
		}
		this.statut = new Statut(this);
		this.village.getHabitants().remove(this);
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
		try {
			Logger.log(this + " a voté contre " + this.getListeDeVote().get(nb), "vote");
			
		}
		
		catch ( IndexOutOfBoundsException e ){
			System.out.println(this);
			System.out.println("Personnage = " + this.village.getHabitants());
			System.out.println("Alliée = " + this.getAlliés());
			System.out.println("Liste de Vote = " + this.getListeDeVote());
			throw e;
		}
		
		finally {
			return this.getListeDeVote().get(nb).getId();	
		}
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (estUnVillageois ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((listeDeVote == null) ? 0 : listeDeVote.hashCode());
		result = prime * result + ((village == null) ? 0 : village.hashCode());
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
		if (estUnVillageois != other.estUnVillageois)
			return false;
		if (id != other.id)
			return false;
		if (listeDeVote == null) {
			if (other.listeDeVote != null)
				return false;
		} else if (!listeDeVote.equals(other.listeDeVote))
			return false;
		if (village == null) {
			if (other.village != null)
				return false;
		} else if (!village.equals(other.village))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return this.getClass().getSimpleName() + this.getId();
	}
	
	


	
	
	


	
}
