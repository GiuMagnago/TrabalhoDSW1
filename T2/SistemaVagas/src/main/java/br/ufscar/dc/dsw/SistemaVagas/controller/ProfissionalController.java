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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.SistemaVagas.domain.Profissional;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IProfissionalService;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/profissionais")
public class ProfissionalController {
    @Autowired
    IProfissionalService service;

    @Autowired
    IUsuarioService usuarioService;
    

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
    public String formCadastro(Profissional profissional, Model model) {
        model.addAttribute("papel", "ROLE_PROFISSIONAL");
        return "profissional/cadastro";
    }

    @GetMapping("/formEdicao/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String formEdicao(@PathVariable("id") Long id, Model model) {
        model.addAttribute("profissional", service.buscarPorId(id));
        model.addAttribute("papel", "ROLE_PROFISSIONAL");
        return "profissional/cadastro";
    }

    @PostMapping("/criar")
    @PreAuthorize("hasRole('ADMIN')")
    public String criar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("papel", "ROLE_PROFISSIONAL");
            return "profissional/cadastro";
        }
        profissional.setSenha(passwordEncoder.encode(profissional.getSenha()));
        service.salvar(profissional);

        attr.addFlashAttribute("success", "success.profissional.criar");
        return "redirect:/profissionais/listar";
    }

    @PostMapping("/editar")
    @PreAuthorize("hasRole('ADMIN')")
    public String editar(@Valid Profissional profissional, BindingResult result, RedirectAttributes attr, Model model) {
        if (result.hasErrors()) {
            return "profissional/cadastro";
        }
        profissional.setSenha(passwordEncoder.encode(profissional.getSenha()));
        service.salvar(profissional);
        attr.addFlashAttribute("success", "success.profissional.editar");
        return "redirect:/profissionais/listar";
    }

    @GetMapping("/remover/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String remover(@PathVariable("id") Long id, RedirectAttributes attr) {
        if (service.buscarPorId(id) == null) {
            attr.addFlashAttribute("error", "error.profissional.excluir");
            return "redirect:/profissionais/listar";
        }

        service.excluir(id);
        attr.addFlashAttribute("success", "success.profissional.excluir");
        return "redirect:/profissionais/listar";
    }
}
