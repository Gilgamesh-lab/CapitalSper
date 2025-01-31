package SpringBot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.archetypes.CapitalSperLoupGarous.Logger;
import org.apache.maven.archetypes.CapitalSperLoupGarous.MeneurDeJeu;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Referentiel;
import org.apache.maven.archetypes.CapitalSperLoupGarous.Village;
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
    	Map<String, Object> response = new HashMap<>();
    	
    	if(partie.getNbPartie() <= 0) {
    		response.put("erreur", "Le nombre de partie doit au moins être supérieur à 0");
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    	}
    	
    	else if(partie.getNbPartie() > 100) {
    		response.put("erreur", "Le nombre de partie doit être inférieur ou égale à 100");
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    	}
    	
    	
    	
    	Village village = new Village(partie.getNbSimpleVillageois(),partie.getNbLoupGarou());
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
    	
    	if(village.getVillage().size() < 3) {
    		response.put("erreur", "Le village doit être composé d'au moins 3 personnages");
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    	}
    	
    	else if(village.getVillage().size() > 25) {
    		response.put("erreur", "Le village peut-être composé au maximun de 25 personnages");
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    	}
    	
    	boolean camp = village.getVillage().get(0).estUnVillageois();
    	if(village.getVillage().stream().allMatch(x->x.estUnVillageois() == camp)) {
    		response.put("erreur", "La composition du village doit au moins avoir deux personnages d'un camps différents");
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    	}
    	
    	village.initRequete();
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
    	meneurDeJeu.initRequete();
    	
    	try {
    		meneurDeJeu.lancerDesParties(partie.getNbPartie());
    	}
    	
    	catch (Exception e) {
    		response.put("erreur", "Une erreur est survenue lors de l'execution de la simulation : " + e);
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    	}
		
		
		
		
        response.put("log", logger.getLogPartie());
        
		return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "application/json").body(response);

    }
}
