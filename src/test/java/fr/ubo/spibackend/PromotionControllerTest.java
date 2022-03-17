package fr.ubo.spibackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.ubo.spibackend.controllers.PromotionController;
import fr.ubo.spibackend.entities.Promotion;
import fr.ubo.spibackend.entities.PromotionPK;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.repositories.PromotionRepository;
import fr.ubo.spibackend.services.FormationService;
import fr.ubo.spibackend.services.PromotionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.sql.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
//@MockitoSettings(strictness = Strictness.LENIENT)
//@RunWith(MockitoJUnitRunner.class)

public class PromotionControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    PromotionService promoSer;
    @Autowired
    PromotionController promoCont;
    @Autowired
    PromotionRepository promoRep;
    @Autowired
    FormationService formaSer;
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    String codeFormation="M2DOSI";
    String anneeUniv= "3003-3004";



    @Test
    public void contextLoads() throws Exception {
        assert promoSer!=null;
        assert mvc!=null;
        assert promoCont!=null;


    }

    public Promotion promoTest() throws Exception {
        Promotion p = new Promotion();
        p.setCodeFormation(this.codeFormation);
        p.setAnneeUniversitaire(this.anneeUniv);
        p.setDateReponseLp(new Date(3000,5,5));
        p.setDateReponseLalp(new Date(3000,6,5));
        p.setDateRentree(new Date(3000,7,5));
        p.setNbMaxEtudiant((byte)22);
        return p;
    }
    @Test
    public void findAll() throws Exception  {
//        return promoSer.findAll();
         mvc.perform( MockMvcRequestBuilders
                         .get("/promotions/")
                         .contentType(MediaType.APPLICATION_JSON)
                         .accept(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize((int) promoRep.count())));
    }
@Test
    public void findById_Ok() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                        .get("/promotions/M2DOSI/2014-2015")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void findById_NOTOk() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                        .get("/promotions/ISI/2023-2024")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
@Test
    public void findByCodeFormation_Ok() throws Exception {
//        return promoSer.findByCodeFormation(code);
    mvc.perform( MockMvcRequestBuilders
                    .get("/promotions/M2DOSI")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    @Test
    public void findByCodeFormation_NOTOk() throws Exception {
//        return promoSer.findByCodeFormation(code);
        mvc.perform( MockMvcRequestBuilders
                        .get("/promotions/BADCODE")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void save_Ok() throws Exception {
        Promotion p = promoTest();
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(p);

        mvc.perform( MockMvcRequestBuilders
                        .post("/promotions/")
                        .contentType(APPLICATION_JSON_UTF8)
                        .accept(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk());
      p = promoSer.findById(this.anneeUniv,this.codeFormation);
        promoRep.delete(p);
    }

//
//    public Promotion tenirCandidats(String annee, String code) throws ServiceException {
//        return promoSer.tenirCandidats(annee, code);
//    }



}
