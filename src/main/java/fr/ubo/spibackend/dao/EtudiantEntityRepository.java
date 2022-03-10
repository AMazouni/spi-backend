package fr.ubo.spibackend.dao;

import fr.ubo.spibackend.entities.EtudiantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "etudiant")
public interface EtudiantEntityRepository extends JpaRepository<EtudiantEntity, String>, JpaSpecificationExecutor<EtudiantEntity> {
}