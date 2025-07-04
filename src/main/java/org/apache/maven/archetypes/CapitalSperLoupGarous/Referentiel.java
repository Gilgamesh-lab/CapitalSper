package org.apache.maven.archetypes.CapitalSperLoupGarous;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Chasseur;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Corbeau;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Cupidon;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.DeuxSoeurs;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.LoupGarouSimple;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Maire;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.MontreurDOurs;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Salvateur;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.SimpleVillageois;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Sorciere;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Voleur;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages.Voyante;

public class Referentiel {
	static Map<Integer, Class<? extends Personnage>> classesParRole = new HashMap<>();

	public Referentiel() {
		this.build();
	}
	
	private static void build() {
		classesParRole.put(SimpleVillageois.IDROLE, SimpleVillageois.class);// 21
		classesParRole.put(LoupGarouSimple.IDROLE, LoupGarouSimple.class);// 15
		classesParRole.put(Cupidon.IDROLE, Cupidon.class);// 3
		classesParRole.put(Chasseur.IDROLE, Chasseur.class);// 22
		classesParRole.put(Sorciere.IDROLE, Sorciere.class);// 19
		classesParRole.put(Salvateur.IDROLE, Salvateur.class);// 14
		classesParRole.put(Voyante.IDROLE, Voyante.class);// 4
		classesParRole.put(MontreurDOurs.IDROLE, MontreurDOurs.class);// 11
		classesParRole.put(Corbeau.IDROLE, Corbeau.class);// 12
		classesParRole.put(DeuxSoeurs.IDROLE, DeuxSoeurs.class);// 8
		classesParRole.put(Voleur.IDROLE, Voleur.class);// 1
	}
	
	public static Personnage conversionDeIdRoleVersPersonnage(int idRole) {
		if(classesParRole.isEmpty()) {
			build();
		}
		Class<? extends Personnage> classePersonnage = classesParRole.get(idRole);
		  if (classePersonnage != null) {
		    try {
				return classePersonnage.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		   
		  } else {
			  throw new RuntimeException("Impossible de créer une instance de la classe de personnage pour l'ID de rôle : " + idRole);
		  }
		  
	}
	
	
	
	public static List<Integer> getIdPersonnageDisponible() {
		if(classesParRole.isEmpty()) {
			build();
		}
		return classesParRole.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
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
		int nbSpecial = (int) (village.getNbPersonnageAvantPartie() - village.getHabitantsEnVie().stream().filter(x -> x.aUnPouvoirSpecial()).count());
		String message = nbSpecial + " simple(s) villageois";
		for(int i = 0; i < village.getNbPersonnageEnVie() ; i++) {
			if(village.getPersonnage(i).aUnPouvoirSpecial() && village.getPersonnage(i).getIdDeRole() != LoupGarouSimple.IDROLE) {
				if(village.getPersonnage(i).getClass() == DeuxSoeurs.class) {
					message += " , 2 soeurs";
				}
				else {
					message += " , 1 " +  village.getPersonnage(i).getClass().getSimpleName();
				}
				
			}
		}
		message += " et " + village.getNbLoupGarou() + " simple(s) loup-garous";
		if(village.aUnMaire()) {
			message += " présidé par un maire";
		}
		return message;
	}

}
