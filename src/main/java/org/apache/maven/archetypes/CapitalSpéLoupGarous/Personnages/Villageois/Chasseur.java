package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.TypeDePouvoir;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.StatsChasseur;

public class Chasseur extends VillageoisSpecial {
	public final static int IDROLE = 22 ;
	private static StatsChasseur statsChasseur = new StatsChasseur();

	public Chasseur() {
		super(IDROLE, statsChasseur);
	}
	
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Mort));
	}
	
	@Override
	public void meurt() {
		super.meurt();
		if(this.getVillage().getNbLoupGarou() > 0) {
			Personnage cible = this.getVillage().getPersonnageParId(this.voter());
			statsChasseur.tirer(cible);
			this.resetListeDeVote();
			Logger.log("Dans son dernier souffle " + this + " a décidé d'emporter avec lui " + cible);
			this.tuer(cible);
			this.perdrePouvoir(TypeDePouvoir.Mort);
		}
	}
	
	public static void setStatsChasseur(StatsChasseur statsChasseur) {
		Chasseur.statsChasseur = statsChasseur;
	}

	public StatsChasseur getStatsChasseur() {
		return Chasseur.statsChasseur;
	}

	@Override
	public String toString() {
		if(this.getVillage() != null && this.getVillage().getVillage().stream().anyMatch(x->x.getIdDeRole() == this.getIdDeRole() && x != this)) {
			return "le chasseur" + this.getId();
		}
		else {
			return "le chasseur";
		}
		
	}

}
