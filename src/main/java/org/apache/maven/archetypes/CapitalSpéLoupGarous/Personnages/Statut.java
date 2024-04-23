package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;

public class Statut {
	private Personnage personnage;
	private boolean estAmoureux;
	private Personnage amoureux;
	private boolean estEnVie;
	private boolean aEteAttaquerParLaMeute;
	private Integer tueur;
	private boolean protéger;

	public Statut(Personnage personnage) {
		this.estAmoureux = false;
		this.estEnVie = true;
		this.personnage = personnage;
		this.aEteAttaquerParLaMeute = false;
		this.tueur = null;
		this.protéger = false;
	}
	
	
	public boolean isProtéger() {
		return protéger;
	}

	public void setProtéger(boolean protéger) {
		this.protéger = protéger;
	}

	public Integer getTueur() {
		return tueur;
	}



	public void setTueur(Integer tueur) {
		this.tueur = tueur;
	}



	public void setAmoureux(Personnage personnage) {
		this.estAmoureux = true;
		personnage.getStatut().setOnAmoureux(this.personnage);
		this.amoureux = personnage;
	}

	public boolean isEstAmoureux() {
		return estAmoureux;
	}
	
	public void setOnAmoureux(Personnage personnage) {
		this.estAmoureux = true;
		this.amoureux = personnage;
	}


	public Personnage getAmoureux() {
		return amoureux;
	}
	
	public boolean aEteAttaquerParLaMeute() {
		return aEteAttaquerParLaMeute;
	}

	public void setAEteAttaqueParLaMeute(boolean attaquerParLg) {
		this.aEteAttaquerParLaMeute = attaquerParLg;
	}

	public boolean estEnVie() {
		return this.estEnVie;
	}
	
	public void estMort() {
		this.estEnVie = false;
	}
	
	

}
