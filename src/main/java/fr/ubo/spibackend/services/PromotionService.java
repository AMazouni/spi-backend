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
import java.util.*;
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
    @Autowired
    FormationService formServ;
    @Autowired
    EtudiantService etuServ;



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
            promo.setCandidats(promo.getCandidats().stream().sorted(SortEntites::compareCandidats).collect(Collectors.toList()));
            return promo;
//           List<Candidat> candidats=promo.getCandidats().stream().sorted((o1, o2)->o1.getListeSelection().
//                           compareTo(o2.getListeSelection())).
//                   collect(Collectors.toList());
    }

    public ArrayList<Promotion> findByCodeFormation(String code) throws ServiceException {
        ArrayList<Promotion> resultats = promoRepo.findByCodeFormationOrderByAnneeUniversitaireDesc(code);
        if(resultats.size()==0)
            throw new ServiceException("Aucune Promotion pour la focmation (code ="+code+")dans la BD",HttpStatus.NOT_FOUND);

        resultats = (ArrayList<Promotion>) resultats.stream().map(this::orderCandidats).collect(Collectors.toList());
        return resultats;
    }

    public Promotion save(Promotion e) throws ServiceException {
        if(e.getCodeFormation()==null | e.getAnneeUniversitaire()==null)
            throw new ServiceException("Le code de formation et/ou l'année universitaire sont null",HttpStatus.BAD_REQUEST);
        if(e.getNbMaxEtudiant()<=0)
            throw new ServiceException("Le nb Max d'étudiant doit être spécifié ",HttpStatus.BAD_REQUEST);
        PromotionPK pk = new PromotionPK(e.getCodeFormation(),e.getAnneeUniversitaire());
        Optional<Promotion> result= promoRepo.findById(pk);
        if(result.isPresent()){
            throw new ServiceException("Une promo similaire existe déjà dans la base de donnée (Année + Formation) ",HttpStatus.BAD_REQUEST);
        }
        if(e.getDateRentree()==null | e.getDateReponseLp()==null | e.getDateReponseLalp()==null)
            throw new ServiceException("La promotion doit inclure les dates de Réponse LP,de réponse LA et de la rentrée",HttpStatus.BAD_REQUEST);
        if(!(e.getDateReponseLp().before(e.getDateReponseLalp()) & e.getDateReponseLalp().before(e.getDateRentree())))
            throw new ServiceException("Les dates ne respectent pas les contraintes imposées (Date Liste Principale < Date Liste Attente < Date Rentrére ",HttpStatus.BAD_REQUEST);

        //TODO : processus stage
        try{
            e.setFormationByCodeFormation(formaServ.getFormationById(e.getCodeFormation()));
            List<Candidat> populatedCandidats = new ArrayList<>();
//            for (Candidat c : e.getCandidats()){
//                populatedCandidats.add(candServ.getCandidatByNoCandidat(c.getNoCandidat()));
//            }
            e.setCandidats(populatedCandidats);
        if(e.getNoEnseignant()!=null)
            e.setEnseignantByNoEnseignant(enDao.findById(e.getNoEnseignant()).get());
            e.setEtudiants(new ArrayList<Etudiant>());
        }catch (ServiceException ex){
            throw new ServiceException("save Promotion exception : "+ex.getErrorMeassage(),ex.getHttpStatus());
        }catch (NoSuchElementException ex){
            throw new ServiceException("save Promotion exception : No such Element Enseignant"+ex.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return promoRepo.save(e);
    }

    @Transactional
    public Promotion accepterCandidats(String annee, String code) throws ServiceException {
        Promotion promo = this.findById(annee,code);
        List<Candidat>  cand = promo.getCandidats();
        //Candidat de la liste principale
        List<Candidat> candidatsLp = new ArrayList<>();
        for(Candidat c : cand)
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
        int nbEtudiantRestants = promo.getNbMaxEtudiant() - promo.getEtudiants().size();
        for(int i=0; i<nbEtudiantRestants & i<sortedCandidats.size();i++)
            aMigrer.add(sortedCandidats.get(i));

        List<Etudiant> etudiants = etuServ.createEtudiant(aMigrer);

        for(Candidat can: aMigrer){
            candServ.deleteCandidatByNocandidat(can.getNoCandidat());
        }
        promo = this.findById(annee,code);


        return promo;
    }


}
