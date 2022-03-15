package fr.ubo.spibackend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.entities.Promotion;
import fr.ubo.spibackend.entities.PromotionPK;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.repositories.PromotionRepository;
import fr.ubo.spibackend.services.PromotionService;

//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
@SpringBootTest
//@MockitoSettings(strictness = Strictness.LENIENT)
//@RunWith(MockitoJUnitRunner.class)

public class PromotionTests {
	
	 @InjectMocks 
	 PromotionService promotionService;
	 
	@Mock
	PromotionRepository mockRepository;
	
	public Promotion getPromo() {
		Date date = new Date();
		Promotion promotion = new Promotion();
		promotion.setCodeFormation("12234");
		promotion.setAnneeUniversitaire("2021-2022");
		List <Candidat> candidats= new ArrayList<>();
		candidats.add(new Candidat());
		promotion.setCandidats(candidats);
		
		return promotion;
	}

	@Test
 	public void findListPromotion() throws ServiceException  {
 		
 		List<Promotion> listPromotions=new ArrayList<Promotion>();
 		
 		listPromotions.add(this.getPromo());
 		
 		Mockito.when(mockRepository.findAll(Sort.by(Sort.Direction.ASC, "anneeUniversitaire"))).thenReturn(listPromotions);
 		
 		List<Promotion> listPromotionsResult= promotionService.findAll();
 		
 		assertEquals(listPromotionsResult,listPromotions);
 		//verify(mockRepository, times(1));
 	
	}
	
	@Test
	public void findById() throws ServiceException {
		PromotionPK pk= new PromotionPK("2021-2022","promoCode");
		
		List <Candidat> candidats= new ArrayList<>();
		candidats.add(new Candidat());
		
		
		Promotion promotion = new Promotion();
		promotion.setAnneeUniversitaire("2021-2022");
		promotion.setCodeFormation("promoCode");
		promotion.setCandidats(candidats);
 		Mockito.when(mockRepository.findById(pk)).thenReturn(Optional.of(promotion));
 		
		Promotion promotionResult = promotionService.findById("promoCode","2021-2022");
		assertEquals(promotionResult,promotion);

	}
	
	@Test
	public void findByCodeFormation() throws ServiceException {
 		List<Promotion> listPromotions=new ArrayList<Promotion>();
 		listPromotions.add(this.getPromo());
 		
 		Mockito.when(mockRepository.findByCodeFormationOrderByAnneeUniversitaireDesc("12234")).thenReturn( (ArrayList<Promotion>) listPromotions);
 		
		ArrayList<Promotion> promotionResults = promotionService.findByCodeFormation("12234");
		assertEquals(promotionResults,listPromotions);

	}
	
	@Test
	public void savePromotion() throws ServiceException {
		ServiceException s= new ServiceException();
		Promotion promotion = this.getPromo();
		PromotionPK pk= new PromotionPK(promotion.getCodeFormation(),promotion.getAnneeUniversitaire());
		Mockito.when(mockRepository.findById(pk)).thenReturn(Optional.of(promotion));
		
		ServiceException thrown = assertThrows(
				ServiceException.class,
	            () -> promotionService.save(promotion),
	            "Expected save() to throw, but it didn't"
	     );
		assertTrue(thrown.getErrorMeassage().equals("Le nb Max d'étudiant doit être spécifié "));
		
	}
	
	 @Test
	 void accepterCandidats() {
		 
	 }
	

	
}
