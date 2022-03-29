package fr.ubo.spibackend.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.entities.Promotion;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.repositories.CandidatRepository;

@Service
public class CandidatService {

	@Autowired
	private CandidatRepository candidatRepo;

	@Autowired
	PromotionService promotionService;

	public Candidat saveCandidat(Candidat candidat) throws ServiceException {

		if (candidat.getAdresse() != null && candidat.getAnneeUniversitaire() != null
				&& candidat.getCodeFormation() != null && candidat.getCodePostal() != null
				&& candidat.getEmail() != null && candidat.getDateNaissance() != null
				&& candidat.getLieuNaissance() != null && candidat.getNationalite() != null && candidat.getNom() != null
				&& candidat.getPaysOrigine() != null && candidat.getPrenom() != null && candidat.getSexe() != null
				&& candidat.getUniversiteOrigine() != null && candidat.getVille() != null) {
			Promotion p = promotionService.findById(candidat.getAnneeUniversitaire(), candidat.getCodeFormation());
			List<Candidat> cs = p.getCandidats();

			if (cs.stream().anyMatch(c -> c.getEmail().equals(candidat.getEmail())))
				throw new ServiceException("Un candidat dont le mail est " + candidat.getEmail() + " existe déja",
						HttpStatus.CONFLICT);

			candidat.setPromotion(p);
			return candidatRepo.save(candidat);
		}

		throw new ServiceException("Merci de remplir les champs obligatoires", HttpStatus.BAD_REQUEST);
	}

	public List<Candidat> getAllCandidat() throws ServiceException {
		// List<Candidat> candidats=
		// candidatRepo.findAll().stream().sorted(Comparator.comparing(Candidat::getSelectionNoOrdre)).collect(Collectors.toList());
		List<Candidat> candidats = candidatRepo.findAll();

		if (candidats.size() == 0)
			throw new ServiceException("Pas de candidats trouvée", HttpStatus.NOT_FOUND);
		return candidats;
	}

	public List<Candidat> updateListeCandidat(List<Candidat> candidats) throws ServiceException {

		List<Candidat> listCandidats = new ArrayList<Candidat>();
		String list = candidats.get(0).getListeSelection().trim();

		for (Candidat candidat : candidats) {

			Optional<Candidat> candidatsData = candidatRepo.findById(candidat.getNoCandidat());

			if (candidatsData.isPresent()) {

				if (!candidat.getListeSelection().trim().equals(list))
					throw new ServiceException("Les candidats sélectionnés ne sont pas affectés à la même liste.",
							HttpStatus.CONFLICT);

				candidatsData.get().setListeSelection(candidat.getListeSelection());
				candidatsData.get().setSelectionNoOrdre(candidat.getSelectionNoOrdre());
				listCandidats.add(candidatsData.get());

			} else {
				throw new ServiceException("Candidat n'existe pas", HttpStatus.NOT_FOUND);
			}

		}
		candidatRepo.saveAll(listCandidats);
		return listCandidats;

	}

	// Fonction qui permet d'avoir le dernier numéro de selection d'ordre de la
	// liste principale
	public int LastSelectionOrdreLP(List<Candidat> candidats) {
		int max = 0;
		for (Candidat c : candidats)
			if (c.getSelectionNoOrdre() != null)
				if (c.getSelectionNoOrdre() > max)
					max = c.getSelectionNoOrdre();
		return max;
	}

	// Fonction qui permet d'avoir le premier candidat de la liste d'attente n'ayant
	// pas dit non
	public Candidat getFirstCandidatLANNon(List<Candidat> candidats) throws ServiceException {
		List<Candidat> candidatsLANNon = new ArrayList<>();
		List<Candidat> sortedCandidats = new ArrayList<>();
		List<Candidat> candidatsLANNonOrdered = new ArrayList<>();
		for (Candidat c : candidats)
			if (!c.getConfirmationCandidat().equalsIgnoreCase("N"))
				candidatsLANNon.add(c);

		for (Candidat c : candidatsLANNon)
			if (c.getSelectionNoOrdre() != null)
				candidatsLANNonOrdered.add(c);
		try {
			sortedCandidats = candidatsLANNonOrdered.stream()
					.sorted(Comparator.comparing(Candidat::getSelectionNoOrdre)).collect(Collectors.toList());
		} catch (Exception e) {

			throw new ServiceException("Impossible de trier les candidats en liste d'attente par ordre de sélection ",
					HttpStatus.CONFLICT);

		}
		if (!sortedCandidats.isEmpty())
			return sortedCandidats.get(0);
		else
			return null;
	}

	public void updateConfirmationCandidat(Candidat candidat) throws ServiceException {
		// List<Candidat> candidats = candidat.getPromotion().getCandidats();
		List<Candidat> candidats = candidatRepo.findAll();
		Candidat c = candidatRepo.findById(candidat.getNoCandidat()).orElse(null);
		Candidat cr = null;
		Candidat cr2 = null;
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/YYY");
		if (c != null) {

			// Si Confirmation_candidat = oui
			if (candidat.getConfirmationCandidat().equalsIgnoreCase("O"))
				c.setConfirmationCandidat("O");
			c.setDateReponseCandidat(LocalDate.now());
			candidatRepo.save(c);

			// Si confirmation_candidat = non
			if (candidat.getConfirmationCandidat().equalsIgnoreCase("N")) {
				c.setConfirmationCandidat("N");
				c.setDateReponseCandidat(LocalDate.now());

				// Si confirmation_candidat = non et liste_selection_candidat = LP
				if (candidat.getListeSelection().equalsIgnoreCase("LP")) {
					List<Candidat> candidatsLp = new ArrayList<>();

					// recencement des candidat de la liste LP
					for (Candidat c1 : candidats)
						if (c1.getListeSelection().equalsIgnoreCase("LP"))
							candidatsLp.add(c1);

					// je vérifie qu'il y ait plus d'un candidat en liste principale avant de
					// décrémenter
					if (candidatsLp.size() > 1) {
						// Stockage du dernier numéro de selection avant décrémentation
						int max = this.LastSelectionOrdreLP(candidatsLp);

						// Dérémentation du numéro d'ordre de sélection des candidats qui se trouvent
						// après le candidat
						for (Candidat c2 : candidatsLp) {
							cr = candidatRepo.findById(c2.getNoCandidat()).orElse(null);
							if (c2.getSelectionNoOrdre() != null && candidat.getSelectionNoOrdre() != null
									&& c2.getSelectionNoOrdre() > candidat.getSelectionNoOrdre())
								cr.setSelectionNoOrdre(cr.getSelectionNoOrdre() - 1);
							// candidatRepo.save(cr);
						}

						// recencement des candidats de la liste LA
						List<Candidat> candidatsLA = new ArrayList<>();
						for (Candidat c3 : candidats)
							if (c3.getListeSelection().equalsIgnoreCase("LA"))
								candidatsLA.add(c3);

						// Premier candidat de la liste d'attente qui n'a pas dit non
						Candidat c4 = this.getFirstCandidatLANNon(candidatsLA);
						int order = c4.getSelectionNoOrdre();

						if (c4 != null) {
							Candidat cand = candidatRepo.findById(c4.getNoCandidat()).orElse(null);
							// Modification de sa liste de selection et de son numéro d'ordre
							cand.setListeSelection("LP");
							cand.setSelectionNoOrdre(max + 1);

							// Décrémentation du numéro d'ordre de sélection des candidats de la LA qui se
							// trouvent après le candidat passé en LP
							if (candidatsLA.size() > 1) {
								for (Candidat c2 : candidatsLA) {
									cr2 = candidatRepo.findById(c2.getNoCandidat()).orElse(null);
									if (c2.getSelectionNoOrdre() != null && c4.getSelectionNoOrdre() != null
											&& c2.getSelectionNoOrdre() > order)
										cr2.setSelectionNoOrdre(cr2.getSelectionNoOrdre() - 1);
								}
							}
						}
					}
				}
				// Si confirmation_candidat = non et liste_selection_candidat = LA
				else if (candidat.getListeSelection().equalsIgnoreCase("LA")) {
					// recencement des candidats de la liste LA
					List<Candidat> candidatsLA = new ArrayList<>();
					for (Candidat c3 : candidats)
						if (c3.getListeSelection().equalsIgnoreCase("LA"))
							candidatsLA.add(c3);

					if (candidatsLA.size() > 1) {
						for (Candidat c2 : candidatsLA) {
							cr = candidatRepo.findById(c2.getNoCandidat()).orElse(null);
							if (c2.getSelectionNoOrdre() != null
									&& c2.getSelectionNoOrdre() > candidat.getSelectionNoOrdre())
								cr.setSelectionNoOrdre(cr.getSelectionNoOrdre() - 1);
						}
					}
				}
				c.setSelectionNoOrdre(null);
				candidatRepo.save(c);
			}

		} else
			throw new ServiceException(
					"Le candidat " + candidat.getPrenom() + " " + candidat.getNom() + " n'a pas été trouvé",
					HttpStatus.NOT_FOUND);

	}

	public void deleteCandidatByNocandidat(String noCandidat) throws ServiceException {
		Candidat c = candidatRepo.findById(noCandidat).orElse(null);
		if (c != null)
			candidatRepo.deleteById(noCandidat);
		else
			throw new ServiceException("Le candidat " + noCandidat + " n'existe pas", HttpStatus.NOT_FOUND);
	}

	public Candidat updateCandidat(Candidat candidat) throws ServiceException {
		Optional<Candidat> myCandidat = candidatRepo.findById(candidat.getNoCandidat());
		if (myCandidat.isPresent()) {
			Candidat candidatResult = myCandidat.get();
			candidatResult.setCodeFormation(candidat.getCodeFormation());
			candidatResult.setAnneeUniversitaire(candidat.getAnneeUniversitaire());
			candidatResult.setNom(candidat.getNom());
			candidatResult.setPrenom(candidat.getPrenom());
			candidatResult.setSexe(candidat.getSexe());
			candidatResult.setDateNaissance(candidat.getDateNaissance());
			candidatResult.setLieuNaissance(candidat.getLieuNaissance());
			candidatResult.setNationalite(candidat.getNationalite());
			candidatResult.setTelephone(candidat.getTelephone());
			candidatResult.setMobile(candidat.getMobile());
			candidatResult.setEmail(candidat.getEmail());
			candidatResult.setAdresse(candidat.getAdresse());
			candidatResult.setCodePostal(candidat.getCodePostal());
			candidatResult.setVille(candidat.getVille());
			candidatResult.setPaysOrigine(candidat.getPaysOrigine());
			candidatResult.setUniversiteOrigine(candidat.getUniversiteOrigine());
			candidatRepo.save(candidatResult);

			return candidatResult;

		}
		throw new ServiceException("Le candidat " + candidat.getNoCandidat() + " n'existe pas", HttpStatus.NOT_FOUND);
	}

	public Candidat findById(String s) throws ServiceException {
		Candidat c = candidatRepo.findById(s)
				.orElseThrow(() -> new ServiceException("Aucun candidat trouvé.", HttpStatus.NOT_FOUND));
		return c;
	}

}
