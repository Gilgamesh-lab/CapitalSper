package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarous;


import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.TypeDePouvoir;

public class Infecter extends LoupGarouSpecial {
	public final static int IDROLE = 99;
	private Personnage persoInfecter;

	public Infecter(Personnage persoInfecter, Village village) {
		super(IDROLE);
		this.persoInfecter = persoInfecter;
		
		village.ajouterPersonnage(this);
		this.tuer(persoInfecter);
		persoInfecter.infecter(this);
		if(this.persoInfecter.estAmoureux()) {
			this.tomberAmoureux(persoInfecter.getStatut().getAmoureux());
		}
		persoInfecter.ajouterAllié(this);
		
		//this.getVillage().getVillage().remove(persoInfecter);
	}
	
	@Override
	public int getId() {
		//System.out.println("id = " + super.getId());
		return persoInfecter.getId();
	}
	
	@Override
	public int getIdDeRole() {
		//System.out.println("id = " + super.getId());
		return persoInfecter.getIdDeRole();
	}

	public Personnage getPersoInfecter() {
		return persoInfecter;
	}

	public void setPersoInfecter(Personnage persoInfecter) {
		this.persoInfecter = persoInfecter;
	}

	@Override
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Ambigu, TypeDePouvoir.Manipulation, TypeDePouvoir.Mort));
	}
	
	public void agirPremiereNuit() {
		persoInfecter.agirPremiereNuit();
	}

	public void agir() {
		persoInfecter.agir();
	}

	public void agirAprèsNuit() {
		persoInfecter.agirAprèsNuit();
	}
	
	@Override
	public String toString() {
		return persoInfecter.toString();
		
	}

}
