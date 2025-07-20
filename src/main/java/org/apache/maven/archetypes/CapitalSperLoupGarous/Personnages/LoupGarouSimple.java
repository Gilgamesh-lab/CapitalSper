package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.TypeDePouvoir;


public class LoupGarouSimple extends LoupGarou {
	public final static int IDROLE = 15;
	
	public LoupGarouSimple() {
		super(IDROLE, true);
	}

	@Override
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Mort));
	}


}
