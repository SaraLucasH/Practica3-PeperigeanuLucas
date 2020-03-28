package es.urjc.etsii;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	/* Redefinición de método para ejecutar acciones sobre la BD. */
    @Override
    @Transactional
    public void run(String... strings) throws Exception {}
}


