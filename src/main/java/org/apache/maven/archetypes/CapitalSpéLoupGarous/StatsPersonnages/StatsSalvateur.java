package org.apache.maven.archetypes.CapitalSpéLoupGarous.StatsPersonnages;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Personnages.Personnage;

public class StatsSalvateur extends StatPersonnage {
	private float nbSalvation;
	private float nbProtectionReussie;
	private float nbVillageoisSalvater;
	private float nbInnocentIdentiferGraceSalvation;
	
	

	public StatsSalvateur() {
		this.nbSalvation = 0;
		this.nbProtectionReussie = 0;
		this.nbVillageoisSalvater = 0;
		this.nbInnocentIdentiferGraceSalvation = 0;
	}
	
	public void incrementerNbSalvation() {
		this.nbSalvation++;
	}

	public void incrementerNbProtectionReussie() {
		this.nbProtectionReussie++;
	}

	public void incrementerNbVillageoisSalvater(Personnage personnage) {
		if(personnage.estUnVillageois()) {
			this.nbVillageoisSalvater++;
		}
	}

	public void incrementerNbInnocentIdentiferGraceSalvation() {
		this.nbInnocentIdentiferGraceSalvation++;
	}

	public float getNbSalvation() {
		return nbSalvation;
	}


	public float getNbProtectionReussie() {
		return nbProtectionReussie;
	}



	public float getNbVillageoisSalvater() {
		return nbVillageoisSalvater;
	}



	public float getNbInnocentIdentiferGraceSalvation() {
		return nbInnocentIdentiferGraceSalvation;
	}



	@Override
	public String getStats() {
		String stats1 = "Le salvateur a effectué en moyenne " + ((double) ((this.nbSalvation / super.getNbPartie()))) + " salvations par partie" ;
		String stats2 = "Sur les " + (int) this.nbSalvation + " salvations, le salavateur a salvater un villageois dans " + ((double) ((this.nbVillageoisSalvater / this.nbSalvation) * 100 )) + "% des cas";
		String stats3 = "Sur les " + (int) this.nbSalvation + " salvations, dans " + ((double) ((this.nbProtectionReussie / this.nbSalvation) * 100 )) + "% des cas cela a sauvé une vie";
		String stats4 = "Sur les " + (int) this.nbProtectionReussie + " protections réussie, dans " + ((double) ((this.nbInnocentIdentiferGraceSalvation / this.nbProtectionReussie) * 100 )) + "% des cas cela a permit de connaitre un innocent";
		return stats1 + "\n" + stats2 + "\n" + stats3 + "\n" + stats4;
	}

}
