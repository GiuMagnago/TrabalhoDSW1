package br.ufscar.dc.dsw.SistemaVagas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.SistemaVagas.domain.Candidatura;
import br.ufscar.dc.dsw.SistemaVagas.domain.Profissional;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.ICandidaturaService;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IVagaService;

@Controller
@RequestMapping("/candidaturas")
public class CandidaturaController {
    @Autowired
    ICandidaturaService service;

    @Autowired
    IVagaService vagaService;

    @GetMapping("/listar")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public String listarPorProfissional(Profissional profissional, Model model) {
        model.addAttribute("candidaturas", service.buscarPorProfissional(profissional));
        return "candidatura/listar";
    }

    @GetMapping("/listarPorVaga")
    @PreAuthorize("hasRole('EMPRESA')")
    public String listarPorVaga(long id, Model model) {
        model.addAttribute("candidaturas", service.buscarPorVaga(vagaService.buscarPorId(id)));
        return "candidatura/listar";
    }

    @GetMapping("/formCriacao")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public String formCriacao(Model model) {
        return "candidatura/criar";
    }

    @PostMapping("/criar")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public String criacao(Candidatura candidatura, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "candidatura/criar";
        }

        candidatura.setStatus_candidatura("ABERTO");
        service.salvar(candidatura);
        attr.addFlashAttribute("success");
        return "redirect:/candidaturas/listar";
    }
}
