package fr.ubo.spibackend.services;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.entities.Etudiant;
import fr.ubo.spibackend.entities.Promotion;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.repositories.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.scripting.config.LangNamespaceHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CandidatService {

    @Autowired
    private CandidatRepository candidatRepo;

    @Autowired
    PromotionService promotionService;

    public Candidat saveCandidat(Candidat candidat) throws ServiceException {


      if (candidat.getAdresse() != null && candidat.getAnneeUniversitaire() != null && candidat.getCodeFormation() != null &&
                  candidat.getCodePostal() != null && candidat.getEmail() != null && candidat.getCodePostal() != null && candidat.getDateNaissance() != null &&
                  candidat.getLieuNaissance() != null && candidat.getNationalite() != null && candidat.getNom() != null && candidat.getPaysOrigine() != null &&
                  candidat.getPrenom() != null && candidat.getSexe() != null && candidat.getUniversiteOrigine() != null && candidat.getVille() != null)
               {
                   Promotion p = promotionService.findById(candidat.getAnneeUniversitaire(),candidat.getCodeFormation());
                   List<Candidat> cs = p.getCandidats();

                   if(cs.stream().anyMatch(c -> c.getEmail().equals(candidat.getEmail())))
                       throw new ServiceException("Un candidat dont le mail est "+candidat.getEmail()+" existe déja", HttpStatus.CONFLICT) ;

                   candidat.setPromotion(p);
              return candidatRepo.save(candidat);
              }

          throw new ServiceException("Merci de remplir les champs obligatoires", HttpStatus.BAD_REQUEST);
      }


    public List<Candidat> getAllCandidat() throws ServiceException {
      //  List<Candidat> candidats= candidatRepo.findAll().stream().sorted(Comparator.comparing(Candidat::getSelectionNoOrdre)).collect(Collectors.toList());
        List<Candidat> candidats= candidatRepo.findAll();

        if(candidats.size()==0)
            throw new ServiceException("Pas de candidats trouvée",HttpStatus.NOT_FOUND);
        return candidats;
    }

    public void deleteCandidatByNocandidat(String noCandidat) throws ServiceException {
        Candidat c = candidatRepo.findById(noCandidat).orElse(null);
        if(c!=null)
            candidatRepo.deleteById(noCandidat);
        else
            throw new ServiceException("Le candidat "+noCandidat+" n'existe pas", HttpStatus.NOT_FOUND) ;
    }

    public Candidat updateCandidat(Candidat candidat) throws ServiceException {
        Candidat c = candidatRepo.findById(candidat.getNoCandidat()).orElse(null);
        if(c!=null)
        {
           c.setConfirmationCandidat(candidat.getConfirmationCandidat());
           c.setListeSelection(candidat.getListeSelection());
          c.setSelectionNoOrdre(candidat.getSelectionNoOrdre());
           candidatRepo.save(c);
           return c;
        }
        else
            throw new ServiceException("Le candidat "+candidat.getNoCandidat()+" n'existe pas", HttpStatus.NOT_FOUND) ;
    }
}
