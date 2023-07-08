package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

public abstract class PersonnageSpecial extends Personnage {

	public PersonnageSpecial(int idDeRole) {
		super(true, idDeRole);
	}
	
	public  abstract void agir();

}
