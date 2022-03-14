package fr.ubo.spibackend.controllers;


import fr.ubo.spibackend.entities.Promotion;
import fr.ubo.spibackend.exception.RestErrorMessage;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("promotion")
public class PromotionController {

    @Autowired
    PromotionService promoServ;

    @GetMapping("/")
    public ResponseEntity findAll(){

        try {
            return new ResponseEntity(promoServ.findAll(), HttpStatus.OK);
        }catch (Exception e ){
            return new ResponseEntity(new RestErrorMessage(e.toString()+" Backend error"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ServiceException e) {
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getMessage()), e.getHttpStatus());
        }
    }

    @GetMapping("/{code}/{annee}")
    public ResponseEntity findById(@PathVariable String annee, @PathVariable String code) {
        try {
            return new ResponseEntity<Promotion>(promoServ.findById(annee, code), HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }catch (Exception e ){
            return new ResponseEntity(new RestErrorMessage(e.toString()+" Backend error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{code}")
    public ResponseEntity findByCodeFormation(@PathVariable String code)  {
        try {
            return new ResponseEntity<ArrayList<Promotion>>(promoServ.findByCodeFormation(code), HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }catch (Exception e ){
            return new ResponseEntity(new RestErrorMessage(e.toString()+" Backend error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
