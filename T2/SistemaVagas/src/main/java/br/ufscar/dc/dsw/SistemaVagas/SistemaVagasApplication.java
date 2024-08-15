package br.ufscar.dc.dsw.SistemaVagas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.SistemaVagas.dao.IAdministradorDAO;
import br.ufscar.dc.dsw.SistemaVagas.domain.Administrador;

@SpringBootApplication
public class SistemaVagasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaVagasApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IAdministradorDAO dao, BCryptPasswordEncoder encoder) {
		return (args) -> {
			Administrador admin = new Administrador();
			admin.setEmail("admin@gmail.com");
			admin.setSenha(encoder.encode("admin"));
			admin.setPapel("ROLE_ADMIN");
			dao.save(admin);
		};
	}
}
