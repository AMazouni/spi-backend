package fr.ubo.spibackend.repositories;

import fr.ubo.spibackend.entities.Promotion;
import fr.ubo.spibackend.entities.PromotionPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@RepositoryRestResource(path = "promotions")
public interface PromotionRepository extends JpaRepository<Promotion, PromotionPK> {

    public ArrayList<Promotion> findByCodeFormationOrderByAnneeUniversitaireDesc(String code);
}