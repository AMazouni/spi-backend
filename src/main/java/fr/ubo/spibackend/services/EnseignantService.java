package fr.ubo.spibackend.services;

import fr.ubo.spibackend.entities.Candidat;
import fr.ubo.spibackend.entities.Enseignant;
import fr.ubo.spibackend.entities.Promotion;
import fr.ubo.spibackend.exception.ServiceException;
import fr.ubo.spibackend.repositories.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepo;

    public List<Enseignant> getAllEnseignant() throws ServiceException {
        List<Enseignant> enseignants= enseignantRepo.findAll().stream().sorted(Comparator.comparing(Enseignant::getNom)).collect(Collectors.toList());

        if(enseignants.size()==0) throw new ServiceException("Aucun enseignant trouvé", HttpStatus.NOT_FOUND);
        return enseignants;
    }
}
