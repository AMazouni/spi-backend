package fr.ubo.spibackend;

import fr.ubo.spibackend.controllers.PromotionController;
import fr.ubo.spibackend.repositories.PromotionRepository;
import fr.ubo.spibackend.services.PromotionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PromotionTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    PromotionService promoSer;
    @Autowired
    PromotionController promoCont;
    @Autowired
    PromotionRepository promoRep;
    @Test
    void contextLoads() {
        assert promoSer!=null;
        assert mvc!=null;
        assert promoCont!=null;
    }
     @Test
    void findAll() throws Exception  {
//        return promoSer.findAll();
         mvc.perform( MockMvcRequestBuilders
                         .get("/promotion/")
                         .contentType(MediaType.APPLICATION_JSON)
                         .accept(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize((int) promoRep.count())));
    }
@Test
    public void findById_Ok() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                        .get("/promotion/M2DOSI/2014-2015")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void findById_NOTOk() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                        .get("/promotion/ISI/2023-2024")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
@Test
    public void findByCodeFormation_Ok() throws Exception {
//        return promoSer.findByCodeFormation(code);
    mvc.perform( MockMvcRequestBuilders
                    .get("/promotion/M2DOSI")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    @Test
    public void findByCodeFormation_NOTOk() throws Exception {
//        return promoSer.findByCodeFormation(code);
        mvc.perform( MockMvcRequestBuilders
                        .get("/promotion/BADCODE")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
//
//    public Promotion save(Promotion e) throws ServiceException {
//        return promoSer.save(e);
//    }
//
//    @Transactional
//    public Promotion tenirCandidats(String annee, String code) throws ServiceException {
//        return promoSer.tenirCandidats(annee, code);
//    }
}
