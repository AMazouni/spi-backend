package fr.ubo.spibackend.repositories.vues;

import fr.ubo.spibackend.entities.DomaineViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;

@Repository
public class ViewsRepository {
     @Autowired
    EntityManager em;

    public ArrayList<DomaineViews> findAllUniversite(){
        ArrayList<DomaineViews> universite = (ArrayList<DomaineViews>) em.createNativeQuery("Select * from DOSI.V_UNIVERSITE", DomaineViews.class).getResultList();
      return universite;
    }
    public ArrayList<DomaineViews> findAllTypeEnseignant(){
        ArrayList<DomaineViews> types = (ArrayList<DomaineViews>) em.createNativeQuery("Select * from DOSI.V_TYPE_ENSEIGNANT", DomaineViews.class).getResultList();
        return types;
    }
    public ArrayList<DomaineViews> findAllSexe(){
        ArrayList<DomaineViews> sexes = (ArrayList<DomaineViews>) em.createNativeQuery("Select * from DOSI.V_SEXE", DomaineViews.class).getResultList();
        return sexes;
    }
public ArrayList<DomaineViews> findAllProcessStage(){
    ArrayList<DomaineViews> res = (ArrayList<DomaineViews>) em.createNativeQuery("Select * from DOSI.V_PROCESSUS_STAGE", DomaineViews.class).getResultList();
    return res;
}   
public ArrayList<DomaineViews> findAllPosition(){
    ArrayList<DomaineViews> res = (ArrayList<DomaineViews>) em.createNativeQuery("Select * from DOSI.V_POSITIONNEMENT", DomaineViews.class).getResultList();
    return res;
}
public ArrayList<DomaineViews> findAllPays(){
    ArrayList<DomaineViews> res = (ArrayList<DomaineViews>) em.createNativeQuery("Select * from DOSI.V_PAYS", DomaineViews.class).getResultList();
    return res;
}
public ArrayList<DomaineViews> findAllOuiNon(){
    ArrayList<DomaineViews> res = (ArrayList<DomaineViews>) em.createNativeQuery("Select * from DOSI.V_OUI_NON", DomaineViews.class).getResultList();
    return res;
}
public ArrayList<DomaineViews> findAllListSelec(){
    ArrayList<DomaineViews> res = (ArrayList<DomaineViews>) em.createNativeQuery("Select * from DOSI.V_LISTE_SELECTION", DomaineViews.class).getResultList();
    return res;
}
public ArrayList<DomaineViews> findAllEtatEvaluation(){
    ArrayList<DomaineViews> res = (ArrayList<DomaineViews>) em.createNativeQuery("Select * from DOSI.V_ETAT_SELECTION", DomaineViews.class).getResultList();
    return res;
}
public ArrayList<DomaineViews> findAllDiplome(){
    ArrayList<DomaineViews> res = (ArrayList<DomaineViews>) em.createNativeQuery("Select * from DOSI.V_DIPLOME", DomaineViews.class).getResultList();
    return res;
}

    public ArrayList<DomaineViews> findAllSalle(){
        ArrayList<DomaineViews> res = (ArrayList<DomaineViews>) em.createNativeQuery("Select * from DOSI.V_SALLE", DomaineViews.class).getResultList();
        return res;
    }

}
