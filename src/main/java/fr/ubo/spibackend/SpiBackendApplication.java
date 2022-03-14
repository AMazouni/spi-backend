package fr.ubo.spibackend;

import fr.ubo.spibackend.repositories.vues.ViewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class SpiBackendApplication {

	@Autowired
	ViewsRepository vr;

	public static void main(String[] args) {
		SpringApplication.run(SpiBackendApplication.class, args);

	}


}
