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

    @GetMapping("/formCadastro")
    @PreAuthorize("hasRole('ADMIN')")
    public String cadastrar(Empresa empresa) {
        return "empresa/cadastro";
    }

    @GetMapping("/formEdicao/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("empresa", service.buscarPorId(id));
        return "empresa/cadastro";
    }

    @PostMapping("/cadastrar")
    @PreAuthorize("hasRole('ADMIN')")
    public String criar(Empresa empresa, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "empresa/cadastro";
        }
        empresa.setSenha(passwordEncoder.encode(empresa.getSenha()));
        empresa.setPapel("ROLE_EMPRESA");
        service.salvar(empresa);
        attr.addFlashAttribute("success", "empresa.create.success");
        return "redirect:/empresas/listar";
    }

    @PostMapping("/editar")
    @PreAuthorize("hasRole('ADMIN')")
    public String atualizar(Empresa empresa, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "empresa/cadastro";
        }
        System.out.println("awdniuawhdiaudhu    " + empresa.getId());
        empresa.setSenha(passwordEncoder.encode(empresa.getSenha()));
        service.salvar(empresa);
        attr.addFlashAttribute("success", "empresa.edit.success");
        return "redirect:/empresas/listar";
    }

    @GetMapping("/remover/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String remover(@PathVariable("id") Long id, RedirectAttributes attr) {
        service.excluir(id);
        attr.addFlashAttribute("success", "empresa.delete.success");
        return "redirect:/empresas/listar";
    }
}
