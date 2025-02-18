package br.com.gustavo.painel_atendimento.controllers;

import br.com.gustavo.painel_atendimento.entities.Senha;
import br.com.gustavo.painel_atendimento.services.SenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller //Agora o Spring sabe que retorna páginas HTML
@RequestMapping("/senhas")
public class SenhaController {

    @Autowired
    private SenhaService senhaService;

    // pagina inicial
    @GetMapping
    public String formularioSenha(Model model) {
        model.addAttribute("senha", new Senha());
        return "gerar-senha";
    }

    // gera senha
    @PostMapping("/gerar")
    public String gerarSenha(@RequestParam String nome, @RequestParam String servico, RedirectAttributes redirectAttributes) {
        Senha senha = senhaService.gerarSenha(nome, servico);
        redirectAttributes.addFlashAttribute("mensagem", "Senha gerada com sucesso: " + senha.getSenha());
        return "redirect:/senhas";
    }

    // exibe painel de senhas pendentes
    @GetMapping("/painel")
    public String painelSenhas(Model model) {
        List<Senha> senhasPendentes = senhaService.listarSenhasPendentes();
        List<Senha> senhasAtendidas = senhaService.listarSenhasAtendidas();

        model.addAttribute("senhasPendentes", senhasPendentes);
        model.addAttribute("senhasAtendidas", senhasAtendidas);

        return "painel";
    }

    // chama a próxima senha
    @PostMapping("/chamar-senha")
    @ResponseBody
    public ResponseEntity<?> chamarProximaSenha() {
        Senha senhaChamada = senhaService.chamarProximaSenha();

        if (senhaChamada != null) {
            return ResponseEntity.ok(Collections.singletonMap("mensagem", "Senha " + senhaChamada.getSenha() + " chamada com sucesso!"));
        } else {
            return ResponseEntity.ok(Collections.singletonMap("mensagem", "Nenhuma senha na fila!"));
        }
    }
}
