package br.ufscar.dc.dsw.SistemaVagas;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.SistemaVagas.dao.ICandidaturaDAO;
import br.ufscar.dc.dsw.SistemaVagas.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.SistemaVagas.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.SistemaVagas.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.SistemaVagas.dao.IVagaDAO;
import br.ufscar.dc.dsw.SistemaVagas.domain.Candidatura;
import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;
import br.ufscar.dc.dsw.SistemaVagas.domain.Profissional;
import br.ufscar.dc.dsw.SistemaVagas.domain.Usuario;
import br.ufscar.dc.dsw.SistemaVagas.domain.Vaga;

@SpringBootApplication
public class SistemaVagasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaVagasApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO usuarioDAO, IEmpresaDAO empresaDAO, IProfissionalDAO profissionalDAO, IVagaDAO vagaDAO, ICandidaturaDAO candidaturaDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			Usuario admin = new Usuario();
			admin.setNome("Administrador");
			admin.setEmail("admin@gmail.com");
			admin.setSenha(encoder.encode("admin"));
			admin.setPapel("ROLE_ADMIN");
			admin.setEnable(true);
			usuarioDAO.save(admin);



			Empresa empresa = new Empresa();
			empresa.setNome("google");
			empresa.setEmail("google@gmail.com");
			empresa.setSenha(encoder.encode("123"));
			empresa.setPapel("ROLE_EMPRESA");
			empresa.setCnpj("123");
			empresa.setDescricao("aaaa");
			empresa.setCidade("São Carlos");
			empresa.setEnable(true);
			empresaDAO.save(empresa);



			Profissional profissional1 = new Profissional();
			profissional1.setNome("joao");
			profissional1.setEmail("joao@gmail.com");
			profissional1.setSenha(encoder.encode("123"));
			profissional1.setPapel("ROLE_PROFISSIONAL");
			profissional1.setCpf("12345678900");
			profissional1.setTelefone("1612345678");
			profissional1.setSexo("Masculino");
			profissional1.setDataNasc(new Date(dateFormat.parse("01-01-2001").getTime()));
			profissionalDAO.save(profissional1);

			Profissional profissional2 = new Profissional();
			profissional2.setNome("giuseppe");
			profissional2.setEmail("giuseppecm2014@gmail.com");
			profissional2.setSenha(encoder.encode("123"));
			profissional2.setPapel("ROLE_PROFISSIONAL");
			profissional2.setCpf("12345678901");
			profissional2.setTelefone("1612345678");
			profissional2.setSexo("Masculino");
			profissional2.setDataNasc(new Date(dateFormat.parse("01-01-2001").getTime()));
			profissionalDAO.save(profissional2);



			Vaga vaga1 = new Vaga();
			vaga1.setCnpj_empresa("123");
			vaga1.setRemuneracao(3000);
			vaga1.setDescricao("...");
			vaga1.setDataLimite(new Date(dateFormat.parse("30-08-2024").getTime()));
			vaga1.setEmpresa(empresa);
			vagaDAO.save(vaga1);

			Vaga vaga2 = new Vaga();
			vaga2.setCnpj_empresa("123");
			vaga2.setRemuneracao(5000);
			vaga2.setDescricao("...");
			vaga2.setDataLimite(new Date(dateFormat.parse("30-07-2024").getTime()));
			vaga2.setEmpresa(empresa);
			vagaDAO.save(vaga2);

			Vaga vaga3 = new Vaga();
			vaga3.setCnpj_empresa("123");
			vaga3.setRemuneracao(7000);
			vaga3.setDescricao("...");
			vaga3.setDataLimite(new Date(dateFormat.parse("30-12-2024").getTime()));
			vaga3.setEmpresa(empresa);
			vagaDAO.save(vaga3);


			Candidatura candidatura1 = new Candidatura();
			candidatura1.setProfissional(profissional1);
			candidatura1.setVaga(vaga2);
			candidatura1.setCurriculoPath("...");
			candidatura1.setStatus_candidatura("ABERTO");
			candidatura1.setCurriculoPath("src/main/resources/uploads/curriculo_3.pdf");
			candidaturaDAO.save(candidatura1);

			Candidatura candidatura2 = new Candidatura();
			candidatura2.setProfissional(profissional2);
			candidatura2.setVaga(vaga1);
			candidatura2.setCurriculoPath("...");
			candidatura2.setStatus_candidatura("ABERTO");
			candidatura2.setCurriculoPath("src/main/resources/uploads/curriculo_4.pdf");
			candidaturaDAO.save(candidatura2);

			Candidatura candidatura3 = new Candidatura();
			candidatura3.setProfissional(profissional2);
			candidatura3.setVaga(vaga2);
			candidatura3.setCurriculoPath("...");
			candidatura3.setStatus_candidatura("ABERTO");
			candidatura3.setCurriculoPath("src/main/resources/uploads/curriculo_4.pdf");
			candidaturaDAO.save(candidatura3);
		};
	}
}
