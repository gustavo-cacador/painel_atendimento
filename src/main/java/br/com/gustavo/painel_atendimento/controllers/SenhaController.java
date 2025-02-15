package br.com.gustavo.painel_atendimento.controllers;

import br.com.gustavo.painel_atendimento.entities.Senha;
import br.com.gustavo.painel_atendimento.services.SenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller // ✅ Agora o Spring sabe que retorna páginas HTML
@RequestMapping("/senhas")
public class SenhaController {

    @Autowired
    private SenhaService senhaService;

    // 🟢 Página inicial
    @GetMapping
    public String formularioSenha(Model model) {
        model.addAttribute("senha", new Senha());
        return "gerar-senha";
    }

    // 🟢 Gerar Senha
    @PostMapping("/gerar")
    public String gerarSenha(@ModelAttribute Senha senha, RedirectAttributes redirectAttributes) {
        senhaService.gerarSenha(senha);
        redirectAttributes.addFlashAttribute("mensagem", "Senha gerada com sucesso!");
        return "redirect:/senhas";
    }

    // 🟢 Exibir painel de senhas pendentes
    @GetMapping("/painel")
    public String painelSenhas(Model model) {
        List<Senha> senhas = senhaService.listarSenhasPendentes();
        model.addAttribute("senhas", senhas);
        return "painel";
    }

    // 🟢 Chamar próxima senha
    @PostMapping("/chamar")
    public String chamarProximaSenha() {
        senhaService.chamarProximaSenha();
        return "redirect:/senhas/painel";
    }
}
