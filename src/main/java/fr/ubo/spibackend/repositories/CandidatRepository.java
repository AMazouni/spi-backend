package fr.ubo.spibackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import fr.ubo.spibackend.entities.Candidat;

import java.util.List;

@Repository
@RepositoryRestResource(path = "candidat")
public interface CandidatRepository extends JpaRepository<Candidat, String> {
    List<Candidat> findByCodeFormation(String codeFormation);
    List<Candidat> findByUniversiteOrigine(String universiteOrigine);
    List<Candidat> findByCodeFormationAndAnneeUniversitaire(String codeFormation, String anneeUniversitaire);
    List<Candidat> findByListeSelection(String listeSelecton);
}