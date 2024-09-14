package br.ufscar.dc.dsw.SistemaVagas.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.SistemaVagas.domain.Vaga;
import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IVagaService;

@Controller
@RestController
public class VagaControllerREST {
    @Autowired
    IVagaService service;

    @Autowired
    IEmpresaService empresaService;

    @GetMapping("/api/vagas")
    public ResponseEntity<List<Vaga>> listar() {
        List<Vaga> vagas = service.buscarTodos();
        if (vagas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vagas);
    }

    @GetMapping("/api/vagas/{id}")
    public ResponseEntity<Vaga> listarPorId(@PathVariable("id") long id) {
        Vaga vaga = service.buscarPorId(id);
        if (vaga == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vaga);
    }

    @GetMapping("/api/vagas/empresas/{id}")
    public ResponseEntity<List<Vaga>> listarAbertoPorEmpresa(@PathVariable("id") long id) {
        Empresa empresa = empresaService.buscarPorId(id);
        List<Vaga> vagas = service.buscarPorEmpresaEmAberto(empresaService.buscarPorId(id), new Date());
        if (empresa == null || vagas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vagas);
    }
}
