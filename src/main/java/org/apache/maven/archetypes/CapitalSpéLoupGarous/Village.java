package org.apache.maven.archetypes.CapitalSpéLoupGarous;

import java.util.ArrayList;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarou;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Meute;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois;

public  class Village {

	private ArrayList<Personnage> village;
	private ArrayList<Villageois> villageois;
	private int nbLoupGarou = 0;
	private static final SimpleVillageois simpleVillageois = new SimpleVillageois();
	private static final LoupGarouSimple loupGarou = new LoupGarouSimple();
	private  Meute meute ;
	
	public Village(int nbVillageois, int nbLoupGarous) {
		this.village = new ArrayList<Personnage>();
		this.villageois = new ArrayList<Villageois>();
		this.meute = new Meute();
		this.meute.setVillage(this);
		
		for(int i = 0 ; i < nbVillageois ; i++) {
			this.ajouterPersonnage(simpleVillageois);
		}
		for(int i = 0 ; i < nbLoupGarous ; i++) {
			this.ajouterPersonnage(loupGarou);
		}
	}
	
	public Village() {
		this.village = new ArrayList<Personnage>();
		this.villageois = new ArrayList<Villageois>();
		
		this.meute = new Meute();
		this.meute.setVillage(this);
	}
	
	public void ajouterPersonnage(Personnage personnage) {
		if(personnage.estUnVillageois()) {
			this.villageois.add((Villageois) personnage);
		}
		else {
			this.nbLoupGarou++;
			this.meute.ajouterLoup((LoupGarou) personnage);
		}
		this.village.add(personnage);
		personnage.setId(this.village.size() - 1);
	}
	
	
	public int getNbPersonnage() {
		return this.village.size() ;
	}
	
	public ArrayList<Villageois> getVillageois() {
		return this.villageois ;
	}
	
	public ArrayList<Personnage> getVillage() {
		return this.village ;
	}
	
	public Meute getMeute() {
		return this.meute ;
	}
	
	public int getNbVillageois() {
		return this.getNbPersonnage() - this.getNbLoupGarou() ;
	}
	
	public int getNbLoupGarou() {
		return this.nbLoupGarou ;
	}
	
	
	
	public Personnage getPersonnage(int nb) {
		return this.village.get(nb);
	}
	
	public void voter() {
		int nb = (int) (Math.random() * ( (this.getNbPersonnage() ) - 0 ));
		if(!this.village.get(nb).estUnVillageois()) {
			this.nbLoupGarou--;
			System.out.println("Un loup-garou a été tué lors du vote");
		}
		else {
			this.villageois.remove(this.village.get(nb));
			System.out.println("Un villageois a été tué lors du vote");
		}
		this.village.remove(nb);
	}
	
}
