package fr.ubo.spibackend.services;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.entities.Etudiant;
import fr.ubo.spibackend.entities.Promotion;
import fr.ubo.spibackend.entities.PromotionPK;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.repositories.EnseignantRepository;
import fr.ubo.spibackend.repositories.PromotionRepository;
import fr.ubo.spibackend.utils.SortEntites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    @Autowired
    PromotionRepository promoRepo;
    @Autowired
    FormationService formaServ;
    @Autowired
    CandidatService candServ;
//    @Autowired
//    EnseigantService ensServ;
    @Autowired
    EnseignantRepository enDao;


    public List<Promotion> findAll() throws ServiceException {
        List<Promotion> promos= promoRepo.findAll(Sort.by(Sort.Direction.ASC, "anneeUniversitaire"));
        if(promos.size()==0)
            throw new ServiceException("Aucune Promotion dans la BD",HttpStatus.NOT_FOUND);
        return promos;
    }




    public Promotion findById(String annee, String code)  throws  ServiceException {
    PromotionPK pk = new PromotionPK(code,annee);
    Optional<Promotion> result= promoRepo.findById(pk);
    if(result.isPresent()){
            return orderCandidats(result.get());
    }
    throw new ServiceException("No Promotion with id {"+annee+";"+code+"}",HttpStatus.NOT_FOUND);


    }

    private Promotion orderCandidats(Promotion promo){
            promo.setCandidats(promo.getCandidats().stream().sorted((Candidat a, Candidat b) ->SortEntites.compareCandidats(a,b)).collect(Collectors.toList()));
            return promo;
//           List<Candidat> candidats=promo.getCandidats().stream().sorted((o1, o2)->o1.getListeSelection().
//                           compareTo(o2.getListeSelection())).
//                   collect(Collectors.toList());
    }

    public ArrayList<Promotion> findByCodeFormationOrderByAnneeUniversitaireDes(String code) throws ServiceException {
        ArrayList<Promotion> resultats = promoRepo.findByCodeFormationOrderByAnneeUniversitaireDesc(code);
        if(resultats.size()==0)
            throw new ServiceException("Aucune Promotion pour la focmation (code ="+code+")dans la BD",HttpStatus.NOT_FOUND);

        resultats = (ArrayList<Promotion>) resultats.stream().map(this::orderCandidats).collect(Collectors.toList());
        return resultats;
    }

    public Promotion save(Promotion e) throws ServiceException {
        if(e.getCodeFormation()==null | e.getCodeFormation()==null)
            throw new ServiceException("Le code de formation et/ou l'année universitaire sont null",HttpStatus.BAD_REQUEST);
        if(e.getNbMaxEtudiant()<=0)
            throw new ServiceException("Le nb Max d'étudiant doit être spécifié null",HttpStatus.BAD_REQUEST);

//        try{
//            //e.setFormationByCodeFormation(formaServ.getFormationById(e.getCodeFormation()));
//            List<Candidat> populatedCandidats = new ArrayList<Candidat>();
//            for (Candidat c : e.getCandidats()){
//                populatedCandidats.add(candServ.getCandidatByNoCandidat(c.getNoCandidat()));
//            }
//        if(e.getNoEnseignant()!=null)
//            e.setEnseignantByNoEnseignant(enDao.getById(e.getNoEnseignant()));
//            e.setEtudiants(new ArrayList<Etudiant>());
//        }catch (ServiceException ex){
//            throw new ServiceException("save Promotion exception : "+ex.getErrorMeassage(),ex.getHttpStatus());
//        }

        return promoRepo.save(e);
    }

    @Transactional
    public Promotion tenirCandidats(String annee, String code) throws ServiceException {
        Promotion promo = this.findById(annee,code);
        List<Candidat>  cand = promo.getCandidats();
        for(int i=0;i<promo.getNbMaxEtudiant() & i<cand.size();i++){
            //TODO ; method to save etudiants
            //TODO ; method to delete candidat .... or make ignored next time
        }

        return promo;
    }


}
