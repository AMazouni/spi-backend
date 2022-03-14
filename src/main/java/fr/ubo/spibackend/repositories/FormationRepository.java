package fr.ubo.spibackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.ubo.spibackend.entities.Formation;

@Repository
public interface FormationRepository extends JpaRepository<Formation, String> {

	Formation findByCodeFormation(String codeFormation);

	List<Formation> findByNomFormation(String nomFormation);

	List<Formation> findByCodeFormationLikeOrNomFormationLike(String nomFormation, String codeFormation);

}
