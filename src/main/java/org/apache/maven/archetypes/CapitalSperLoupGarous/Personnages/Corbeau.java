package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsCorbeau;

public class Corbeau extends VillageoisSpecial{
	public final static int IDROLE = 12 ;
	private static StatsCorbeau statsCorbeau = new StatsCorbeau();
	public static Personnage personnageCorbeauter;
	
	public Corbeau() {
		super(IDROLE, statsCorbeau);
		this.personnageCorbeauter = null;
	}
	
	@Override
	public void agir() {
		this.corbeauter(this.voter());
		this.resetListeDeVote();
		
	}
	
	public void corbeauter(int idPersonnage) {
		this.getVillage().getTableauDeVotes().put(idPersonnage, 2);
		personnageCorbeauter = this.getVillage().getPersonnageParId(idPersonnage);
		Logger.log("Le corbeau a corbeauter " + personnageCorbeauter + ".", TypeDeLog.role);
		
		this.getStatsCorbeau().corbeauter(personnageCorbeauter);
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

	public static StatsCorbeau getStatsCorbeau() {
		return statsCorbeau;
	}

	public static void setStatsCorbeau(StatsCorbeau statsCorbeau) {
		Corbeau.statsCorbeau = statsCorbeau;
	}
	
	
	
	

	
}
