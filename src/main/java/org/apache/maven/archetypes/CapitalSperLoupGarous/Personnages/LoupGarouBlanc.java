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
	
	public StatsLoupGarouBlanc getStatsLoupGarouBlanc() {
		return LoupGarouBlanc.statsLoupGarouBlanc;
	}
	
	public void tuerUnLoupGarou(LoupGarou lg) {
		this.tuer(lg);
		Logger.log("Le Loup-Garou Blanc s'est réveillé et a décidé de dévorer " + lg, TypeDeLog.role);
	}
	
	public LoupGarou getUnAutreLoupGarou() {
		ArrayList<LoupGarou> autreLoupGarou = (this.getMeute().getLoupGarouEnVie());
		autreLoupGarou.remove(this);
		int nbLg = (int) (Math.random() * ( autreLoupGarou.size() - 0 ));
		return autreLoupGarou.get(nbLg);
	}
	
	@Override
	public void agirPremiereNuit() {
		
	}
	
	@Override
	public void agir() {
		super.agir(); // Si pas de simple simple loup-garou la meute est quand même appelée
		if(this.peutSeReveiller && this.getMeute().getNbLgEnVie() > 1) {
			int nbVi = this.getVillage().getNbVillageois();
			int nbLg = this.getVillage().getNbLoupGarouEnVie();
			
			if(nbLg + 2 >= nbVi) {
				this.tuerUnLoupGarou(this.getUnAutreLoupGarou());
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
			return "le LoupGarouBlanc" + this.getId();
		}
		else {
			return "le LoupGarouBlanc";
		}
		
	}
	
	public void reset() {
		super.reset();
		this.peutSeReveiller = true;
	}
	
}