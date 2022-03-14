package fr.ubo.spibackend.controllers;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.entities.Etudiant;
import fr.ubo.spibackend.exception.RestErrorMessage;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/etudiants")
public class EtudiantController {

    @Autowired
    EtudiantService etudiantSerice;

    @PostMapping
    public ResponseEntity createEtudiant(@RequestBody List<Candidat> candidats) {
        try {
            return new ResponseEntity<List<Etudiant>>(etudiantSerice.createEtudiant(candidats), HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }catch (Exception e ){
            return new ResponseEntity(new RestErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
