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

import br.ufscar.dc.dsw.SistemaVagas.domain.Profissional;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IProfissionalService;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {
    @Autowired
    IProfissionalService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/listar")
    @PreAuthorize("hasRole('ADMIN')")
    public String listar(Model model) {
        model.addAttribute("profissionais", service.buscarTodos());
        return "profissional/lista";
    }

    @GetMapping("/cadastrarForm")
    @PreAuthorize("hasRole('ADMIN')")
    public String cadastrar(Model model) {
        return "profissional/cadastro";
    }

    @GetMapping("/editarForm")
    @PreAuthorize("hasRole('ADMIN')")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("profissional", service.buscarPorId(id));
        return "profissional/cadastro";
    }

    @PostMapping("/cadastrar")
    @PreAuthorize("hasRole('ADMIN')")
    public String registerProfissional(Profissional profissional) {
        profissional.setSenha(passwordEncoder.encode(profissional.getSenha()));
        profissional.setPapel("ROLE_PROFISSIONAL");
        service.salvar(profissional);
        return "redirect:/admin/index";
    }

    @PostMapping("/remover")
    @PreAuthorize("hasRole('ADMIN')")
    public String removeProfissional(@RequestParam Long id) {
        service.excluir(id);
        return "redirect:/admin/index";
    }
}
