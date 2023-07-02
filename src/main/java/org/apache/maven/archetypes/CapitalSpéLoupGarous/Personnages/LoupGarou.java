package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Log;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Partie;

public class LoupGarou extends Personnage {
	private Meute meute;
	
	

	public LoupGarou() {
		super(false);
	}
	
	@Override
	public void meurt() {
		super.meurt();
		this.meute.getMeute().remove(this);
	}

	@Override
	public void agir() {
		// TODO Auto-generated method stub
		
	}
	
	public Meute getMeute() {
		return this.meute;
	}

	public void rejoindreUneMeute(Meute meute) {
		this.meute = meute;
	}

	@Override
	public int voter() {
		this.getListeDeVote().clear();
		for(int i = 0; i < this.getVillage().getNbPersonnage(); i++) {
			this.getListeDeVote().add(this.getVillage().getVillage().get(i));
		}
		for(int i = 0 ; i < this.getMeute().getMeute().size() ; i++) {
			this.getListeDeVote().remove(this.getMeute().getMeute().get(i));
		}
		int nb = (int) (Math.random() * ( this.getListeDeVote().size()   - 0 ));
		if(Partie.log.isDetailVoteVillageOn()) {
			Log.println(this + " a voté contre " + this.getListeDeVote().get(nb));
		}
		return this.getListeDeVote().get(nb).getId();
		
	}


}
