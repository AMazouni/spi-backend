package fr.ubo.spibackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fr.ubo.spibackend.entities.Formation;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.repositories.FormationRepository;

@Service
public class FormationService {

	@Autowired
	FormationRepository formationRepository;

	public List<Formation> getAllFormations() throws ServiceException {
		List<Formation> formations = new ArrayList<Formation>();

		formationRepository.findAll().forEach(formations::add);

		if (formations.isEmpty())
			throw new ServiceException("Aucune Promotion dans la BD", HttpStatus.NOT_FOUND);

		return formations;
	}

	public List<Formation> searchByCodeOrNom(String input) throws ServiceException {

		List<Formation> formations = new ArrayList<Formation>();

		formationRepository.findByCodeFormationLikeOrNomFormationLike(input, input).forEach(formations::add);

		if (formations.isEmpty())
			throw new ServiceException("Acune formation existe " + input, HttpStatus.NOT_FOUND);

		return formations;

	}

	public Formation getFormationById(String id) throws ServiceException {
		Optional<Formation> formation = formationRepository.findById(id);

		if (formation.isPresent())
			return formation.get();
		throw new ServiceException("Pas de formation avec id =" + id, HttpStatus.NOT_FOUND);
	}

	public Formation createFormation(Formation formation) throws ServiceException {
		if (formation.getNomFormation() == null && formation.getDiplome() == null
				&& formation.getDoubleDiplome() == null)
			throw new ServiceException("Merci de remplir les champs obligatoires", HttpStatus.BAD_REQUEST);
		if (formation.getN0Annee() <= 0)
			throw new ServiceException("Merci de donner un numero valide", HttpStatus.BAD_REQUEST);
		/*
		 * Formation _formation = formationRepository.save(new
		 * Formation(formation.getNomFormation(), formation.getN0Annee(),
		 * formation.getDiplome(),formation.getDoubleDiplome(),
		 * formation.getDebutAccreditation(), formation.getFinAccreditation() ));
		 */

		return formationRepository.save(formation);
	}

	public void deleteFormation(String id) throws ServiceException {
		Formation formation = formationRepository.getById(id);
		if (formation != null)
			formationRepository.deleteById(id);
		else
			throw new ServiceException("La formation " + id + " n'exite pas", HttpStatus.BAD_REQUEST);

	}
}
