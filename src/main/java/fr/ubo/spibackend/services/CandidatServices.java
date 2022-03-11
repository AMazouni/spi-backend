package fr.ubo.spibackend.services;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.repositories.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scripting.config.LangNamespaceHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatServices {

    @Autowired
    private CandidatRepository candidatRepo;

    public Candidat saveCandidat(Candidat candidat){
       Candidat c = candidatRepo.findById(candidat.getNoCandidat()).orElse(null);
       if(c!=null)
           return candidatRepo.save(c);
       else
           return null;
    }

    public List<Candidat> getAllCandidat(){
        return candidatRepo.findAll();
    }

    public List<Candidat> getAllCandidatByUniversiteO(String universiteOrigine){
        return candidatRepo.findByUniversiteOrigine(universiteOrigine);
    }

    public List<Candidat> getAllCandidatByCodeFormation(String codeFormation){
        return candidatRepo.findByCodeFormation(codeFormation);
    }

    public List<Candidat> getAllCandidatBtListeSelection(String listeSelection){
        return candidatRepo.findByListeSelection(listeSelection);
    }

    public List<Candidat> getAllCandidatBycodeAndAnnee(String codeFormation, String anneeUniversitaire){
        return candidatRepo.findByCodeFormationAndAnneeUniversitaire(codeFormation, anneeUniversitaire);
    }

    public String deleteCandidat(String noCandidat){
        Candidat c = candidatRepo.findById(noCandidat).orElse(null);
        if(c!=null) {
            candidatRepo.deleteById(noCandidat);
            return "Candidat "+noCandidat+" a bien été supprimé";
        }
        else
            return "Candidat "+noCandidat+" n'existe pas";
    }

    public Candidat getCandidatByNoCandidat(String noCandidat){
        return candidatRepo.findById(noCandidat).orElse(null);
    }
}
