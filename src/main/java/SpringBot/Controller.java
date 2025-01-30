package SpringBot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.archetypes.CapitalSpéLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.MeneurDeJeu;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Referentiel;
import org.apache.maven.archetypes.CapitalSpéLoupGarous.Village;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Préfixe commun pour les routes de ce contrôleur
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class Controller {

    // Route GET /api/hello
	
	
	@GetMapping("/ping")
    public ResponseEntity ping() {
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
	
	
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot!";
    }

    
    @GetMapping("/getPersonnages")
    public List<Integer> getPersonnages() {
    	List<Integer> liste = Referentiel.getIdPersonnageDisponible();
    	liste.add(98); // maire
        return liste;
    }

    @PostMapping("/lancerUnePartie")
    public ResponseEntity  lancerUnePartie(@RequestBody Partie partie) {
    	Village village = new Village(partie.getNbSimpleVillageois(),partie.getNbLoupGarou());
    	village.initRequete();
    	village.setMaire(partie.isaUnMaire());
    	
    	if(partie.getListeIdRolePersonnageSpecial() != null ) {
    		List<Integer> liste = Referentiel.getIdPersonnageDisponible();
    		for(int i : partie.getListeIdRolePersonnageSpecial()) {
        		if(liste.contains(i)) {
        			village.ajouterPersonnage(i);
        		}
        		else {
        			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : l'id " + i + " est introuvable");
        		}
        	}
    	}
    	
    	Logger logger = new Logger();
    	logger.setSauvegardePartie(true);
    	
    	if(partie.getNbPartie() > 1) {
    		logger.setModeStatistiqueOn();
    	}
    	else {
    		logger.setModeSpectateurOn();
    		logger.setOffAfficherLogStats();
    	}
    	
    	MeneurDeJeu meneurDeJeu = new MeneurDeJeu(village, logger);
		meneurDeJeu.lancerDesParties(partie.getNbPartie());
		
		Map<String, Object> response = new HashMap<>();
		
        response.put("log", logger.getLogPartie());
        
		return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json").body(response);

    }
}
