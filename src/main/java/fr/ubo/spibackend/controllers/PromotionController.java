package fr.ubo.spibackend.controllers;


import fr.ubo.spibackend.entities.PromotionPK;
import fr.ubo.spibackend.repositories.PromotionRepository;
import fr.ubo.spibackend.entities.Promotion;
import fr.ubo.spibackend.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("promotion")
public class PromotionController {

    @Autowired
    PromotionService promoServ;

    @GetMapping("/")
    public List<Promotion> findAll(){
        return promoServ.findAll();
    }

    @GetMapping("/{annee}/{code}")
    public Promotion findByAnneeUniversitaireAndCodeFormation(@PathVariable String annee, @PathVariable String code) {
        return promoServ.findByAnneeUniversitaireAndCodeFormation(annee, code);
    }
}
