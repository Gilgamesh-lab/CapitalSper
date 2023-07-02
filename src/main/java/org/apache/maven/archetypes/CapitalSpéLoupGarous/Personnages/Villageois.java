package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Log;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Partie;

public abstract class Villageois extends Personnage {
	private ArrayList<Personnage> alliés;
	
	public Villageois() {
		super(true);
		this.alliés = new ArrayList<Personnage>();
	}
	
	@Override
	public int voter() {
		this.resetListeDeVote();
		for(int i = 0; i < this.getVillage().getNbPersonnage(); i++) {
			this.getListeDeVote().add(this.getVillage().getVillage().get(i));
		}
		
		for(int i = 0 ; i < this.getAlliés().size() ; i++) {
			this.getListeDeVote().remove(this.getAlliés().get(i));
		}
		this.getListeDeVote().remove(this);
		int nb = (int) (Math.random() * ( this.getListeDeVote().size()    - 0 ));
		Log.println(this + " a voté contre " + this.getListeDeVote().get(nb), 1);
		return this.getListeDeVote().get(nb).getId();
		
	}

	public ArrayList<Personnage> getAlliés() {
		return alliés;
	}

	public void setAlliés(ArrayList<Personnage> alliés) {
		this.alliés = alliés;
	}
	
	@Override
	public void meurt() {
		super.meurt();
		this.getVillage().getVillageois().remove(this);
	}
	
	
	

}
