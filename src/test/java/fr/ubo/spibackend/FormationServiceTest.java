package fr.ubo.spibackend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.entities.Formation;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.repositories.FormationRepository;
import fr.ubo.spibackend.services.FormationService;

@SpringBootTest
public class FormationServiceTest {
	@InjectMocks 
	FormationService formationService;

	@Mock
	FormationRepository mockRepository;

	public Formation initFormation() {
		Formation formation = new Formation();
		formation.setCodeFormation("1223M");
		return formation;

	}
	@Test
	public void getAllFormations() throws ServiceException {
		List<Formation> listFormations=new ArrayList<Formation>();

		listFormations.add(this.initFormation());

		Mockito.when(mockRepository.findAll()).thenReturn(listFormations);

		List<Formation> listFormationResult= formationService.getAllFormations();

		assertEquals(listFormationResult,listFormations);
	}
	
	public void searchByCodeOrNom() throws ServiceException{
		
		Formation formation= this.initFormation();
		List<Formation> formations= new ArrayList<Formation>();
		formations.add(formation);
		
		Mockito.when(mockRepository.findByCodeFormationLikeOrNomFormationLike("1223M", "1223M")).thenReturn(formations);
		 
		List<Formation> result =formationService.searchByCodeOrNom("1223M");
		assertEquals(formations, result);
	}
	
	@Test
	public void getFormationById() throws ServiceException{
		Formation formation= this.initFormation();
		Mockito.when(mockRepository.findById(formation.getCodeFormation())).thenReturn(Optional.of(formation));
		 
		Formation formationResult =formationService.getFormationById(formation.getCodeFormation());
		assertEquals(formation, formationResult);
	}
	
	@Test
	public void createFormation() {
		Formation formation= this.initFormation();
		//Mockito.when(mockRepository.findByEmail(candidat.getEmail())).thenReturn(candidat);
		ServiceException thrown = assertThrows(
				ServiceException.class,
				() -> formationService.createFormation(formation),
				"Expected save() to throw, but it didn't"
				);
		assertTrue(thrown.getErrorMeassage().equals("Merci de remplir les champs obligatoires"));
	}
	
	@Test
	public void deleteFormation() throws ServiceException {
		Formation formation= this.initFormation();
		Mockito.when(mockRepository.findById("1223M")).thenReturn(Optional.of(formation));
		 
		formationService.deleteFormation("1223M");
		Mockito.verify(mockRepository).deleteById("1223M");
	}
}
