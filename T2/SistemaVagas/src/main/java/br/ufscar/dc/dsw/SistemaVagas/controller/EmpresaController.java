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

import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {
    @Autowired
    IEmpresaService service;

    @Autowired
    IUsuarioService usuarioService;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @GetMapping("/listar")
    @PreAuthorize("hasRole('ADMIN')")
    public String listar(Model model) {
        model.addAttribute("empresas", service.buscarTodos());
        return "empresa/lista";
    }

    @GetMapping("/formCadastro")
    @PreAuthorize("hasRole('ADMIN')")
    public String cadastrar(Empresa empresa, Model model) {
        return "empresa/cadastro";
    }

    @GetMapping("/formEdicao/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("empresa", service.buscarPorId(id));
        return "empresa/cadastro";
    }

    @PostMapping("/criar")
    @PreAuthorize("hasRole('ADMIN')")
    public String criar(@Valid Empresa empresa, BindingResult result, RedirectAttributes attr, Model model) {
        if (result.hasErrors()) {
            return "empresa/cadastro";
        }
        empresa.setPapel("ROLE_EMPRESA");
        empresa.setEnable(true);
        empresa.setSenha(passwordEncoder.encode(empresa.getSenha()));
        service.salvar(empresa);

        attr.addFlashAttribute("success", "success.empresa.criar");
        return "redirect:/empresas/listar";
    }

    @PostMapping("/editar")
    @PreAuthorize("hasRole('ADMIN')")
    public String atualizar(@Valid Empresa empresa, BindingResult result, RedirectAttributes attr, Model model) {
        if (result.getErrorCount() > 2 || result.getFieldValue("cnpj") == null || result.getFieldValue("email") == null) {
            return "empresa/cadastro";
        }
        empresa.setPapel("ROLE_EMPRESA");
        empresa.setEnable(true);
        empresa.setSenha(passwordEncoder.encode(empresa.getSenha()));
        service.salvar(empresa);

        attr.addFlashAttribute("success", "success.empresa.editar");
        return "redirect:/empresas/listar";
    }

    @GetMapping("/remover/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String remover(@PathVariable("id") Long id, RedirectAttributes attr) {
        if (service.buscarPorId(id) == null) {
            attr.addFlashAttribute("error", "error.empresa.excluir");
            return "redirect:/empresas/listar";
        }
        service.excluir(id);
        attr.addFlashAttribute("success", "success.empresa.excluir");
        return "redirect:/empresas/listar";
    }
}

