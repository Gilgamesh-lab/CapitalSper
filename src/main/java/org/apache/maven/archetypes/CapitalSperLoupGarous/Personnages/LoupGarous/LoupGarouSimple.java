package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarous;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.TypeDePouvoir;

public class LoupGarouSimple extends LoupGarou {
	public final static int IDROLE = 15;
	
	public LoupGarouSimple() {
		super(IDROLE, false);
	}

	@Override
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Mort));
	}


}
