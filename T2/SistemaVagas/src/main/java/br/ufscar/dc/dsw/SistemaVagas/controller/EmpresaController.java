package br.ufscar.dc.dsw.SistemaVagas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IEmpresaService;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {
    @Autowired
    IEmpresaService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/listar")
    @PreAuthorize("hasRole('ADMIN')")
    public String listar(Model model) {
        model.addAttribute("empresas", service.buscarTodos());
        return "empresa/lista";
    }

    @GetMapping("/cadastrarForm")
    @PreAuthorize("hasRole('ADMIN')")
    public String cadastrar(Model model) {
        return "empresa/cadastro";
    }

    @GetMapping("/editarForm")
    @PreAuthorize("hasRole('ADMIN')")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("empresa", service.buscarPorId(id));
        return "empresa/cadastro";
    }

    @PostMapping("/cadastrar")
    @PreAuthorize("hasRole('ADMIN')")
    public String registerEmpresa(Empresa empresa) {
        empresa.setSenha(passwordEncoder.encode(empresa.getSenha()));
        empresa.setPapel("ROLE_EMPRESA");
        service.salvar(empresa);
        return "redirect:/admin/index";
    }

    @PostMapping("/remover")
    @PreAuthorize("hasRole('ADMIN')")
    public String removeEmpresa(@RequestParam Long id) {
        service.excluir(id);
        return "redirect:/admin/index";
    }
}
