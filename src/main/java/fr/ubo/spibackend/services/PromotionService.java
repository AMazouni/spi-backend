package fr.ubo.spibackend.services;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.entities.Promotion;
import fr.ubo.spibackend.entities.PromotionPK;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.repositories.PromotionRepository;
import fr.ubo.spibackend.utils.SortEntites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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
//           List<Candidat> candidats=promo.getCandidats().stream().sorted((o1, o2)->o1.getListeSelection().
//                           compareTo(o2.getListeSelection())).
//                   collect(Collectors.toList());

           promo.setCandidats(promo.getCandidats().stream().sorted((Candidat a, Candidat b) ->SortEntites.compareCandidats(a,b)).collect(Collectors.toList()));
//           promo.setCandidats(candidats);
           return promo;
       }
       if(code.equals("a")){
           throw new ServiceException("test", HttpStatus.BAD_REQUEST) ;
       }
     throw new ServiceException("No Promotion with id {"+annee+";"+code+"}",HttpStatus.NOT_FOUND);


    }

    public ArrayList<Promotion> findByCodeFormationOrderByAnneeUniversitaireDes(String code) throws ServiceException {
        ArrayList<Promotion> r = promoRepo.findByCodeFormationOrderByAnneeUniversitaireDesc(code);
        if(r.size()==0) throw new ServiceException("Aucune Promotion pour la focmation (code ="+code+")dans la BD",HttpStatus.NOT_FOUND);
        return r;
    }
}
