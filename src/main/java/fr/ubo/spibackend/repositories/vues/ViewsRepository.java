package fr.ubo.spibackend.repositories.vues;

import fr.ubo.spibackend.entities.VAbbr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ViewsRepository {
     @Autowired
    EntityManager em;

    public ArrayList<VAbbr> findAllUniversite(){
        ArrayList<VAbbr> universite = (ArrayList<VAbbr>) em.createNativeQuery("Select * from DOSI.V_UNIVERSITE",VAbbr.class).getResultList();
      return universite;
    }
    public ArrayList<VAbbr> findAllTypeEnseignant(){
        ArrayList<VAbbr> types = (ArrayList<VAbbr>) em.createNativeQuery("Select * from DOSI.V_TYPE_ENSEIGNANT",VAbbr.class).getResultList();
        return types;
    }
    public ArrayList<VAbbr> findAllSexe(){
        ArrayList<VAbbr> sexes = (ArrayList<VAbbr>) em.createNativeQuery("Select * from DOSI.V_SEXE",VAbbr.class).getResultList();
        return sexes;
    }
public ArrayList<VAbbr> findAllProcessStage(){
    ArrayList<VAbbr> res = (ArrayList<VAbbr>) em.createNativeQuery("Select * from DOSI.V_PROCESSUS_STAGE",VAbbr.class).getResultList();
    return res;
}   
public ArrayList<VAbbr> findAllPosition(){
    ArrayList<VAbbr> res = (ArrayList<VAbbr>) em.createNativeQuery("Select * from DOSI.V_POSITIONNEMENT",VAbbr.class).getResultList();
    return res;
}
public ArrayList<VAbbr> findAllPays(){
    ArrayList<VAbbr> res = (ArrayList<VAbbr>) em.createNativeQuery("Select * from DOSI.V_PAYS",VAbbr.class).getResultList();
    return res;
}
public ArrayList<VAbbr> findAllOuiNon(){
    ArrayList<VAbbr> res = (ArrayList<VAbbr>) em.createNativeQuery("Select * from DOSI.V_OUI_NON",VAbbr.class).getResultList();
    return res;
}
public ArrayList<VAbbr> findAllListSelec(){
    ArrayList<VAbbr> res = (ArrayList<VAbbr>) em.createNativeQuery("Select * from DOSI.V_LISTE_SELECTION",VAbbr.class).getResultList();
    return res;
}
public ArrayList<VAbbr> findAllEtatEvaluation(){
    ArrayList<VAbbr> res = (ArrayList<VAbbr>) em.createNativeQuery("Select * from DOSI.V_ETAT_SELECTION",VAbbr.class).getResultList();
    return res;
}
public ArrayList<VAbbr> findAllDiplome(){
    ArrayList<VAbbr> res = (ArrayList<VAbbr>) em.createNativeQuery("Select * from DOSI.V_DIPLOME",VAbbr.class).getResultList();
    return res;
}


}
