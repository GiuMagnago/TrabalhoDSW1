package br.ufscar.dc.dsw.SistemaVagas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;
import br.ufscar.dc.dsw.SistemaVagas.domain.Vaga;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IVagaService;

@Controller
@RequestMapping("/vagas")
public class VagaController {
    @Autowired
    IVagaService service;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("vagas", service.buscarTodos());
        return "vaga/lista";
    }

    @GetMapping("/listarPorCidade")
    public String listarPorCidade(Model model, String cidade) {
        model.addAttribute("vagas", service.buscarPorCidade(cidade));
        return "vaga/lista";
    }

    @GetMapping("/listarPorEmpresa")
    @PreAuthorize("hasRole('EMPRESA')")
    public String listarPorEmpresa(Model model, Empresa empresa) {
        model.addAttribute("vagas", service.buscarPorEmpresa(empresa));
        return "vaga/lista";
    }

    @GetMapping("/formCriacao")
    @PreAuthorize("hasRole('EMPRESA')")
    public String formCriacao(Vaga vaga) {
        return "vaga/criar";
    }

    @PostMapping("/criar")
    @PreAuthorize("hasRole('EMPRESA')")
    public String criar(Vaga vaga, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "vaga/criar";
        }
        service.salvar(vaga); 

        /*
         * checar no front se a data limite é válida
         * 
         * 
         * 
         * 
         * 
         */
        attr.addFlashAttribute("success", "vaga.create.sucess"); // Adiciona um atributo para o front falando que a criação foi um sucesso
        return "redirect:/vagas/listar";
    }

    @GetMapping("/remover/{id}")
    @PreAuthorize("hasRole('EMPRESA')")
    public String remover(@PathVariable("id") Long id, RedirectAttributes attr) {
        service.excluir(id);
        attr.addAttribute("succes", "vaga.delete.success");
        return "redirect:/vagas/listar";
    }
}
