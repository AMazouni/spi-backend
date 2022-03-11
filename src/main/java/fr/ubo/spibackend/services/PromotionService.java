package fr.ubo.spibackend.services;

import fr.ubo.spibackend.entities.Promotion;
import fr.ubo.spibackend.entities.PromotionPK;
import fr.ubo.spibackend.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PromotionService {

    @Autowired
    PromotionRepository promoRepo;


    public List<Promotion> findAll() {
          List<Promotion> promos= promoRepo.findAll(Sort.by(Sort.Direction.ASC, "anneeUniversitaire"));
    if(promos.size()==0) throw new NoSuchElementException();
    return promos;
    }

    public Promotion findByAnneeUniversitaireAndCodeFormation(String annee, String code) {
        PromotionPK pk = new PromotionPK(code,annee);
//        return promoRepo.findByAnneeUniversitaireAndCodeFormation(annee, code);
      return  promoRepo.findById(pk).get();
    }
}
