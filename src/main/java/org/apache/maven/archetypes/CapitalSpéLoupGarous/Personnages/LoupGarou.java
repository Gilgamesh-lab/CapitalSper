package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

public class LoupGarou extends Personnage {
	private Meute meute;
	
	

	public LoupGarou(int idRole, boolean aUnPouvoirSpecial) {
		super(false, idRole, aUnPouvoirSpecial);
	}

	
	public Meute getMeute() {
		return this.meute;
	}

	public void rejoindreUneMeute(Meute meute) {
		this.meute = meute;
	}
	
	public void reset() {
		super.reset();
		this.meute.enrolerUnLoupGarou(this);
	}




}
