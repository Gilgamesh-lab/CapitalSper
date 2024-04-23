package org.apache.maven.archetypes.CapitalSpéLoupGarous;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Chasseur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Cupidon;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.MontreurDOurs;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Salvateur;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Sorcière;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Voyante;

public class Référentiel {
	private  Map<Integer ,Personnage > référentiel = new HashMap<>();

	public Référentiel() {
		this.build();
	}
	
	private void build() {
		this.référentiel.put(SimpleVillageois.IDROLE, new SimpleVillageois());// 21
		this.référentiel.put(LoupGarouSimple.IDROLE, new LoupGarouSimple());// 15
		this.référentiel.put(Cupidon.IDROLE, new Cupidon());// 3
		this.référentiel.put(Chasseur.IDROLE, new Chasseur());// 22
		this.référentiel.put(Sorcière.IDROLE, new Sorcière());// 19
		this.référentiel.put(Salvateur.IDROLE, new Salvateur());// 14
		this.référentiel.put(Voyante.IDROLE, new Voyante());// 4
		this.référentiel.put(MontreurDOurs.IDROLE, new MontreurDOurs());// 11
	}
	
	public Personnage conversionDeIdRoleVersPersonnage(int idRole) {
		Personnage personnage = référentiel.get(idRole);
		personnage.reset();
		return personnage;
	}
	
	public ArrayList<Personnage> conversionDeVillageVersListePersonnagesSeulementSpecial(Village village) {
		ArrayList<Personnage> personnages = new ArrayList<Personnage>();
		for(int i = 0; i < village.getNbPersonnageEnVie() ; i++) {
			if(village.getHabitantsEnVie().get(i).getIdDeRole() != LoupGarouSimple.IDROLE && village.getHabitantsEnVie().get(i).getIdDeRole() != SimpleVillageois.IDROLE ) {
				personnages.add(conversionDeIdRoleVersPersonnage(village.getHabitantsEnVie().get(i).getIdDeRole()));
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
		int nbSpecial = (int) (village.getNbVillageois() - village.getHabitantsEnVie().stream().filter(x -> x.aUnPouvoirSpecial()).count());
		String message = nbSpecial + " simple(s) villageois";
		for(int i = 0; i < village.getNbPersonnageEnVie() ; i++) {
			if(village.getPersonnage(i).aUnPouvoirSpecial()) {
				message += " , 1 " +  village.getPersonnage(i).getClass().getSimpleName();
			}
		}
		message += " et " + village.getNbLoupGarou() + " simple(s) loup-garous";
		if(village.aUnMaire()) {
			message += " présidé par un maire";
		}
		return message;
	}

}
