package fr.ubo.spibackend.controllers;

import fr.ubo.spibackend.entities.DomaineViews;
import fr.ubo.spibackend.repositories.vues.ViewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RequestMapping("domaine")
@RestController
@CrossOrigin(origins="*")
public class ViewsController {

    @Autowired
    ViewsRepository vr;
        @GetMapping("universite")
    public ArrayList<DomaineViews> findAllUniversite() {
        return vr.findAllUniversite();
    }
        @GetMapping("typeens")
    public ArrayList<DomaineViews> findAllTypeEnseignant() {
        return vr.findAllTypeEnseignant();
    }
@GetMapping("sexe")
    public ArrayList<DomaineViews> findAllSexe() {
        return vr.findAllSexe();
    }
@GetMapping("stage")
    public ArrayList<DomaineViews> findAllProcessStage() {
        return vr.findAllProcessStage();
    }
@GetMapping("positionnement")
    public ArrayList<DomaineViews> findAllPosition() {
        return vr.findAllPosition();
    }
    @GetMapping("pays")
    public ArrayList<DomaineViews> findAllPays() {
        return vr.findAllPays();
    }
    @GetMapping("ouinon")
    public ArrayList<DomaineViews> findAllOuiNon() {
        return vr.findAllOuiNon();
    }
    @GetMapping("listeselection")
    public ArrayList<DomaineViews> findAllListSelec() {
        return vr.findAllListSelec();
    }
    @GetMapping("etatevaluation")
    public ArrayList<DomaineViews> findAllEtatEvaluation() {
        return vr.findAllEtatEvaluation();
    }
    @GetMapping("diplome")
    public ArrayList<DomaineViews> findAllDiplome() {
        return vr.findAllDiplome();
    }
    @GetMapping("salle")
    public ArrayList<DomaineViews> findAllSalle() {
        return vr.findAllSalle();
    }
}
