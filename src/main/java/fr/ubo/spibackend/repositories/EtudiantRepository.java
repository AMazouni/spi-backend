package fr.ubo.spibackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.ubo.spibackend.entities.Etudiant;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
}