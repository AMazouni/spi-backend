package fr.ubo.spibackend.dao;

import fr.ubo.spibackend.entities.CandidatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "candidat")
public interface CandidatEntityRepository extends JpaRepository<CandidatEntity, String>, JpaSpecificationExecutor<CandidatEntity> {
}