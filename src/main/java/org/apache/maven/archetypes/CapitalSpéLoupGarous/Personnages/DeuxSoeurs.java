package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.Arrays;

public class DeuxSoeurs extends VillageoisSpecial {
	public final static int IDROLE = 8;
	public int ordreDeNaissance;

	public DeuxSoeurs() {
		super(IDROLE);
		this.ordreDeNaissance = 1;

	}
	
	

	public int getOrdreDeNaissance() {
		return ordreDeNaissance;
	}



	public void setOrdreDeNaissance(int ordreDeNaissance) {
		this.ordreDeNaissance = ordreDeNaissance;
	}



	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Voyance));
	}
	
	public void trouverJumelle() {
		DeuxSoeurs jumelle =  this.getVillage().initDeuxSoeurs();
		this.ajouterAllié(jumelle);
		jumelle.ajouterAllié(this);
	}
	
	@Override
	public void agirPremiereNuit() {
		this.trouverJumelle();
	}
	
	@Override
	public String toString() {
		if(this.getVillage() != null && this.getVillage().getVillage().stream().filter(x->x.getIdDeRole() == this.getIdDeRole() && x != this ).count() > 1) {
			return "la soeurs" + this.getId();
		}
		else {
			return "la soeurs" + ordreDeNaissance ;
		}
		
	}

}
