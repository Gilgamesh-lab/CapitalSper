package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarous;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.TypeDePouvoir;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.Statistiques;

public abstract class LoupGarouSpecial extends LoupGarou {
	private ArrayList<TypeDePouvoir> typeDePouvoir;

	public LoupGarouSpecial(int idRole) {
		super(idRole, true);
	}
	
	protected LoupGarouSpecial(int idDeRole, Statistiques statPersonnage) {
		super(idDeRole, true, statPersonnage);
		this.typeDePouvoir = this.init();
		
		
	}
	
	public boolean aCePouvoir(TypeDePouvoir typeDePouvoir) {
		return this.getTypeDePouvoir().contains(typeDePouvoir);
	}
	
	public void perdrePouvoir(TypeDePouvoir typeDePouvoir) {
		this.typeDePouvoir.remove(typeDePouvoir);
	}
	
	public abstract ArrayList<TypeDePouvoir> init() ;

	public List<TypeDePouvoir> getTypeDePouvoir() {
		return typeDePouvoir;
	}

}
