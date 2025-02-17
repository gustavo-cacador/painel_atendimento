package br.com.gustavo.painel_atendimento.controllers;

import br.com.gustavo.painel_atendimento.entities.Senha;
import br.com.gustavo.painel_atendimento.services.SenhaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/senhas")
public class SenhaController {

    private final SenhaService senhaService;

    public SenhaController(SenhaService senhaService) {
        this.senhaService = senhaService;
    }

    // Página inicial
    @GetMapping
    public String formularioSenha(Model model) {
        model.addAttribute("senha", new Senha());
        return "gerar-senha";
    }

    // Gera uma nova senha
    @PostMapping("/gerar")
    public String gerarSenha(@RequestParam String nome, @RequestParam String servico, RedirectAttributes redirectAttributes) {
        Senha senha = senhaService.gerarSenha(nome, servico);
        redirectAttributes.addFlashAttribute("mensagem", "Senha gerada com sucesso: " + senha.getSenha());
        return "redirect:/senhas";
    }

    // 🔹 Exibe painel de senhas pendentes e atendidas (Retorna JSON para atualização via AJAX)
    @GetMapping("/painel")
    @ResponseBody
    public Map<String, List<Senha>> atualizarPainel() {
        Map<String, List<Senha>> response = new HashMap<>();
        response.put("senhasPendentes", senhaService.listarSenhasPendentes());
        response.put("senhasAtendidas", senhaService.listarSenhasAtendidas());
        return response;
    }

    // 🔹 Chama a próxima senha da fila e retorna JSON
    @PostMapping("/chamar-senha")
    @ResponseBody
    public ResponseEntity<Map<String, String>> chamarProximaSenha() {
        Senha senhaChamada = senhaService.chamarProximaSenha();
        Map<String, String> response = new HashMap<>();

        if (senhaChamada != null) {
            response.put("mensagem", "Senha " + senhaChamada.getSenha() + " chamada com sucesso!");
        } else {
            response.put("mensagem", "Nenhuma senha na fila!");
        }

        return ResponseEntity.ok(response);
    }
}
