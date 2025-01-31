package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages;

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
	}

	@Override
	public void agir() {
		this.meute.attaquerVillage();
		
	}


	@Override
	public void agirAprèsNuit() {
		if(this.getVillage().getNuitSansMort()) {
			this.getMeute().getStatsMeute().ASurvecu(this.getVillage().getVillage());
		}
		
		
	}




}
