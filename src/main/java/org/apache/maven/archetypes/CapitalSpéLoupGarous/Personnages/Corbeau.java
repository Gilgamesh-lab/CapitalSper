package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;


import java.util.ArrayList;
import java.util.Arrays;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;

public class Corbeau extends VillageoisSpecial{
	public final static int IDROLE = 12 ;
	
	public Corbeau() {
		super(IDROLE);
	}
	
	@Override
	public void agir() {
		this.corbeauter(this.voter());
		
	}
	
	public void corbeauter(int idPersonnage) {
		this.getVillage().getTableauDeVotes().put(idPersonnage, 2);
		Logger.log("Le corbeau a corbeauter " + this.getVillage().getPersonnageParId(idPersonnage), TypeDeLog.role);
	}

	@Override
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Mort));
	}
	
	@Override
	public String toString() {
		if(this.getVillage() != null && this.getVillage().getVillage().stream().anyMatch(x->x.getIdDeRole() == this.getIdDeRole() && x != this)) {
			return "le corbeau" + this.getId();
		}
		else {
			return "le corbeau";
		}
		
	}
	
	

	
}
