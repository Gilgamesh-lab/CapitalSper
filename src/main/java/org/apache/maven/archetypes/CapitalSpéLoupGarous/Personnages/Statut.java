package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

public class Statut {
	private Personnage personnage;
	private boolean estAmoureux;
	private Personnage amoureux;
	private boolean estEnVie;

	public Statut(Personnage personnage) {
		this.estAmoureux = false;
		this.estEnVie = true;
		this.personnage = personnage;
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
