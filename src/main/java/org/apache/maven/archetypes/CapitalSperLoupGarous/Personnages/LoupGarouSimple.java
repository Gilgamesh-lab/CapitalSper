<<<<<<< HEAD
package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages;
=======
package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.LoupGarous;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.TypeDePouvoir;
>>>>>>> infecter

public class LoupGarouSimple extends LoupGarou {
	public final static int IDROLE = 15;
	
	public LoupGarouSimple() {
<<<<<<< HEAD
		super(IDROLE, true);
=======
		super(IDROLE, false);
	}

	@Override
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Mort));
>>>>>>> infecter
	}


}
