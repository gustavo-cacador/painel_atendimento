package br.com.gustavo.painel_atendimento.controllers;

import br.com.gustavo.painel_atendimento.dto.ClienteDTO;
import br.com.gustavo.painel_atendimento.entities.Cliente;
import br.com.gustavo.painel_atendimento.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/senhas")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String formularioSenha(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "gerar-senha";
    }

    @PostMapping("/gerar")
    public ResponseEntity<String> gerarCliente(@RequestBody ClienteDTO dto) {
        ClienteDTO cliente = clienteService.gerarCliente(dto);
        return ResponseEntity.ok("Senha gerada com sucesso: " + cliente.getSenha());
    }

    @GetMapping("/painel")
    public String painelSenhas(Model model) {
        List<ClienteDTO> clientesPendentes = clienteService.listarClientesPendentes();
        List<ClienteDTO> clientesAtendidos = clienteService.listarClientesAtendidos();

        model.addAttribute("clientesPendentes", clientesPendentes);
        model.addAttribute("clientesAtendidos", clientesAtendidos);

        return "painel-controle";
    }

    /*
    @GetMapping("/atualizar-painel")
    @ResponseBody
    public Map<String, List<Senha>> atualizarPainel() {
        List<Senha> senhasAtendidas = senhaRepository.findByChamadaTrue();
        List<Senha> senhasPendentes = senhaRepository.findByChamadaFalseOrderByDataHoraAsc();

        // Log para verificar os guichês
        senhasAtendidas.forEach(s -> System.out.println("Senha: " + s.getCodigoSenha() + ", Guichê: " + (s.getGuiche() != null ? s.getGuiche().getNumero() : "Nenhum")));

        Map<String, List<Senha>> response = new HashMap<>();
        response.put("senhasAtendidas", senhasAtendidas);
        response.put("senhasPendentes", senhasPendentes);
        return response;
    }
     */

    
}
