package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

public abstract class PersonnageSpecial extends Villageois {

	public PersonnageSpecial(int idDeRole) {
		super(idDeRole);
	}
	
	public  abstract void agir();

}
