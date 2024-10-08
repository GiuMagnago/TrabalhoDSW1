package br.ufscar.dc.dsw.SistemaVagas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.SistemaVagas.domain.Profissional;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IProfissionalService;
import jakarta.validation.Valid;

@RestController
public class ProfissionalControllerREST {

	@Autowired
	private IProfissionalService service;

	//listar todos
    @GetMapping(path = "/api/profissionais")
	public ResponseEntity<List<Profissional>> lista() {
		List<Profissional> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

    //listar por id
	@GetMapping(path = "/api/profissionais/{id}")
	public ResponseEntity<Profissional> lista(@PathVariable("id") long id) {
		Profissional profissional = service.buscarPorId(id);
		if (profissional == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(profissional);
	}
	
    //criar profissional
	@PostMapping(path = "/api/profissionais")
	@ResponseBody
	public ResponseEntity<Profissional> cria(@Valid @RequestBody Profissional profissional, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return ResponseEntity.badRequest().build();
		}
		profissional.setPapel("ROLE_PROFISSIONAL");
		profissional.setEnable(true);
		service.salvar(profissional);
		return ResponseEntity.ok(profissional);
	}

    //atualizar profissional via id
	@PutMapping(path = "/api/profissionais/{id}")
	@ResponseBody
	public ResponseEntity<Profissional> atualiza(@PathVariable("id") long id, @Valid @RequestBody Profissional profissional, BindingResult result) {
		if (result.getErrorCount() > 2 || result.getFieldValue("cpf") == null || result.getFieldValue("email") == null) {
			return ResponseEntity.badRequest().build();
		}
		if (service.buscarPorId(id) == null) {
			return ResponseEntity.notFound().build();
		}
		profissional.setPapel("ROLE_PROFISSIONAL");
		profissional.setEnable(true);
		service.salvar(profissional);
		return ResponseEntity.ok(profissional);
	}

	//deletar profissional
    @DeleteMapping(path = "/api/profissionais/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {
		if (service.buscarPorId(id) == null) {
			return ResponseEntity.notFound().build();
		}
		service.excluir(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}