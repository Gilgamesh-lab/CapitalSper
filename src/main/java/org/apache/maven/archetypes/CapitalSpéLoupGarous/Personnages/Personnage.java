package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Objects;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;

public abstract class Personnage {
	
	private boolean estUnVillageois;
	private int id;
	private int idDeRole;
	private Village village;
	private ArrayList<Personnage> listeDeVote;
	
	public Personnage(Boolean estUnVillageois, int idDeRole) {
		this.estUnVillageois = estUnVillageois;
		this.idDeRole = idDeRole;
		this.listeDeVote = new ArrayList<Personnage>();
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
	
	public void resetListeDeVote() {
		this.listeDeVote = new ArrayList<Personnage>();
	}
	

	

	public boolean estUnVillageois() {
		return estUnVillageois;
	}
	
	public abstract void agir();
	
	public void meurt() {
		this.village.getHabitants().remove(this);
	}

	public void setVillage(Village village) {
		this.village = village;
	}
	
	public abstract int voter(); 
	
	

	public void setEstUnVillageois(boolean estUnVillageois) {
		this.estUnVillageois = estUnVillageois;
	}


	public int getId() {
		return id;
	}

	public Village getVillage() {
		return village;
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
