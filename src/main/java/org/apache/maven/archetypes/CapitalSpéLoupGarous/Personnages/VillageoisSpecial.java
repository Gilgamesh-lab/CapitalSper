package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;


import java.util.List;

public abstract class VillageoisSpecial extends Villageois {
	private List<TypeDePouvoir> typeDePouvoir;
	
	public VillageoisSpecial(int idDeRole, List<TypeDePouvoir> typeDePouvoir) {
		super(idDeRole, true);
		this.typeDePouvoir = typeDePouvoir;
		
	}
	
	public  abstract void agir();

	public List<TypeDePouvoir> getTypeDePouvoir() {
		return typeDePouvoir;
	}
	

}
