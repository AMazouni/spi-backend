package fr.ubo.spibackend.controllers;


import fr.ubo.spibackend.dto.PromotionDTO;
import fr.ubo.spibackend.dto.converter.PromotionMapper;
import fr.ubo.spibackend.entities.Promotion;
import fr.ubo.spibackend.exception.RestErrorMessage;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.services.PromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("promotions")
public class PromotionController {

    @Autowired
    PromotionService promoServ;
    private PromotionMapper mapper
            = Mappers.getMapper(PromotionMapper.class);

    @GetMapping
    public ResponseEntity findAll(){

        try {
            return new ResponseEntity(promoServ.findAll(), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getMessage()), e.getHttpStatus());
        }catch (Exception e ){ e.printStackTrace();
            return new ResponseEntity(new RestErrorMessage("erreur serveur 500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{code}/{annee}")
    public ResponseEntity findById(@PathVariable String annee, @PathVariable String code) {
        try {
            PromotionDTO promo = mapper.map(promoServ.findById(annee, code));
            return new ResponseEntity<PromotionDTO>(promo, HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }catch (Exception e ){ e.printStackTrace();
            return new ResponseEntity(new RestErrorMessage("erreur serveur 500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{code}")
    public ResponseEntity findByCodeFormation(@PathVariable String code)  {
        try {
            return new ResponseEntity<ArrayList<Promotion>>(promoServ.findByCodeFormation(code), HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }catch (Exception e ){ e.printStackTrace();
            return new ResponseEntity(new RestErrorMessage("erreur serveur 500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")

    public ResponseEntity save(@RequestBody Promotion e) throws ServiceException {

        try {
            return new ResponseEntity( promoServ.save(e), HttpStatus.OK);
        }catch(ServiceException ex){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(ex.getErrorMeassage()), ex.getHttpStatus());
        }catch (Exception ex){ ex.printStackTrace();
            return new ResponseEntity(new RestErrorMessage("erreur serveur 500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/{code}/{annee}/accept")
    public ResponseEntity accepterCandidats(@PathVariable String annee, @PathVariable String code) throws ServiceException {

        try {
            return new ResponseEntity(promoServ.accepterCandidats(annee, code), HttpStatus.OK);
        }catch(ServiceException e){
            return new ResponseEntity<RestErrorMessage>(new RestErrorMessage(e.getErrorMeassage()), e.getHttpStatus());
        }catch (Exception e ){ e.printStackTrace();
            return new ResponseEntity(new RestErrorMessage("erreur serveur 500"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
