package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;

public class Référentiel {
	private  Map<Integer ,Personnage > référentiel = new HashMap<>();

	public Référentiel() {
		this.build();
	}
	
	private void build() {
		this.référentiel.put(0, new SimpleVillageois());
		this.référentiel.put(1, new LoupGarouSimple());
		this.référentiel.put(4, new Cupidon());
		this.référentiel.put(5, new Chasseur());
	}
	
	public Personnage conversionDeIdRoleVersPersonnage(int idRole) {
		Personnage personnage = référentiel.get(idRole);
		personnage.reset();
		return personnage;
	}
	
	public ArrayList<Personnage> conversionDeVillageVersListePersonnagesSeulementSpecial(Village village) {
		ArrayList<Personnage> personnages = new ArrayList<Personnage>();
		for(int i = 0; i < village.getNbPersonnage() ; i++) {
			if(village.getHabitants().get(i).getIdDeRole() > 1) {
				personnages.add(conversionDeIdRoleVersPersonnage(village.getHabitants().get(i).getIdDeRole()));
			}
		}
		return personnages;
	}
	
	public ArrayList<Personnage> conversionDeListeIdVersListePersonnages(ArrayList<Integer> ids) {
		ArrayList<Personnage> personnages = new ArrayList<Personnage>();
		for(int i = 0; i < ids.size() ; i++) {
			personnages.add(conversionDeIdRoleVersPersonnage(ids.get(i)));
		}
		return personnages;
	}
	
	public String messageDebutPartie(Village village) {
		int nbSpecial = (int) (village.getNbVillageois() - village.getHabitants().stream().filter(x -> x.aUnPouvoirSpecial()).count());
		String message = nbSpecial + " villageois";
		for(int i = 0; i < village.getNbPersonnage() ; i++) {
			if(village.getPersonnage(i).aUnPouvoirSpecial()) {
				message += " , 1 " +  village.getPersonnage(i).toString() ;
			}
		}
		message += " et " + village.getNbLoupGarou() + " loup-garous";
		return message;
	}

}
