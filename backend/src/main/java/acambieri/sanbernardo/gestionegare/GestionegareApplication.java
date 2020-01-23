package acambieri.sanbernardo.gestionegare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("acambieri.sanbernardo.gestionegare")
public class GestionegareApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Class[] { GestionegareApplication.class/*,DbUpdater.class*/}, args);
	}
}
