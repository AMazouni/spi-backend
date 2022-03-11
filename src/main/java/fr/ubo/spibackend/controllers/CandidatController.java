package fr.ubo.spibackend.controllers;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.exception.RestErrorMessage;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.services.CandidatServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/candidats")
public class CandidatController {

    @Autowired
   private CandidatServices candidatServices;
    private static final Logger logger = LogManager.getLogger(CandidatController.class);

    @GetMapping("/")
    public ResponseEntity<List<Candidat>> findAllCandidat() {
        try {
            return new ResponseEntity(candidatServices.getAllCandidat(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new RestErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity addCandidat(@RequestBody Candidat candidat) {
        try {
            return new ResponseEntity<Candidat>(candidatServices.saveCandidat(candidat), HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }catch (Exception e ){
            return new ResponseEntity(new RestErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/")
    public ResponseEntity updateCandidat(@RequestBody Candidat candidat) {
        try {
            return new ResponseEntity<Candidat>(candidatServices.updateCandidat(candidat), HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }catch (Exception e ){
            return new ResponseEntity(new RestErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{noCandidat}")
    public ResponseEntity deleteCandidat(@RequestBody String noCandidat) {
        try {
            candidatServices.deleteCandidatByNocandidat(noCandidat);
            return new ResponseEntity(null, HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }catch (Exception e ){
            return new ResponseEntity(new RestErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
