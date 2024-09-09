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

import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IEmpresaService;
import jakarta.validation.Valid;

@RestController
public class EmpresaRestController {

	@Autowired
	private IEmpresaService service;
	
	// retorna todas
    @GetMapping(path = "/api/empresas")
	public ResponseEntity<List<Empresa>> lista() {
		List<Empresa> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

    //retorna por id
	@GetMapping(path = "/api/empresas/{id}")
	public ResponseEntity<Empresa> lista(@PathVariable("id") long id) {
		Empresa empresa = service.buscarPorId(id);
		if (empresa == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(empresa);
	}

    //criacao de nova empresa
	@PostMapping(path = "/api/empresas")
	@ResponseBody
	public ResponseEntity<Empresa> cria(@Valid @RequestBody Empresa empresa, BindingResult result) {

		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(null);
		} else {
			service.salvar(empresa);
			return ResponseEntity.ok(empresa);
		}
	}

    //update de empresa via id
	@PutMapping(path = "/api/empresas/{id}")
	public ResponseEntity<Empresa> atualiza(@PathVariable("id") long id, @Valid @RequestBody Empresa empresa,
			BindingResult result) {
		// Apenas rejeita se o problema não for com o CNPJ (CNPJ campo read-only)

		if (result.getFieldErrorCount() > 1 || result.getFieldError("CNPJ") == null) {
			return ResponseEntity.badRequest().body(null);
		} else {
			Empresa e = service.buscarPorId(id);
			if (e == null) {
				return ResponseEntity.notFound().build();
			} else {
				empresa.setId(id);
				service.salvar(empresa);
				return ResponseEntity.ok(empresa);
			}
		}
	}

    //remocao de empresa via id 
	@DeleteMapping(path = "/api/empresas/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Empresa empresa = service.buscarPorId(id);
		if (empresa == null) {
			return ResponseEntity.notFound().build();
		} else {
			if (service.empresaTemVagas(id)) {
				return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
			} else {
				service.excluir(id);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
		}
	}
}