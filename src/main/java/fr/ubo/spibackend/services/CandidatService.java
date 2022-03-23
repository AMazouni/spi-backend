package fr.ubo.spibackend.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

		for (Candidat candidat : candidats) {

			Optional<Candidat> candidatsData = candidatRepo.findById(candidat.getNoCandidat());

			if (candidatsData.isPresent()) {
//				if (candidatsData.get().getConfirmationCandidat() == "N") {
//					throw new ServiceException(
//							"Merci de sélectionner que les candidats qui ont une confirmation positive ou en attente de confirmation.",
//							HttpStatus.CONFLICT);
//				}
				if (candidatsData.get().getListeSelection() != null) {
					throw new ServiceException(
							"Merci de sélectionner que les candidats qui n'ont pas de liste de sélection.",
							HttpStatus.CONFLICT);
				} else {
					candidatsData.get().setListeSelection(candidat.getListeSelection());
					candidatsData.get().setSelectionNoOrdre(candidat.getSelectionNoOrdre());
					listCandidats.add(candidatsData.get());
					candidatRepo.save(candidatsData.get());
				}

			} else {
				throw new ServiceException("Candidat n'existe pas", HttpStatus.NOT_FOUND);
			}

		}
		return listCandidats;

	}

	public int LastSelectionOrdreLP(List<Candidat> candidats){
		int max = 0;
		for(Candidat c : candidats)
			if(c.getSelectionNoOrdre()!=null)
			if(c.getSelectionNoOrdre()>max)
				max=c.getSelectionNoOrdre();
		return max;
	}

	public Candidat getFirstCandidatLANNon(List<Candidat> candidats) throws ServiceException {
		List<Candidat> candidatsLANNon = new ArrayList<>();
		List<Candidat> sortedCandidats = new ArrayList<>();
		for (Candidat c : candidats)
			if (!c.getConfirmationCandidat().equalsIgnoreCase("N") )
				candidatsLANNon.add(c);

		for (Candidat c : candidatsLANNon)
			if (c.getSelectionNoOrdre()!=null)

				try {
					sortedCandidats = candidatsLANNon.stream()
							.sorted(Comparator.comparing(Candidat::getSelectionNoOrdre))
							.collect(Collectors.toList());
				} catch (Exception e) {

					throw new ServiceException("Impossible de trier les candidats en liste d'attente par ordre de sélection ", HttpStatus.CONFLICT);

				}
				return  sortedCandidats.get(0);
			}

	public void updateConfirmationCandidat(Candidat candidat) throws ServiceException {

		List<Candidat> candidats = candidat.getPromotion().getCandidats();
		Candidat c = candidatRepo.findById(candidat.getNoCandidat()).orElse(null);
		Candidat cr=null;
		Candidat cr2=null;
		if(c!=null){
			if(candidat.getConfirmationCandidat().equalsIgnoreCase("O"))
				c.setConfirmationCandidat("O");
			if(candidat.getConfirmationCandidat().equalsIgnoreCase("N")){
				c.setConfirmationCandidat("N");
				if(candidat.getListeSelection().equalsIgnoreCase("LP")){
					List<Candidat> candidatsLp = new ArrayList<>();
					for(Candidat c1 : candidats)
						if(c1.getListeSelection().equalsIgnoreCase("LP"))
							candidatsLp.add(c1);
					for(Candidat c2 : candidatsLp) {
						cr = candidatRepo.findById(c2.getNoCandidat()).orElse(null);
						if (c2.getSelectionNoOrdre() < candidat.getSelectionNoOrdre())
							cr.setSelectionNoOrdre(cr.getSelectionNoOrdre() + 1);
					}

					List<Candidat> candidatsLA = new ArrayList<>();
					for(Candidat c3 : candidats)
						if(c3.getListeSelection().equalsIgnoreCase("LA"))
							candidatsLA.add(c3);

					Candidat c4 = this.getFirstCandidatLANNon(candidatsLA);
					Candidat cand = candidatRepo.findById(c4.getNoCandidat()).orElse(null);
					cand.setListeSelection("LP");
					cand.setSelectionNoOrdre(this.LastSelectionOrdreLP(candidatsLp)+1);

					for(Candidat c2 : candidatsLA) {
						cr2 = candidatRepo.findById(c2.getNoCandidat()).orElse(null);
						if (c2.getSelectionNoOrdre() < c4.getSelectionNoOrdre())
							cr2.setSelectionNoOrdre(cr2.getSelectionNoOrdre() + 1);


					}
				}
			}
		}

	}

    public void deleteCandidatByNocandidat(String noCandidat) throws ServiceException {
        Candidat c = candidatRepo.findById(noCandidat).orElse(null);
        if(c!=null)
            candidatRepo.deleteById(noCandidat);
        else
            throw new ServiceException("Le candidat "+noCandidat+" n'existe pas", HttpStatus.NOT_FOUND) ;
    }

    public Candidat updateCandidat (Candidat candidat)  throws ServiceException {
    	Optional<Candidat> myCandidat= candidatRepo.findById(candidat.getNoCandidat());
    	if(myCandidat.isPresent()) {
    		Candidat candidatResult= myCandidat.get();
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
    	throw new ServiceException("Le candidat "+candidat.getNoCandidat()+" n'existe pas", HttpStatus.NOT_FOUND) ;
    }
}
