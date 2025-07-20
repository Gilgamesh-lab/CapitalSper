package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages;


import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.TypeDeLog;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.TypeDePouvoir;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsCorbeau;

public class Corbeau extends VillageoisSpecial{
	public final static int IDROLE = 12 ;
	private static StatsCorbeau statsCorbeau = new StatsCorbeau();
	public static Personnage personnageCorbeauter;
	
	public Corbeau() {
		super(IDROLE, statsCorbeau);
		personnageCorbeauter = null;
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
		
		getStatsCorbeau().corbeauter(personnageCorbeauter);
	}
	

	@Override
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Vote));
	}
	
	@Override
	public String toString() {
		String nom;
		if(this.getVillage() != null && this.getVillage().getVillage().stream().anyMatch(x->x.getIdDeRole() == this.getIdDeRole() && x != this)) {
			nom = "le corbeau" + this.getId();
		}
		else {
			nom = "le corbeau";
		}
		if(this.getStatut().isInfecter()) {
			nom += "(infecté)";
		}
		return nom;
	}

	public static StatsCorbeau getStatsCorbeau() {
		return statsCorbeau;
	}

	public static void setStatsCorbeau(StatsCorbeau statsCorbeau) {
		Corbeau.statsCorbeau = statsCorbeau;
	}
	
	
	
	

	
}
