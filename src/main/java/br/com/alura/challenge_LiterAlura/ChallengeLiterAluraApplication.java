package br.com.alura.challenge_LiterAlura;

import br.com.alura.challenge_LiterAlura.principal.Principal;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiterAluraApplication implements CommandLineRunner {

    @Autowired
    private Principal principal;

	public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );
        SpringApplication.run(ChallengeLiterAluraApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        principal.exibeMenu();
    }
}
