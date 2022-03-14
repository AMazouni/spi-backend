package fr.ubo.spibackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.ubo.spibackend.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.ubo.spibackend.entities.Formation;
import fr.ubo.spibackend.repositories.FormationRepository;

@Service
public class FormationService {

	@Autowired
	FormationRepository formationRepository;

	public ResponseEntity<List<Formation>> getAllFormations() {

		try {
			List<Formation> formations = new ArrayList<Formation>();

			formationRepository.findAll().forEach(formations::add);

			if (formations.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(formations, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<List<Formation>> getByNomFormation(String nomFormation) {

		try {
			List<Formation> formations = new ArrayList<Formation>();

			formationRepository.findByNomFormation(nomFormation).forEach(formations::add);

			return new ResponseEntity<>(formations, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public Formation getFormationById(String id) throws ServiceException {
		Optional<Formation> formation = formationRepository.findById(id);

		if (formation.isPresent())
			return  formation.get();
		throw new ServiceException("Pas de formation avec id ="+id,HttpStatus.NOT_FOUND);


	}

	public ResponseEntity<Formation> createFormation(Formation formation) {
		try {

			Formation _formation = formationRepository.save(formation);

			return new ResponseEntity<>(_formation, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Formation> updateFormation(String id, Formation formation) {
		Optional<Formation> formationData = formationRepository.findById(id);

		if (formationData.isPresent()) {
			Formation _formation = formationData.get();

			_formation.setDebutAccreditation(formation.getDebutAccreditation());
			_formation.setDiplome(formation.getDiplome());
			_formation.setDoubleDiplome(formation.getDoubleDiplome());
			_formation.setFinAccreditation(formation.getFinAccreditation());
			_formation.setN0Annee(formation.isN0Annee());
			_formation.setNomFormation(formation.getNomFormation());

			return new ResponseEntity<>(formationRepository.save(_formation), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<HttpStatus> deleteFormation(String id) {

		try {
			formationRepository.deleteById(id);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<HttpStatus> deleteAllFormations() {
		try {
			formationRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
