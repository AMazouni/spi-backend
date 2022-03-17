package fr.ubo.spibackend.controllers;



import fr.ubo.spibackend.entities.Enseignant;
import fr.ubo.spibackend.exception.RestErrorMessage;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.services.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/enseignants")
public class EnseignantController {

    @Autowired
    private EnseignantService enseignantServices;

    @GetMapping("")
    public ResponseEntity<List<Enseignant>> findAllEnseignant() {
        try {
            return new ResponseEntity(enseignantServices.getAllEnseignant(), HttpStatus.OK);
        } catch (ServiceException e){
            return new ResponseEntity(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }
        catch (Exception e ){ e.printStackTrace();
            return new ResponseEntity(new RestErrorMessage("erreur serveur 500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
