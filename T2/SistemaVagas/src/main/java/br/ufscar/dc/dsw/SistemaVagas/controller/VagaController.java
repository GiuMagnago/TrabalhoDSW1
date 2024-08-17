package br.ufscar.dc.dsw.SistemaVagas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;
import br.ufscar.dc.dsw.SistemaVagas.domain.Vaga;
import br.ufscar.dc.dsw.SistemaVagas.security.CustomUserDetails;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IVagaService;

@Controller
@RequestMapping("/vagas")
public class VagaController {
    @Autowired
    IVagaService service;

    @Autowired
    IEmpresaService empresaService;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("vagas", service.buscarTodos());
        model.addAttribute("cidades", empresaService.buscarCidadesUnicas());
        return "vaga/lista";
    }

    @GetMapping("/listarPorCidade")
    public String listarPorCidade(@RequestParam("cidade") String cidade, Model model) {
        model.addAttribute("vagas", service.buscarPorCidade(cidade));
        model.addAttribute("cidades", empresaService.buscarCidadesUnicas());
        model.addAttribute("cidadeSelecionada", cidade);
        return "vaga/lista";
    }

    @GetMapping("/listarPorEmpresa")
    @PreAuthorize("hasRole('EMPRESA')")
    public String listarPorEmpresa(Model model) {
        // Pega o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Faz o cast para CustomUserDetails e acessa o objeto Usuario para pegar o ID
        long empresaId = ((CustomUserDetails) authentication.getPrincipal()).getUsuario().getId();
        Empresa empresa = empresaService.buscarPorId(empresaId);

        model.addAttribute("vagas", service.buscarPorEmpresa(empresa));
        return "vaga/listaEmpresa";
    }

    @GetMapping("/formCadastro")
    @PreAuthorize("hasRole('EMPRESA')")
    public String formCriacao(Vaga vaga) {
        return "vaga/cadastro";
    }

    @PostMapping("/cadastrar")
    @PreAuthorize("hasRole('EMPRESA')")
    public String criar(Vaga vaga, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "vaga/cadastro";
        }
        vaga.setEmpresa(empresaService.buscarPorCnpj(vaga.getCnpj_empresa()));
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
        return "redirect:/vagas/listarPorEmpresa";
    }

    @GetMapping("/remover/{id}")
    @PreAuthorize("hasRole('EMPRESA')")
    public String remover(@PathVariable("id") Long id, RedirectAttributes attr) {
        service.excluir(id);
        attr.addAttribute("succes", "vaga.delete.success");
        return "redirect:/vagas/listar";
    }
}
