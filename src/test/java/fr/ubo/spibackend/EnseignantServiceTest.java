package fr.ubo.spibackend;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.entities.Enseignant;
import fr.ubo.spibackend.repositories.CandidatRepository;
import fr.ubo.spibackend.repositories.EnseignantRepository;
import fr.ubo.spibackend.services.CandidatService;
import fr.ubo.spibackend.services.EnseignantService;

@SpringBootTest
public class EnseignantServiceTest {

	@InjectMocks 
	EnseignantService enseignantService;

	@Mock
	EnseignantRepository mockRepository;
	
	@Test
	public void getAllEnseignant() {
		List<Enseignant> listEnseignants=new ArrayList<Enseignant>();

		listEnseignants.add(new Enseignant());
		Mockito.when(mockRepository.findAll()).thenReturn(listEnseignants);
		assertEquals(listEnseignants,listEnseignants);

	}
}
