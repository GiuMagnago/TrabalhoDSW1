package br.ufscar.dc.dsw.SistemaVagas.controller;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
import br.ufscar.dc.dsw.SistemaVagas.domain.Vaga;
import br.ufscar.dc.dsw.SistemaVagas.security.CustomUserDetails;
import br.ufscar.dc.dsw.SistemaVagas.service.impl.EmailService;
import br.ufscar.dc.dsw.SistemaVagas.service.impl.StorageService;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.ICandidaturaService;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IProfissionalService;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IVagaService;
import jakarta.mail.internet.InternetAddress;

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
    EmailService emailService;

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
    public String cadastrar(@PathVariable("id") Long vagaID, Model model, RedirectAttributes attr) {
        // Pega o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Faz o cast para CustomUserDetails e acessa o objeto Usuario para pegar o ID
        long profissionalId = ((CustomUserDetails) authentication.getPrincipal()).getUsuario().getId();
        Profissional profissional = profissionalService.buscarPorId(profissionalId);

        Vaga vaga = vagaService.buscarPorId(vagaID);
                            
        if (service.existeCandidatura(profissional, vaga)) {
            attr.addFlashAttribute("error", "candidatura.create.error");
            return "redirect:/vagas/listar";
        }
        model.addAttribute("vagaID", vagaID);
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

        Candidatura candidatura = new Candidatura();
        candidatura.setVaga(vagaService.buscarPorId(vagaID));
        candidatura.setProfissional(profissional);
        candidatura.setStatus_candidatura("ABERTO");
        candidatura.setCurriculoPath(storageService.store(curriculo, ("curriculo_" + profissionalId + ".pdf")));
        service.salvar(candidatura);
        attr.addFlashAttribute("success", "candidatura.create.success");
        return "redirect:/candidaturas/listar";
    }

    @GetMapping("/formEdicao/{id}")
    @PreAuthorize("hasRole('EMPRESA')")
    public String editar(@PathVariable("id") Long id, Model model, RedirectAttributes attr) {
        model.addAttribute("candidatura", service.buscarPorId(id));
        return "candidatura/editar";  
    }

    private void sendEmail(Candidatura candidatura) {
        try {
            Profissional profissional = candidatura.getProfissional();
            InternetAddress from = new InternetAddress("sistemavagas@gmail.com", "SistemaVagas");
            InternetAddress to = new InternetAddress(profissional.getEmail(), profissional.getNome());
            emailService.send(from, to, candidatura.getStatus_candidatura(), "link");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @PostMapping("/editar")
    @PreAuthorize("hasRole('EMPRESA')")
    public String atualizar(@RequestParam("id") Long id, @RequestParam("status") String status, RedirectAttributes attr) {
        Candidatura candidatura = service.buscarPorId(id);
        candidatura.setStatus_candidatura(status);
        service.salvar(candidatura);

        sendEmail(candidatura);

        attr.addFlashAttribute("success", "candidatura.edit.success");
        return "redirect:/candidaturas/listarPorVaga/" + candidatura.getVaga().getId();
    }

    @GetMapping("/download/{id}")
    @PreAuthorize("hasRole('EMPRESA')")
    public ResponseEntity<Resource> downloadCurriculo(@PathVariable("id") Long id) {
        Candidatura candidatura = service.buscarPorId(id);
        String filePath = candidatura.getCurriculoPath(); // caminho salvo na base de dados

        try {
            Path file = Paths.get(filePath);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName().toString() + "\"").body(resource);
            } else {
                throw new RuntimeException("Arquivo não encontrado ou não é possível ler o arquivo");
        }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Erro ao fazer o download do arquivo", e);
        }
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
