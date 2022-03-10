package fr.ubo.spibackend.repositories;

import fr.ubo.spibackend.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "etudiant")
public interface EtudiantEntityRepository extends JpaRepository<Etudiant, String> {
}