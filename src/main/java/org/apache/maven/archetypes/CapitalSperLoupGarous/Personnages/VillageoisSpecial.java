package org.apache.maven.archetypes.CapitalSperLoupGarous.Personnages;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Statistiques.Statistiques;

public abstract class VillageoisSpecial extends Villageois {
	private ArrayList<TypeDePouvoir> typeDePouvoir;
	
	protected VillageoisSpecial(int idDeRole) {
		super(idDeRole, true);
		this.typeDePouvoir = this.init();
		
	}
	
	protected VillageoisSpecial(int idDeRole, Statistiques statPersonnage) {
		super(idDeRole, true, statPersonnage);
		this.typeDePouvoir = this.init();
		
		
	}
	
	public boolean aCePouvoir(TypeDePouvoir typeDePouvoir) {
		return this.getTypeDePouvoir().contains(typeDePouvoir);
	}
	
	public void perdrePouvoir(TypeDePouvoir typeDePouvoir) {
		this.typeDePouvoir.remove(typeDePouvoir);
	}
	
	public abstract ArrayList<TypeDePouvoir> init() ;

	public List<TypeDePouvoir> getTypeDePouvoir() {
		return typeDePouvoir;
	}
	
	
	public Personnage voteCibleAction() {// voyante
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
	

}
