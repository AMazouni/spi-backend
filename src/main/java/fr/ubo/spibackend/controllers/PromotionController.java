package fr.ubo.spibackend.controllers;


import fr.ubo.spibackend.entities.Promotion;
import fr.ubo.spibackend.exception.RestErrorMessage;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("promotion")
public class PromotionController {

    @Autowired
    PromotionService promoServ;

    @GetMapping("/")
    public ResponseEntity findAll(){

        try {
            return new ResponseEntity(promoServ.findAll(), HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }catch (Exception e ){
            return new ResponseEntity(new RestErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{code}/{annee}")
    public ResponseEntity findById(@PathVariable String annee, @PathVariable String code) {
        try {
            return new ResponseEntity<Promotion>(promoServ.findById(annee, code), HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }catch (Exception e ){
            return new ResponseEntity(new RestErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{code}")
    public ResponseEntity findByCode(@PathVariable String code) {
        try {
            return new ResponseEntity(promoServ.findByCodeFormationOrderByAnneeUniversitaireDes(code), HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }catch (Exception e ){
            return new ResponseEntity(new RestErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
