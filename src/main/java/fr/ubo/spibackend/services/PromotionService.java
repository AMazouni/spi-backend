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

import javax.persistence.EntityManager;
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
    @Autowired
    EntityManager em;



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
            throw new ServiceException("Le code de formation et l'année universitaire sont obligatoires",HttpStatus.BAD_REQUEST);
        if(e.getNbMaxEtudiant()<0 | e.getNbMaxEtudiant()>999)
            throw new ServiceException("Le Nombre Max d'étudiants doit être positif.",HttpStatus.PRECONDITION_FAILED);
        PromotionPK pk = new PromotionPK(e.getCodeFormation(),e.getAnneeUniversitaire());
        Optional<Promotion> result= promoRepo.findById(pk);
        if(result.isPresent()){
            System.out.println(result.get().toString());
            throw new ServiceException("Cette promotion existe déjà",HttpStatus.CONFLICT);
        }
        if(e.getDateRentree()==null | e.getDateReponseLp()==null | e.getDateReponseLalp()==null)
            throw new ServiceException("Merci de remplir les champs obligatoires ",HttpStatus.BAD_REQUEST);
        if(!(e.getDateReponseLp().before(e.getDateReponseLalp()) & e.getDateReponseLalp().before(e.getDateRentree())))
            throw new ServiceException("Les dates fournies ne sont pas cohérentes.",HttpStatus.PRECONDITION_FAILED);

        //TODO : processus stage
            e.setFormationByCodeFormation(formaServ.getFormationById(e.getCodeFormation()));
        if(e.getNoEnseignant()!=null)
            e.setEnseignantByNoEnseignant(enDao.findById(e.getNoEnseignant()).get());

        return promoRepo.save(e);
    }

    @Transactional
    public Promotion accepterCandidats(String annee, String code) throws ServiceException {
        Promotion promo = this.findById(annee,code);
        List<Candidat>  cand = promo.getCandidats();
        int nbEtudiantRestants = promo.getNbMaxEtudiant() - promo.getEtudiants().size();
        //Candidat de la liste principale
        List<Candidat> candidatsLp = new ArrayList<>();
        for(Candidat c : cand)
            if(c.getListeSelection().equalsIgnoreCase("LP"))
                candidatsLp.add(c);
        if(candidatsLp.size()==0)
            throw new ServiceException("Il n'y a aucun candidat dans la liste principale de cette promotion "+promo.getSiglePromotion(),HttpStatus.NOT_FOUND);
        //Candidat ayant Confirmation_Candidat=oui
        List<Candidat> candidatsOui = new ArrayList<>();
        for(Candidat c : candidatsLp)
            if(c.getConfirmationCandidat()!=null)
            if(c.getConfirmationCandidat().equalsIgnoreCase("O"))
                candidatsOui.add(c);
        if(candidatsOui.size()==0)
            throw new ServiceException("Aucun candidat n'as confirmé sa candidature cette promotion "+promo.getSiglePromotion(),HttpStatus.NOT_FOUND);

            //Trie des candidats de la liste principale ayant dit oui
            //TODO: s'assurer que les Selection_ORDRE ne soit pas null dans la base de données
            //candidatsOui.stream().filter(e->e.getSelectionNoOrdre()!=null)


        //candidats sur la liste principale ayant un numéro d'ordre de selection non null
        if(candidatsOui.size()<=nbEtudiantRestants) {


            //Liste de candidats à migrer
            List<Candidat> aMigrer = new ArrayList<>();
            for (int i = 0; i< candidatsOui.size(); i++)
                aMigrer.add(candidatsOui.get(i));

            if (aMigrer.size() == 0)
                throw new ServiceException("Aucun candidats ne peut être accepté", HttpStatus.NOT_FOUND);
            List<Etudiant> etudiants = etuServ.createEtudiant(aMigrer);

            for (Candidat can : aMigrer) {
                candServ.deleteCandidatByNocandidat(can.getNoCandidat());

            }
        }
        else{
            for (Candidat c : candidatsOui)
                if (c.getSelectionNoOrdre() == null)
                    throw new ServiceException("Tous les candidats ayant dit oui doivent avoir un ordre de sélection car le nombre de place est insuffisant", HttpStatus.CONFLICT);
            else{
                    List<Candidat> sortedCandidats = new ArrayList<>();
                    try {
                        sortedCandidats = candidatsOui.stream()
                                .sorted(Comparator.comparing(Candidat::getSelectionNoOrdre))
                                .collect(Collectors.toList());
                    } catch (Exception e) {

                        throw new ServiceException("Impossible de trier les candidats en LP par ordre de sélection ", HttpStatus.CONFLICT);

                    }


                    //Liste de candidats à migrer
                    List<Candidat> aMigrer = new ArrayList<>();

                    if (nbEtudiantRestants <= 0)
                        throw new ServiceException("Le nombre maximal d'etudiant pour cette formation est atteint", HttpStatus.NOT_FOUND);
                    for (int i = 0; i < nbEtudiantRestants & i < sortedCandidats.size(); i++)
                        aMigrer.add(sortedCandidats.get(i));

                    if (aMigrer.size() == 0)
                        throw new ServiceException("Aucun candidats ne peut être accepté", HttpStatus.NOT_FOUND);
                    List<Etudiant> etudiants = etuServ.createEtudiant(aMigrer);
                    promo.setEtudiants(etudiants);

                    for (Candidat can : aMigrer) {
                        candServ.deleteCandidatByNocandidat(can.getNoCandidat());
                         promo.getCandidats().remove(can);


                    }
                }
        }
        em.detach(promo);
        Promotion p = promoRepo.findById(new PromotionPK(code,annee)).get();


        return p;
    }


}
