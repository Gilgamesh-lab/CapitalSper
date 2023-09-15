package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Partie;

public class LoupGarou extends Personnage {
	private Meute meute;
	
	

	public LoupGarou(int idRole, boolean aUnPouvoirSpecial) {
		super(false, idRole, aUnPouvoirSpecial);
	}
	
	@Override
	public void meurt() {
		super.meurt();
		this.meute.getMeute().remove(this);
	}

	
	public Meute getMeute() {
		return this.meute;
	}

	public void rejoindreUneMeute(Meute meute) {
		this.meute = meute;
	}
	
	public void reset() {
		this.getAlliés().clear();
		this.getAlliés().add(this);
		this.meute.getMeute().stream().filter(x->x!=this).forEach(x->this.ajouterAlliés(x));
		super.reset();
	}




}
