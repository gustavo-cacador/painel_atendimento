package br.com.gustavo.painel_atendimento.controllers;

import br.com.gustavo.painel_atendimento.entities.Senha;
import br.com.gustavo.painel_atendimento.services.SenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        List<Senha> senhas = senhaService.listarSenhasPendentes();
        model.addAttribute("senhas", senhas);
        return "painel";
    }

    // chama a próxima senha
    @PostMapping("/chamar")
    public String chamarProximaSenha() {
        senhaService.chamarProximaSenha();
        return "redirect:/senhas/painel";
    }
}
