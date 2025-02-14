package br.com.gustavo.painel_atendimento.controllers;

import br.com.gustavo.painel_atendimento.entities.Senha;
import br.com.gustavo.painel_atendimento.services.SenhaService;
import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/senhas")
public class SenhaController {

    @Autowired
    private SenhaService senhaService;

    @GetMapping
    public String formularioSenha() {
        return "index";
    }

    @PostMapping("/gerar")
    public String gerarSenha(@RequestParam String nome, @RequestParam String servico, Model model) {
        Senha senha = senhaService.gerarSenha(nome, servico);
        model.addText("senha");
        return "index";
    }

    @GetMapping("/painel")
    public String painelSenhas(Model model) {
        List<Senha> senhas = senhaService.listarSenhasPendentes();
        model.addText("senhas");
        return "painel";
    }

    @PostMapping("/chamar")
    public String chamarProximaSenha() {
        senhaService.chamarProximaSenha();
        return "redirect:/painel";
    }
}
