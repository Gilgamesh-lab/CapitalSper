package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarous;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.Statistiques;

public abstract class LoupGarou extends Personnage {
	private Meute meute;
	
	public LoupGarou(int idRole, boolean aUnPouvoirSpecial) {
		super(false, idRole, aUnPouvoirSpecial);
	}
	
	public LoupGarou(int idRole, boolean aUnPouvoirSpecial, Statistiques statPersonnage) {
		super(true, idRole, aUnPouvoirSpecial, statPersonnage);
		
	}
	
	public Meute getMeute() {
		return this.meute;
	}

	public void rejoindreUneMeute(Meute meute) {
		this.meute = meute;
	}
	
	public void reset() {
		super.reset();
	}

	@Override
	public void agir() {
		this.meute.attaquerVillage();
	}


	@Override
	public void agirAprèsNuit() {
		if(this.getVillage().getNuitSansMort()) {
			Meute.getStatsMeute().ASurvecu(this.getVillage().getVillage());
		}
		
		
	}




}
