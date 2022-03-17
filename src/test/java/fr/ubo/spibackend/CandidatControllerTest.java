package fr.ubo.spibackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.ubo.spibackend.controllers.CandidatController;
import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.repositories.CandidatRepository;
import fr.ubo.spibackend.services.CandidatService;
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

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CandidatControllerTest
{

    @Autowired
    private MockMvc mvc;
    @Autowired
    CandidatRepository candRepo;
    @Autowired
    CandidatController candCtrl;
    @Autowired
    CandidatService candServ;
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void contextLoads() {
        assert candRepo !=null;
        assert mvc!=null;
        assert candCtrl !=null;
    }
    @Test
    public void saveCandidat() throws Exception {
        //return candServ.saveCandidat(candidat);
        Candidat c = candServ.getAllCandidat().get(0);
        c.setNoCandidat(null);
        c.setSelectionNoOrdre(BigInteger.valueOf(100000000l));
        c.setEmail(UUID.randomUUID().toString()+"@ubo.fr");

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(c );

        mvc.perform( MockMvcRequestBuilders
                        .post("/candidats")
                        .contentType(APPLICATION_JSON_UTF8)
                        .accept(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isOk());

        candRepo.delete(candRepo.findBySelectionNoOrdre(BigInteger.valueOf(100000000l)));
    }
    @Test
    public void saveCandidat_Conflict() throws Exception {
        //return candServ.saveCandidat(candidat);
        Candidat c = candServ.getAllCandidat().get(0);
        c.setNoCandidat(null);

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(c );

        mvc.perform( MockMvcRequestBuilders
                        .post("/candidats")
                        .contentType(APPLICATION_JSON_UTF8)
                        .accept(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isConflict());

        //candRepo.delete(candRepo.findBySelectionNoOrdre(BigInteger.valueOf(100000000l)));
    }
    @Test
    public void saveCandidat_BadReq() throws Exception {
        //return candServ.saveCandidat(candidat);
        Candidat c = new Candidat();

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(c );

        mvc.perform( MockMvcRequestBuilders
                        .post("/candidats")
                        .contentType(APPLICATION_JSON_UTF8)
                        .accept(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andExpect(status().isBadRequest());

        //candRepo.delete(candRepo.findBySelectionNoOrdre(BigInteger.valueOf(100000000l)));
    }
    @Test
    public void getAllCandidat() throws Exception {
        //return candServ.getAllCandidat();
        mvc.perform( MockMvcRequestBuilders
                        .get("/candidats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize((int) candRepo.count())));
    }

}
