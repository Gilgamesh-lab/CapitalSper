package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarous;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.TypeDePouvoir;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.Statistiques;

public class InfectPereDesLoups extends LoupGarouSpecial {
	public final static int IDROLE = 17;
	public boolean aInfection;
	
	public InfectPereDesLoups() {
		super(IDROLE);
		this.aInfection = true;
	}
	
	public InfectPereDesLoups(Statistiques statPersonnage) {
		super(IDROLE, statPersonnage);
		this.aInfection = true;
		
	}

	@Override
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Mort,TypeDePouvoir.Vie));
	}
	
	@Override
	public void agir() {
		int pileOuFace = (int) (Math.random() * ( 1 - 0 ));
		if(pileOuFace == 0 && this.getVillage().getHabitantsEnVie().stream().anyMatch(x->x.getStatut().aEteAttaquerParLaMeute()) && this.aInfection) {
			this.infecter();
			this.aInfection = false;
		}
	}
	
	public void infecter() {
		Personnage persoDevorer = this.getVillage().getVillageois().stream().filter(x->x.getStatut().aEteAttaquerParLaMeute()).findAny().get();
		System.out.println(persoDevorer + " a été infecté et est devenue un loup garou");
		new Infecter(persoDevorer, this.getVillage());
	}
	
	@Override
	public String toString() {
		if(this.getVillage() != null && this.getVillage().getVillage().stream().anyMatch(x->x.getIdDeRole() == this.getIdDeRole() && x != this)) {
			return "l'infect père des loups garous" + this.getId();
		}
		else {
			return "l'infect père des loups garous";
		}
		
	}
	
	@Override
	public void agirAprèsNuit() {
		if(!this.aInfection && this.aCePouvoir(TypeDePouvoir.Vie)) {// pour le salvateur
			this.perdrePouvoir(TypeDePouvoir.Vie);
		}
		
	}
	
	@Override
	public void reset() {
		super.reset();
		this.aInfection = true;
	}

}
