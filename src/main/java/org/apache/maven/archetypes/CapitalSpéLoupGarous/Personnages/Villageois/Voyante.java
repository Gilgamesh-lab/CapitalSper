package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Villageois;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.TypeDeLog;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.TypeDePouvoir;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.LoupGarous.LoupGarou;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.StatsVoyante;

public class Voyante extends VillageoisSpecial {
	public final static int IDROLE = 4;
	private static StatsVoyante statsVoyante = new StatsVoyante();
	private Personnage dernierPersonnageSonder;

	public Voyante() {
		super(IDROLE, statsVoyante);
	}
	
	public Voyante(LoupGarou infecter) {
		super(IDROLE, infecter);
	}
	
	public ArrayList<TypeDePouvoir> init() {
		return new ArrayList<>(Arrays.asList(TypeDePouvoir.Voyance));
	}
	
	@Override
	public int voter() {
		int persoVoter = super.voter();
		statsVoyante.voter(this.getVillage().getPersonnageParId(persoVoter));
		return persoVoter;
	}
	
	/**
	 * Cette fonction est une fonction de vote qui cible ne cible pas soi même, les alliés et les ennemis.
	 * @return Le personnage a voté
	 */
	public Personnage voteCibleAction() {// voyante( 
		for(int i = 0; i < this.getVillage().getAutreHabitantsEnVie(this).size(); i++) {
			if(!this.getAlliés().contains(this.getVillage().getAutreHabitantsEnVie(this).get(i)) && !this.getEnnemies().contains(this.getVillage().getAutreHabitantsEnVie(this).get(i))){
				this.getListeDeVote().add(this.getVillage().getAutreHabitantsEnVie(this).get(i));
			}
			
		}
		
		int nb ;
		if(this.getListeDeVote().size() == 0) {
			if(this.estAmoureux()) {
				this.setListeDeVote(new ArrayList<Personnage>(this.getVillage().getAutreHabitantsEnVie(this).stream().filter(x->this.getAmoureux() != x).collect(Collectors.toList())));
				nb = (int) (Math.random() * ( this.getListeDeVote().size()    - 0 ));
				Personnage cibleAction = this.getListeDeVote().get(nb);
				this.getListeDeVote().clear();
				return cibleAction;	
			}
			else {
				nb = (int) (Math.random() * ( this.getVillage().getAutreHabitantsEnVie(this).size()   - 0 ));
				return this.getVillage().getAutreHabitantsEnVie(this).get(nb);	
			}
		}
		else {
			nb = (int) (Math.random() * ( this.getListeDeVote().size()    - 0 ));
			Personnage cibleAction = this.getListeDeVote().get(nb);
			this.getListeDeVote().clear();
			return cibleAction;	
		}
			
	}
	
	public void sonder() {
		dernierPersonnageSonder = this.voteCibleAction();
		 statsVoyante.voyance(dernierPersonnageSonder);
		 if(dernierPersonnageSonder.equals(this)) {
				System.out.println("erreur vovo détecté : " + this + "s'est choisi elle-même");
			}
		 if(dernierPersonnageSonder.estUnVillageois()) {
			 Logger.log(this + " a décidé de sonder " + dernierPersonnageSonder + " qui s'est révélé être innocent", TypeDeLog.role);
			 //this.ajouterAlliés(dernierPersonnageSonder.getAlliés());
			 this.ajouterAllié(dernierPersonnageSonder);
			 
		 }
		 else {
			 Logger.log(this + " a décidé de sonder " + dernierPersonnageSonder + " qui s'est révélé être un ennemie du village", TypeDeLog.role);
			 this.ajouterEnnemie(dernierPersonnageSonder);
			 
		 }
	}
	
	@Override
	public void agir() {
		this.sonder();
		
	}
	
	public static void setStatsVoyante(StatsVoyante statsVoyante) {
		Voyante.statsVoyante = statsVoyante;
	}

	public StatsVoyante getStatsVoyante() {
		return Voyante.statsVoyante;
	}
	
	/*@Override
	public void meurt() {// Lors de sa mort les villageois se rendent compte qu'il peuvent avoir confiance aux dire de la voyante
		super.meurt();// dans cet ordre pour ne pas prendre en compte l'amoureux
		if(this.getStatut().getTueur() == LoupGarouSimple.IDROLE) {// la voyante n'a pas u le temps de parler au village de sa dernière divination car elle est morte la nuit
			//System.out.println(dernierPersonnageSonder + " ," + this.getAlliés().size() + " , " + this.getEnnemies().size()+ " , " + Référentiel.conversionDeIdRoleVersPersonnage(this.getStatut().getTueur()));
			if(dernierPersonnageSonder.estUnVillageois()) {
				this.getAlliés().remove(dernierPersonnageSonder);
			 }
			 else {
				 this.getEnnemies().remove(dernierPersonnageSonder);
			 }
			
		}
		this.getVillage().getVillageois().stream().forEach(x-> {x.ajouterAlliés(this.getAlliés()); x.ajouterEnnemies(this.getEnnemies());});	
	}*/

	@Override
	public String toString() {
		String nom;
		if(this.getVillage() != null && this.getVillage().getVillage().stream().anyMatch(x->x.getIdDeRole() == this.getIdDeRole() && x != this)) {
			nom = "la voyante" + this.getId();
		}
		else {
			nom = "la voyante";
		}
		if(this.getStatut().isInfecter()) {
			nom += "(infecté)";
		}
		return nom;
	}
	

}
