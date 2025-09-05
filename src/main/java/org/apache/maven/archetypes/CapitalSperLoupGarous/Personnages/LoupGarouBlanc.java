package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsLoupGarouBlanc;

public class LoupGarouBlanc extends LoupGarouSpecial {
	public final static int IDROLE = 16;
	private static StatsLoupGarouBlanc statsLoupGarouBlanc = new StatsLoupGarouBlanc();
	private boolean peutSeReveiller = true;
	
	public boolean isPeutSeReveiller() {
		return peutSeReveiller;
	}

	public void setPeutSeReveiller(boolean peutSeReveiller) {
		this.peutSeReveiller = peutSeReveiller;
	}

	public LoupGarouBlanc() {
		super(IDROLE, statsLoupGarouBlanc);
	}
	
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Mort));
	}
	
	public static void setStatsLoupGarouBlanc(StatsLoupGarouBlanc statsLoupGarouBlanc) {
		LoupGarouBlanc.statsLoupGarouBlanc = statsLoupGarouBlanc;
	}
	
	public static StatsLoupGarouBlanc getStatsLoupGarouBlanc() {
		return LoupGarouBlanc.statsLoupGarouBlanc;
	}
	
	public void tuerUnLoupGarou(LoupGarou lg) {
		Logger.log("Le Loup-Garou Blanc s'est réveillé et a décidé de dévorer " + lg, TypeDeLog.role);
		lg.getStatut().setTuerParLeLoupGarouBlanc(true);
		this.statsLoupGarouBlanc.incrementerNbLgTUer();
	}
	
	public LoupGarou getUnAutreLoupGarou() {
		ArrayList<LoupGarou> autreLoupGarou = this.getMeute().getLoupGarouEnVie();
		autreLoupGarou.remove(this);
		if(this.estAmoureux() && !this.getAmoureux().estUnVillageois()) {
			autreLoupGarou.remove(this.getAmoureux());
		}
		int nbLg = (int) (Math.random() * ( autreLoupGarou.size() - 0 ));
		return autreLoupGarou.get(nbLg);
	}
	
	public boolean pasDeVictimePossible() {
		ArrayList<LoupGarou> autreLoupGarou = this.getMeute().getLoupGarouEnVie();
		autreLoupGarou.remove(this);
		if(this.estAmoureux() && !this.getAmoureux().estUnVillageois()) {
			autreLoupGarou.remove(this.getAmoureux());
		}
		return autreLoupGarou.size() == 0;
	}
	
	@Override
	public void agirPremiereNuit() {
		
	}
	
	@Override
	public void agir() {
		super.agir(); // Si pas de simple simple loup-garou la meute est quand même appelée
		if(this.peutSeReveiller && this.getMeute().getNbLgEnVie() > 1) {
			this.statsLoupGarouBlanc.incrementerNbFoisReveiller();
			if(this.getVillage().getNbLoupGarouEnVie() + 2 >= this.getVillage().getNbVillageoisEnVie() && !this.pasDeVictimePossible()) {
				this.tuerUnLoupGarou(this.getUnAutreLoupGarou());
			}
			else {
				Logger.log("Le Loup-Garou Blanc s'est réveillé et a décidé de ne pas tuer un autre loup-garou", TypeDeLog.role);
			}
		}
		this.peutSeReveiller = !this.peutSeReveiller;
	}
	
	@Override
	public void agirAprèsNuit() {
		
	}
	
	
	@Override
	public String toString() {
		if(this.getVillage() != null && this.getVillage().getVillage().stream().anyMatch(x->x.getIdDeRole() == this.getIdDeRole() && x != this)) {
			return "le Loup-garou blanc" + this.getId();
		}
		else {
			return "le Loup-garou blanc";
		}
		
	}
	
	public void reset() {
		super.reset();
		this.peutSeReveiller = true;
	}
	
}