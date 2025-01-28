# CapitalSperLoupGarou

Le but du projet CapitalSperLoupGarou est de permettre d'estimer le poid des cartes et d'une composition afin de savoir si une partie de loup garou de Thiercelieux est équilibré.

Pour cela, 4 action principales sont attendues :
	- Simulation (Simule un certain nombre de partie avec une composition donnée)
	- Exploration (Explore toutes les possibilités d’une partie avec une composition donnée)
	- Estimation (Estime le coût d’un personnage en s’appuyant sur Exploration)
	- Composition (Compose une partie équilibré en s’appuyant sur Estimation)
	
Une interface web pourra être mis en place afin d'exécuter ces actions de manière user friendly.
	
	
Action principales implémentés :
	- Simulation avec la fonction MeneurDeJeu.lancerDesParties(int nb).
	- Exploration avec la fonction MeneurDeJeu.exploration() mais ne supporte que des compositions composés uniquement de simple-villageois et de simple loup garou.
