package fr.ubo.spibackend.dao;

import fr.ubo.spibackend.entities.PromotionEntity;
import fr.ubo.spibackend.entities.PromotionEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "promotions")
public interface PromotionEntityRepository extends JpaRepository<PromotionEntity, PromotionEntityPK> {
}