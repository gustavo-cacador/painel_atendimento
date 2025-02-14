package br.com.gustavo.painel_atendimento.services;

import br.com.gustavo.painel_atendimento.entities.Senha;
import br.com.gustavo.painel_atendimento.repositories.SenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SenhaService {

    @Autowired
    private SenhaRepository senhaRepository;

    public Senha gerarSenha(String nome, String servico) {
        Senha senha = new Senha();
        senha.setNome(nome);
        senha.setServico(servico);
        senha.setSenha(gerarCodigoSenha(servico));
        return senhaRepository.save(senha);
    }

    public List<Senha> listarSenhasPendentes() {
        return senhaRepository.findByChamadaFalseOrderByDataHoraAsc();
    }

    public void chamarProximaSenha() {
        List<Senha> senhasPendentes = listarSenhasPendentes();
        if (!senhasPendentes.isEmpty()) {
            Senha senha = senhasPendentes.get(0);
            senha.setChamada(true);
            senhaRepository.save(senha);
        }
    }

    private String gerarCodigoSenha(String servico) {
        return servico.substring(0, 1).toUpperCase() + System.currentTimeMillis() % 1000;
    }
}
