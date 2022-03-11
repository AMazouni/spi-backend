package fr.ubo.spibackend.services;

import fr.ubo.spibackend.entities.Promotion;
import fr.ubo.spibackend.entities.PromotionPK;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PromotionService {

    @Autowired
    PromotionRepository promoRepo;


    public List<Promotion> findAll() throws ServiceException {
          List<Promotion> promos= promoRepo.findAll(Sort.by(Sort.Direction.ASC, "anneeUniversitaire"));
    if(promos.size()==0) throw new ServiceException("Aucune Promotion dans la BD",HttpStatus.NOT_FOUND);
    return promos;
    }

    public Promotion findById(String annee, String code)  throws  ServiceException {
        PromotionPK pk = new PromotionPK(code,annee);
     //   return promoRepo.findByAnneeUniversitaireAndCodeFormation(annee, code);
       Optional<Promotion> result= promoRepo.findById(pk);
       if(result.isPresent()){
           Promotion promo= result.get();
//           promo.getCandidats().sort(e->e);
           return promo;
       }
       if(code.equals("a")){
           throw new ServiceException("test", HttpStatus.BAD_REQUEST) ;
       }
     throw new ServiceException("No Promotion with id {"+annee+";"+code+"}",HttpStatus.NOT_FOUND);


    }

    public ArrayList<Promotion> findByCodeFormationOrderByAnneeUniversitaireDes(String code) {
        return promoRepo.findByCodeFormationOrderByAnneeUniversitaireDesc(code);
    }
}
