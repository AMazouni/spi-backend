package fr.ubo.spibackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.ubo.spibackend.entities.Formation;
import fr.ubo.spibackend.exception.RestErrorMessage;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.services.FormationService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path = "/formations")
public class FormationController {

	@Autowired
	private FormationService formationService;

	@GetMapping
	public ResponseEntity getAllFormations() {
		try {
			return new ResponseEntity<List<Formation>>(formationService.getAllFormations(), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
		} catch (Exception e) {
			return new ResponseEntity(new RestErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity getFormationById(@PathVariable("id") String id) {
		try {
			return new ResponseEntity<Formation>(formationService.getFormationById(id), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
		} catch (Exception e) {
			return new ResponseEntity(new RestErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/searchByCodeOrNom/{input}")
	public ResponseEntity getByNomFormation(@PathVariable("input") String input) {
		try {
			return new ResponseEntity<List<Formation>>(formationService.searchByCodeOrNom(input), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
		} catch (Exception e) {
			return new ResponseEntity(new RestErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity createFormation(@RequestBody Formation formation) {
		try {
			return new ResponseEntity<Formation>(formationService.createFormation(formation), HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
		} catch (Exception e) {
			return new ResponseEntity(new RestErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteFormation(@PathVariable("id") String id) {
		try {
			formationService.deleteFormation(id);
			return new ResponseEntity(null, HttpStatus.OK);
		} catch (ServiceException e) {
			return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
		} catch (Exception e) {
			return new ResponseEntity(new RestErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
