package br.ufscar.dc.dsw.SistemaVagas.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.SistemaVagas.domain.Candidatura;
import br.ufscar.dc.dsw.SistemaVagas.domain.Profissional;
import br.ufscar.dc.dsw.SistemaVagas.security.CustomUserDetails;
import br.ufscar.dc.dsw.SistemaVagas.service.impl.StorageService;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.ICandidaturaService;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IProfissionalService;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IVagaService;

@Controller
@RequestMapping("/candidaturas")
public class CandidaturaController {
    @Autowired
    ICandidaturaService service;

    @Autowired
    IVagaService vagaService;

    @Autowired
    IProfissionalService profissionalService;

    @Autowired
    StorageService storageService;

    @GetMapping("/listar")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public String listarPorProfissional(Model model) {
        // Pega o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Faz o cast para CustomUserDetails e acessa o objeto Usuario para pegar o ID
        long profissionalId = ((CustomUserDetails) authentication.getPrincipal()).getUsuario().getId();
        Profissional profissional = profissionalService.buscarPorId(profissionalId);

        model.addAttribute("candidaturas", service.buscarPorProfissional(profissional));
        return "candidatura/lista";
    }

    @GetMapping("/listarPorVaga/{id}")
    @PreAuthorize("hasRole('EMPRESA')")
    public String listarPorVaga(@PathVariable("id") Long id, Model model) {
        model.addAttribute("candidaturas", service.buscarPorVaga(vagaService.buscarPorId(id)));
        return "candidatura/lista";
    }

    @GetMapping("/formCadastro/{id}")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public String cadastrar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("vagaID", id);
        return "candidatura/cadastro";  
    }

    @PostMapping("/cadastrar")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public String criar(@RequestParam("vagaID") Long vagaID, 
                        @RequestParam("curriculo") MultipartFile curriculo, 
                        RedirectAttributes attr) {
        // Pega o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Faz o cast para CustomUserDetails e acessa o objeto Usuario para pegar o ID
        long profissionalId = ((CustomUserDetails) authentication.getPrincipal()).getUsuario().getId();
        Profissional profissional = profissionalService.buscarPorId(profissionalId);

        if (profissional == null) {
            return ("redirect:candidaturas/formCadastro/" + vagaID);
        }

        Candidatura candidatura = new Candidatura();
        candidatura.setVaga(vagaService.buscarPorId(vagaID));
        candidatura.setProfissional(profissional);
        candidatura.setStatus_candidatura("ABERTO");
        candidatura.setCurriculoPath(storageService.store(curriculo));
        service.salvar(candidatura);
        attr.addFlashAttribute("success", "candidatura.create.success");
        return "redirect:/candidaturas/listar";
    }

    @PostMapping("/editar")
    @PreAuthorize("hasRole('EMPRESA')")
    public String atualizar(@RequestParam("id") Long id, @RequestParam("status") String status, RedirectAttributes attr) {
        Candidatura candidatura = service.buscarPorId(id);
        candidatura.setStatus_candidatura(status);
        service.salvar(candidatura);
        attr.addFlashAttribute("success", "candidatura.edit.success");
        return "redirect:/candidaturas/listarPorVaga/" + candidatura.getVaga().getId();
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws MalformedURLException {
        Path file = storageService.load(filename);
        Resource resource = new UrlResource(file.toUri());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

    @GetMapping("/remover/{id}")
    @PreAuthorize("hasRole('PROFISSIONAL')")
    public String remover(@PathVariable("id") Long id, RedirectAttributes attr) {
        storageService.deleteFile(service.buscarPorId(id).getCurriculoPath()); // Deletar o curriculo
        service.excluir(id);
        attr.addAttribute("success", "cadidatura.delete.success");
        return "redirect:/candidaturas/listar";
    }
}
