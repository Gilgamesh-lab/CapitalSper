package SpringBot;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.MeneurDeJeu;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.StatsMeute;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Statistiques.StatsVillage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@RestController
@RequestMapping("/api") // Préfixe commun pour les routes de ce contrôleur
public class Controller {

    // Route GET /api/hello
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot!";
    }

    
    @GetMapping("/getPersonnages")
    public String getPersonnages(@PathVariable String id) {
        return "User ID: " + id;
    }

    @GetMapping("/lancerUnePartie")
    public String createUser(@RequestBody Partie partie) {
    	
    	
    	
    	Village village = new Village(partie.getNbSimpleVillageois(),partie.getNbLoupGarou());
    	if(village.getStatsVillage().getNbVote() != 0) {
    		village.setStatsVillage(new StatsVillage());
    	}
    	
    	if(village.getMeute().getStatsMeute().getNbVote() != 0) {
    		village.getMeute().setStatsMeute(new StatsMeute());
    	}
    	
		Logger logger = new Logger();
		logger.setModeSpectateurOn();
		logger.setSauvegardePartie(true);
		MeneurDeJeu meneurDeJeu = new MeneurDeJeu(village, logger);
		meneurDeJeu.lancerDesParties(1);
		
		
        return logger.getLogPartie();
    }
}
