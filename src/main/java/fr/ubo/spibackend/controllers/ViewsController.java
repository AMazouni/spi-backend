package fr.ubo.spibackend.controllers;

import fr.ubo.spibackend.entities.VAbbr;
import fr.ubo.spibackend.repositories.vues.ViewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
@RequestMapping("/views")
public class ViewsController {

    @Autowired
    ViewsRepository vr;
     @GetMapping("universite")
    public ArrayList<VAbbr> findAllUniversite() {
        return vr.findAllUniversite();
    }
@GetMapping("typeens")
    public ArrayList<VAbbr> findAllTypeEnseignant() {
        return vr.findAllTypeEnseignant();
    }
@GetMapping("sexe")
    public ArrayList<VAbbr> findAllSexe() {
        return vr.findAllSexe();
    }
@GetMapping("stage")
    public ArrayList<VAbbr> findAllProcessStage() {
        return vr.findAllProcessStage();
    }
@GetMapping("positionnement")
    public ArrayList<VAbbr> findAllPosition() {
        return vr.findAllPosition();
    }
    @GetMapping("pays")
    public ArrayList<VAbbr> findAllPays() {
        return vr.findAllPays();
    }
    @GetMapping("ouinon")
    public ArrayList<VAbbr> findAllOuiNon() {
        return vr.findAllOuiNon();
    }
    @GetMapping("listeselection")
    public ArrayList<VAbbr> findAllListSelec() {
        return vr.findAllListSelec();
    }
    @GetMapping("etatevaluation")
    public ArrayList<VAbbr> findAllEtatEvaluation() {
        return vr.findAllEtatEvaluation();
    }
    @GetMapping("diplome")
    public ArrayList<VAbbr> findAllDiplome() {
        return vr.findAllDiplome();
    }
}
