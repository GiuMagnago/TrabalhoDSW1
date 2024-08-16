package br.ufscar.dc.dsw.SistemaVagas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.SistemaVagas.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.SistemaVagas.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;
import br.ufscar.dc.dsw.SistemaVagas.domain.Usuario;

@SpringBootApplication
public class SistemaVagasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaVagasApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO dao, IEmpresaDAO empresaDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {
			Usuario admin = new Usuario();
			admin.setNome("Administrador");
			admin.setEmail("admin@gmail.com");
			admin.setSenha(encoder.encode("admin"));
			admin.setPapel("ROLE_ADMIN");
			admin.setEnable(true);
			dao.save(admin);

			Empresa empresa = new Empresa();
			empresa.setNome("google");
			empresa.setEmail("google@gmail.com");
			empresa.setSenha(encoder.encode("123"));
			empresa.setCnpj("123");
			empresa.setDescricao("aaaa");
			empresa.setCidade("São Carlos");
			empresa.setPapel("ROLE_EMPRESA");
			empresa.setEnable(true);
			empresaDAO.save(empresa);
		};
	}
}
