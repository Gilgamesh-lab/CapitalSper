package SpringBot;

import java.util.ArrayList;

public class Partie {
	private int nbSimpleVillageois;
	private int nbLoupGarou;
	private boolean aUnMaire;
	private ArrayList<Integer> listeIdRolePersonnageSpecial;
	private int nbPartie;
	
	public int getNbSimpleVillageois() {
		return nbSimpleVillageois;
	}
	public void setNbSimpleVillageois(int nbSimpleVillageois) {
		this.nbSimpleVillageois = nbSimpleVillageois;
	}
	public int getNbLoupGarou() {
		return nbLoupGarou;
	}
	public void setNbLoupGarou(int nbLoupGarou) {
		this.nbLoupGarou = nbLoupGarou;
	}
	public void setaUnMaire(boolean aUnMaire) {
		this.aUnMaire = aUnMaire;
	}
	public boolean isaUnMaire() {
		return aUnMaire;
	}
	public ArrayList<Integer> getListeIdRolePersonnageSpecial() {
		return listeIdRolePersonnageSpecial;
	}
	public void setListeIdRolePersonnageSpecial(ArrayList<Integer> listeIdRolePersonnageSpecial) {
		this.listeIdRolePersonnageSpecial = listeIdRolePersonnageSpecial;
	}
	public int getNbPartie() {
		return nbPartie;
	}
	public void setNbPartie(int nbPartie) {
		this.nbPartie = nbPartie;
	}
	
	
	
	
	
	

}
