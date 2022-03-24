package fr.ubo.spibackend.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.exception.RestErrorMessage;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.services.CandidatService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/candidats")
public class CandidatController {

	@Autowired
	private CandidatService candidatServices;
	private static final Logger logger = LogManager.getLogger(CandidatController.class);

	@GetMapping("")
	public ResponseEntity<List<Candidat>> findAllCandidat() {
		try {
			return new ResponseEntity(candidatServices.getAllCandidat(), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new RestErrorMessage("erreur serveur 500"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	public ResponseEntity addCandidat(@RequestBody Candidat candidat) {
		try {
			logger.info("Candidat bien inséré");
			return new ResponseEntity<Candidat>(candidatServices.saveCandidat(candidat), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new RestErrorMessage("erreur serveur 500"), HttpStatus.INTERNAL_SERVER_ERROR);
		}


    }
/*
    @PutMapping("")
    public ResponseEntity updateCandidat(@RequestBody Candidat candidat) {
        try {
            return new ResponseEntity<Candidat>(candidatServices.updateCandidat(candidat), HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }catch (Exception e ){ e.printStackTrace();
            return new ResponseEntity(new RestErrorMessage("erreur serveur 500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    */

    @DeleteMapping("/{noCandidat}")
    public ResponseEntity deleteCandidat(@PathVariable String noCandidat) {
        try {
            candidatServices.deleteCandidatByNocandidat(noCandidat);
            return new ResponseEntity(null, HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }catch (Exception e ){ e.printStackTrace();
            return new ResponseEntity(new RestErrorMessage("erreur serveur 500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    
    @PutMapping("")
    public ResponseEntity updateCandidat(@RequestBody Candidat candidat) {
        try {
            return new ResponseEntity<Candidat>(candidatServices.updateCandidat(candidat), HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }catch (Exception e ){ e.printStackTrace();
            return new ResponseEntity(new RestErrorMessage("erreur serveur 500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    

	@PutMapping("/updateConfirmation")
	public ResponseEntity updateConfirmationCandidat(@RequestBody Candidat candidat) {
		try {
			candidatServices.updateConfirmationCandidat(candidat);
			return new ResponseEntity(null, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new RestErrorMessage("erreur serveur 500"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateListe")
	public ResponseEntity updateListeCandidat(@RequestBody List<Candidat> candidats) {
		try {
			return new ResponseEntity<List<Candidat>>(candidatServices.updateListeCandidat(candidats), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(new RestErrorMessage("erreur serveur 500"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity findById(@PathVariable String id) {
		try {
			return new ResponseEntity<Candidat>(candidatServices.findById(id), HttpStatus.OK);
		}catch(ServiceException e){
			return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
		}catch (Exception e ){ e.printStackTrace();
			return new ResponseEntity(new RestErrorMessage("erreur serveur 500"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
