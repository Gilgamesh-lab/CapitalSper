package org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class VillageoisSpecial extends Villageois {
	private List<TypeDePouvoir> typeDePouvoir;
	
	public VillageoisSpecial(int idDeRole, List<TypeDePouvoir> typeDePouvoir) {
		super(idDeRole, true);
		this.typeDePouvoir = typeDePouvoir;
		
	}
	
	public  abstract void agir();

	public List<TypeDePouvoir> getTypeDePouvoir() {
		return typeDePouvoir;
	}
	
	public Personnage voteCibleAction() {
		this.getListeDeVote().clear();
		for(int i = 0; i < this.getVillage().getNbPersonnage(); i++) {
			this.getListeDeVote().add(this.getVillage().getHabitants().get(i));
		}
		for(int i = 0 ; i < this.getAlliés().size() ; i++) {
			this.getListeDeVote().remove(this.getAlliés().get(i));
		}
		int nb ;
		if(this.getListeDeVote().size() == 0) {
			if(this.estAmoureux()) {
				this.setListeDeVote(new ArrayList<Personnage>(this.getVillage().getHabitants().stream().filter(x->this.getAmoureux() != x).collect(Collectors.toList())));
				nb = (int) (Math.random() * ( this.getListeDeVote().size()    - 0 ));
				return this.getListeDeVote().get(nb);	
			}
			else {
				nb = (int) (Math.random() * ( this.getVillage().getNbPersonnage()   - 0 ));
				return this.getVillage().getPersonnage(nb);	
			}
		}
		else {
			nb = (int) (Math.random() * ( this.getListeDeVote().size()    - 0 ));
			return this.getListeDeVote().get(nb);
		}
			
	}
	

}
