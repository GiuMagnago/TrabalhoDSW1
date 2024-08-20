package br.ufscar.dc.dsw.SistemaVagas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.SistemaVagas.domain.Profissional;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IProfissionalService;
import jakarta.validation.Valid;

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

    @GetMapping("/formCadastro")
    @PreAuthorize("hasRole('ADMIN')")
    public String cadastrar(Profissional profissional) {
        return "profissional/cadastro";
    }

    @GetMapping("/formEdicao/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("profissional", service.buscarPorId(id));
        return "profissional/cadastro";
    }

    @PostMapping("/cadastrar")
    @PreAuthorize("hasRole('ADMIN')")
    public String criar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "profissionais/cadastro";
        }
        profissional.setSenha(passwordEncoder.encode(profissional.getSenha()));
        profissional.setPapel("ROLE_PROFISSIONAL");
        service.salvar(profissional);
        attr.addFlashAttribute("sucess", "profissional.create.sucess");
        return "redirect:/profissionais/listar";
    }

    @PostMapping("/editar")
    @PreAuthorize("hasRole('ADMIN')")
    public String atualizar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "profissionais/cadastro";
        }
        profissional.setSenha(passwordEncoder.encode(profissional.getSenha()));
        service.salvar(profissional);
        attr.addFlashAttribute("sucess", "profissional.edit.sucess");
        return "redirect:/profissionais/listar";
    }

    @GetMapping("/remover/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String remover(@RequestParam Long id, RedirectAttributes attr) {
        service.excluir(id);
        attr.addFlashAttribute("sucess", "profissional.delete.sucess");
        return "redirect:/profissionais/listar";
    }
}
