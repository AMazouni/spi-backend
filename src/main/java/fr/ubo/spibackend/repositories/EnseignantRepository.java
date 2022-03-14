package fr.ubo.spibackend.repositories;

import fr.ubo.spibackend.entities.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepository extends JpaRepository<Enseignant, Short> {
}
