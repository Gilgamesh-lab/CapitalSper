package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class VillageoisSpecial extends Villageois {
	private ArrayList<TypeDePouvoir> typeDePouvoir;
	
	public VillageoisSpecial(int idDeRole) {
		super(idDeRole, true);
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
	
	public Personnage voteCibleAction() {
		for(int i = 0; i < this.getVillage().getNbPersonnageEnVie(); i++) {
			if(!this.getAlliés().contains(this.getVillage().getHabitantsEnVie().get(i)) && !this.getEnnemies().contains(this.getVillage().getHabitantsEnVie().get(i))){
				this.getListeDeVote().add(this.getVillage().getHabitantsEnVie().get(i));
			}
			
		}
		
		int nb ;
		if(this.getListeDeVote().size() == 0) {
			if(this.estAmoureux()) {
				this.setListeDeVote(new ArrayList<Personnage>(this.getVillage().getHabitantsEnVie().stream().filter(x->this.getAmoureux() != x).collect(Collectors.toList())));
				nb = (int) (Math.random() * ( this.getListeDeVote().size()    - 0 ));
				Personnage cibleAction = this.getListeDeVote().get(nb);
				this.getListeDeVote().clear();
				return cibleAction;	
			}
			else {
				nb = (int) (Math.random() * ( this.getVillage().getNbPersonnageEnVie()   - 0 ));
				return this.getVillage().getPersonnage(nb);	
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
