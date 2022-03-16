package fr.ubo.spibackend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.repositories.CandidatRepository;
import fr.ubo.spibackend.services.CandidatService;

@SpringBootTest
public class CandidatServiceTest {

	@InjectMocks 
	CandidatService candidatService;

	@Mock
	CandidatRepository mockRepository;

	public Candidat getCandidat() {
		Candidat candidat = new Candidat();
		candidat.setEmail("Amine@gmail.com");
		candidat.setNoCandidat("122M");
		return candidat;
	}

	@Test
	public void CreateCandidat() {
		Candidat candidat= this.getCandidat();
		Mockito.when(mockRepository.findByEmail(candidat.getEmail())).thenReturn(candidat);
		ServiceException thrown = assertThrows(
				ServiceException.class,
				() -> candidatService.saveCandidat(candidat),
				"Expected save() to throw, but it didn't"
				);
		assertTrue(thrown.getErrorMeassage().equals("Le candidat "+candidat.getNoCandidat()+" existe déja"));

	}

	@Test
	public void getAllCandidat() throws ServiceException {

		List<Candidat> listCandidats=new ArrayList<Candidat>();

		listCandidats.add(this.getCandidat());

		Mockito.when(mockRepository.findAll()).thenReturn(listCandidats);

		List<Candidat> listCandidatResult= candidatService.getAllCandidat();

		assertEquals(listCandidatResult,listCandidats);
	}
	
	@Test
	public void deleteCandidatByNocandidat() throws ServiceException {
		
		Candidat candidat= this.getCandidat();
		Mockito.when(mockRepository.findById(candidat.getNoCandidat())).thenReturn(Optional.of(candidat));
		 
		candidatService.deleteCandidatByNocandidat(candidat.getNoCandidat());
		Mockito.verify(mockRepository).deleteById(candidat.getNoCandidat());
	}
	
	@Test
	public void updateCandidat() throws ServiceException {
		Candidat candidat= this.getCandidat();
		Mockito.when(mockRepository.findById(candidat.getNoCandidat())).thenReturn(Optional.of(candidat));
		Candidat candidatResult = candidatService.updateCandidat(candidat);
		assertEquals(candidatResult,candidat);

	}
}
