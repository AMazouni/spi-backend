package fr.ubo.spibackend.controllers;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.services.CandidatServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CandidatController {

    @Autowired
   private CandidatServices candidatServices;
    private static final Logger logger = LogManager.getLogger(CandidatController.class);


    @PostMapping("/candidats")
    public ResponseEntity<Candidat> addCandidat(@RequestBody Candidat candidat) {

        try {
            Candidat data = candidatServices.getCandidatByNoCandidat(candidat.getNoCandidat());


            if (data!=null) {
                logger.warn("ce candidat existe déja");
                return new ResponseEntity("Le candidat "+candidat.getNoCandidat()+" existe deja", HttpStatus.OK);
            }
            logger.info("Ce candidat n'existe pas encore");
            if( candidat.getAdresse()!=null && candidat.getAnneeUniversitaire()!=null && candidat.getCodeFormation()!=null &&
                    candidat.getCodePostal()!=null && candidat.getEmail()!=null && candidat.getCodePostal()!=null && candidat.getDateNaissance()!=null &&
                    candidat.getLieuNaissance()!=null && candidat.getNationalite()!=null && candidat.getNom()!=null && candidat.getPaysOrigine()!=null &&
                    candidat.getPrenom()!=null && candidat.getPromotion()!=null && candidat.getSexe()!=null && candidat.getUniversiteOrigine()!=null && candidat.getVille()!= null) {
                candidatServices.saveCandidat(candidat);
                return new ResponseEntity<>(data, HttpStatus.OK);
            }
            else
                 return new ResponseEntity("Informations manquantes sur le candidat à enregistrer ", HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Problème survenu durant l'insertion du candidat "+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/candidats/{codeFormation}/{anneeUniversitaire}")
    public ResponseEntity<List<Candidat>> findCandidatBycodeFormationAndAnneeUniversitaire(@PathVariable String codeFormation, @PathVariable String anneeUniversitaire){
        try {
            List<Candidat> data = candidatServices.getAllCandidatBycodeAndAnnee(codeFormation, anneeUniversitaire);

            if (data.isEmpty()) {
                logger.warn("Pas de données trouvées");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("Données trouvées");
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Problème survenu durant la recherche de données"+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
