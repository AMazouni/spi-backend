package fr.ubo.spibackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.entities.Etudiant;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.repositories.EtudiantRepository;

@Service
public class EtudiantService {

	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	PromotionService promotionService;

	public List<Etudiant> getAllEtudiant() throws ServiceException {
		List<Etudiant> etudiants = etudiantRepository.findAll();
		if (etudiants.size() == 0)
			throw new ServiceException("Il n'y a aucun étudiant", HttpStatus.NOT_FOUND);
		return etudiants;
	}

	@SuppressWarnings("unused")
	public List<Etudiant> createEtudiant(List<Candidat> candidats) throws ServiceException {
		if (candidats.isEmpty()) {
			throw new ServiceException("La liste des candidats est vide", HttpStatus.NOT_FOUND);
		}
		List<Etudiant> etudiants = new ArrayList<Etudiant>();
//        Promotion promotion;
		Etudiant etudiant;

		for (Candidat candidat : candidats) {
//            promotion = promotionService.findById(candidat.getAnneeUniversitaire(), candidat.getCodeFormation());
			List<Etudiant> etudiantsWithEmail = etudiantRepository.findAll().stream()
					.filter(etd -> candidat.getEmail().equals(etd.getEmail())).collect(Collectors.toList());

			if (!etudiantsWithEmail.isEmpty()) {
				throw new ServiceException("Candidat " + etudiantsWithEmail.get(0).getNom() + " "
						+ etudiantsWithEmail.get(0).getPrenom() + " est déjà accepté(e) comme étudiant(e)",
						HttpStatus.CONFLICT);

			} else {

				etudiant = etudiantRepository.save(new Etudiant(candidat.getCodeFormation(),
						candidat.getAnneeUniversitaire(), candidat.getNom(), candidat.getPrenom(), candidat.getSexe(),
						candidat.getDateNaissance(), candidat.getLieuNaissance(), candidat.getNationalite(),
						candidat.getTelephone(), candidat.getMobile(), candidat.getEmail(), candidat.getAdresse(),
						candidat.getCodePostal(), candidat.getVille(), candidat.getPaysOrigine(),
						candidat.getUniversiteOrigine(), null));

				etudiants.add(etudiant);
			}
		}
		return etudiants;

	}

}
