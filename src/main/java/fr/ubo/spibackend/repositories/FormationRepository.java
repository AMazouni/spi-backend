package fr.ubo.spibackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ubo.spibackend.entities.Formation;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(path = "formation")
public interface FormationRepository extends JpaRepository<Formation, String> {

    Formation findByCodeFormation(String codeFormation);

    List<Formation> findByNomFormation(String nomFormation);

}
