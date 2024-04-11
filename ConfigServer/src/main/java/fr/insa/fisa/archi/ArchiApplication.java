package fr.insa.fisa.archi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ArchiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArchiApplication.class, args);
	}

}
