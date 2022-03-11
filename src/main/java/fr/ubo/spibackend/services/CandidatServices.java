package fr.ubo.spibackend.services;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.entities.Etudiant;
import fr.ubo.spibackend.entities.Promotion;
import fr.ubo.spibackend.repositories.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scripting.config.LangNamespaceHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatServices {

    @Autowired
    private CandidatRepository candidatRepo;

    public Candidat saveCandidat(Candidat candidat){
       Candidat c = candidatRepo.findById(candidat.getNoCandidat()).orElse(null);
       if(c==null)
           return candidatRepo.save(candidat);
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

    /*public void migrateCandidat(){
        Promotion p = new Promotion();
        List<Candidat> cs = p.getCandidats();

        //Candidat de la liste principale
        List<Candidat> candidatsLp = new ArrayList<>();
        for(Candidat c : cs)
            if(c.getListeSelection().equalsIgnoreCase("LP"))
                candidatsLp.add(c);

        //Candidat ayant Confirmation_Candidat=oui
        List<Candidat> candidatsOui = new ArrayList<>();
        for(Candidat c : candidatsLp)
            if(c.getConfirmationCandidat().equalsIgnoreCase("O"))
          candidatsOui.add(c);

        //Trie des candidats de la liste principale ayant dit oui
        List<Candidat> sortedCandidats = candidatsOui.stream()
                .sorted(Comparator.comparing(Candidat::getSelectionNoOrdre))
                .collect(Collectors.toList());

        //Liste de candidats à migrer
        List<Candidat> aMigrer = new ArrayList<>();
       for(int i=0; i<p.getNbMaxEtudiant();i++)
           aMigrer.add(sortedCandidats.get(i));

       //transformation des candidats en etudiants
       for(Candidat c : aMigrer){
           Etudiant e = new Etudiant();
           e.setNom(c.getNom());
           e.setPrenom(c.getPrenom());
           ...

           EtudiantService.save(e);
       }
    }*/
}
