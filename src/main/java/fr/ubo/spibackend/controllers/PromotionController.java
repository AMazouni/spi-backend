package fr.ubo.spibackend.controllers;


import fr.ubo.spibackend.repositories.PromotionEntityRepository;
import fr.ubo.spibackend.entities.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("promo")
public class PromotionController {

    @Autowired
    PromotionEntityRepository promo;

    @GetMapping("/")
    public List<Promotion> findAll(){
        return promo.findAll();
    }


}
