package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

public class Statut {
	private Personnage personnage;
	private boolean estAmoureux;
	private Personnage amoureux;
	private boolean estEnVie;
	private boolean attaquerParLg;
	private Integer tueur;
	private boolean mortRecemment;
	private boolean protéger;

	public Statut(Personnage personnage) {
		this.estAmoureux = false;
		this.estEnVie = true;
		this.personnage = personnage;
		this.attaquerParLg = false;
		this.tueur = null;
		this.mortRecemment = false;
		this.protéger = false;
	}
	
	
	
	
	
	
	
	public boolean isProtéger() {
		return protéger;
	}

	public void setProtéger(boolean protéger) {
		this.protéger = protéger;
	}







	public boolean isMortRecemment() {
		return mortRecemment;
	}





	public void setMortRecemment(boolean mortRecemment) {
		this.mortRecemment = mortRecemment;
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
		if(this.estEnVie == false) {
			System.out.println("Erreur, statut vie est faux pour ce personnage : " +  this.personnage);
			this.estEnVie = true;
		}
		this.amoureux = personnage;
	}


	public Personnage getAmoureux() {
		return amoureux;
	}
	
	
	
	
	
	
	public boolean estAttaquerParLg() {
		return attaquerParLg;
	}

	public void setAttaquerParLg(boolean attaquerParLg) {
		this.attaquerParLg = attaquerParLg;
	}

	public boolean estEnVie() {
		return this.estEnVie;
	}
	
	public void EnVie() {
		this.estEnVie = true;
	}
	
	public void estMort() {
		this.estEnVie = false;
	}
	
	

}
