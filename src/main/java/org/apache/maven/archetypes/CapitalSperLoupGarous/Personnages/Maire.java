package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.StatsMaire;

public class Maire extends Fonction {
	private static StatsMaire statsMaire = new StatsMaire(); 
	public final static int IDROLE = 97;

	public Maire() {
		super(true);
	}
	
	public void entrerEnFonction(Personnage personnage) {
		if(this.getPersonnage() != null) {
			Logger.log(this.getPersonnage() + " a choisi " + personnage + " pour lui succéder en tant que maire du village");
		}
		
		super.setPersonnage(personnage);
		personnage.setFonction(this);
		personnage.incrementerNbVote(1);
		this.getStatsMaire().nouveauMaire(personnage);
		
	}
	
	public void election() {
		int nb;
		if(this.getPersonnage() != null) { // choisi par l'ancien maire
			this.getPersonnage().setAlliés(new ArrayList<Personnage>(this.getPersonnage().getAlliés().stream().filter(x->x.estEnvie()).collect(Collectors.toList())));//maj alliés mort et vivant
			if(this.getPersonnage().getAlliés().size() > 0 && this.getPersonnage().estUnVillageois()) {// choisi de manière aléatoire
				nb = (int) (Math.random() * ( this.getPersonnage().getAlliés().size()    - 0 ));
				entrerEnFonction(this.getPersonnage().getAlliés().get(nb));
			}
			else {//si un ancien maire villageois à au moins un alliés, il est choisi par ses alliées 
				nb = this.getPersonnage().elire();
				entrerEnFonction(this.getVillage().getPersonnageParId(nb));
			}
		}
		else {// 1er maire
			entrerEnFonction(this.getVillage().election());
		}
	}

	public static StatsMaire getStatsMaire() {
		return statsMaire;
	}

	public static void setStatsMaire(StatsMaire statsMaire) {
		Maire.statsMaire = statsMaire;
	}
	
	

}
