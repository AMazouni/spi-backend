package fr.ubo.spibackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.ubo.spibackend.entities.Formation;
import fr.ubo.spibackend.services.FormationService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path = "/v1/api/formations")
public class FormationController {

	@Autowired
	private FormationService formationService;

	/*@GetMapping
	public ResponseEntity<List<Formation>> getAllFormations() {
		return formationService.getAllFormations();
	}

	/*@GetMapping("/{id}")
	public ResponseEntity<Formation> getClientById(@PathVariable("id") String id) {
		return formationService.getFormationById(id);
	}*/

	@GetMapping("/nom/{nomFormation}")
	public ResponseEntity<List<Formation>> getByNomFormation(@PathVariable("nomFormation") String nomFormation) {
		return formationService.getByNomFormation(nomFormation);
	}

	@PostMapping
	public ResponseEntity<Formation> createFormation(@RequestBody Formation formation) {
		return formationService.createFormation(formation);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Formation> updateFormation(@PathVariable("id") String id, @RequestBody Formation client) {
		return formationService.updateFormation(id, client);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteFormation(@PathVariable("id") String id) {
		return formationService.deleteFormation(id);
	}

	@DeleteMapping
	public ResponseEntity<HttpStatus> deleteAllFormations() {
		return formationService.deleteAllFormations();
	}
}
