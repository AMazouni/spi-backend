package fr.ubo.spibackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.ubo.spibackend.entities.Formation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation, String> {

    Formation findByCodeFormation(String codeFormation);

    List<Formation> findByNomFormation(String nomFormation);

}
