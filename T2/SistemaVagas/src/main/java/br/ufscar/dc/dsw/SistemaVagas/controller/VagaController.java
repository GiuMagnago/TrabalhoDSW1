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
import jakarta.validation.Valid;

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
        model.addAttribute("cidades", service.buscarCidadesDistintas());
        return "vaga/lista";
    }

    @GetMapping("/listarPorCidade")
    public String listarPorCidade(@RequestParam("cidade") String cidade, Model model) {
        if (cidade.isEmpty()) {
            return "redirect:/vagas/listar";
        }
        model.addAttribute("vagas", service.buscarPorCidade(cidade));
        model.addAttribute("cidades", service.buscarCidadesDistintas());
        model.addAttribute("cidadeSelecionada", cidade);
        return "vaga/lista";
    }

    @GetMapping("/listarPorEmpresa")
    @PreAuthorize("hasRole('EMPRESA')")
    public String listarPorEmpresa(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long empresaId = ((CustomUserDetails) authentication.getPrincipal()).getUsuario().getId();
        Empresa empresa = empresaService.buscarPorId(empresaId);

        model.addAttribute("vagas", service.buscarPorEmpresa(empresa));
        return "vaga/listaEmpresa";
    }

    @GetMapping("/formCadastro")
    @PreAuthorize("hasRole('EMPRESA')")
    public String formCadastro(Vaga vaga, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long empresaId = ((CustomUserDetails) authentication.getPrincipal()).getUsuario().getId();
        Empresa empresa = empresaService.buscarPorId(empresaId);
        model.addAttribute("cnpj", empresa.getCnpj());
        return "vaga/cadastro";
    }

    @PostMapping("/criar")
    @PreAuthorize("hasRole('EMPRESA')")
    public String criar(@Valid Vaga vaga, BindingResult result, RedirectAttributes attr, Model model) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            long empresaId = ((CustomUserDetails) authentication.getPrincipal()).getUsuario().getId();
            Empresa empresa = empresaService.buscarPorId(empresaId);
            model.addAttribute("cnpj", empresa.getCnpj());
            return "vaga/cadastro";
        }
        vaga.setEmpresa(empresaService.buscarPorCnpj(vaga.getCnpj_empresa()));
        service.salvar(vaga);

        attr.addFlashAttribute("success", "success.vaga.criar"); // Adiciona um atributo para o front falando que a criação foi um successo
        return "redirect:/vagas/listarPorEmpresa";
    }

    @GetMapping("/remover/{id}")
    @PreAuthorize("hasRole('EMPRESA')")
    public String remover(@PathVariable("id") Long id, RedirectAttributes attr) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long empresaId = ((CustomUserDetails) authentication.getPrincipal()).getUsuario().getId();
        Empresa empresa = empresaService.buscarPorId(empresaId);

        if (service.buscarPorId(id).getEmpresa() != empresa) {
            attr.addFlashAttribute("error", "error.vaga.excluir");
            return "redirect:/vagas/listar";
        }

        service.excluir(id);
        attr.addAttribute("succes", "success.vaga.excluir");
        return "redirect:/vagas/listarPorEmpresa";
    }
}
