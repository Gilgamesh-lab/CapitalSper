package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Villageois;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.TypeDePouvoir;

public class SimpleVillageois extends Villageois {
	public final static int IDROLE = 21;
	
	public SimpleVillageois() {
		super(IDROLE, false);
	}

	@Override
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Aucun));
	}

	


	
	/*@Override
	public String toString() {
		return "SimpleVillageois " + this.getId();
	}*/

}
