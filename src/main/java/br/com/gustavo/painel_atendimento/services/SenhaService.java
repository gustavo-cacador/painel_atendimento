package br.com.gustavo.painel_atendimento.services;

import br.com.gustavo.painel_atendimento.entities.Senha;
import br.com.gustavo.painel_atendimento.repositories.SenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SenhaService {

    @Autowired
    private SenhaRepository senhaRepository;

    public Senha gerarSenha(String nome, String servico) {
        Senha senha = new Senha();
        senha.setNome(nome);
        senha.setServico(servico);
        senha.setDataHora(LocalDateTime.now());
        senha.setChamada(false);

        Long proximoId = senhaRepository.count() + 1;
        senha.setSenha(String.format("%03d", proximoId));

        return senhaRepository.save(senha);
    }

    public List<Senha> listarSenhasPendentes() {
        return senhaRepository.findByChamadaFalseOrderByDataHoraAsc();
    }

    public Senha chamarProximaSenha() {
        List<Senha> senhasPendentes = listarSenhasPendentes();

        if (!senhasPendentes.isEmpty()) {
            Senha senha = senhasPendentes.get(0); // Pega a primeira da fila
            senha.setChamada(true);
            senhaRepository.save(senha);
            return senha;
        }

        return null; // Nenhuma senha na fila
    }


    private String gerarCodigoSenha(String servico) {
        return servico.substring(0, 1).toUpperCase() + UUID.randomUUID().toString().substring(0, 6);
    }

    public void gerarSenha(Senha senha) {
        senha.setDataHora(LocalDateTime.now());
        senha.setChamada(false);
        senhaRepository.save(senha);
    }

    public List<Senha> listarSenhasAtendidas() {
        return senhaRepository.findByChamadaTrueOrderByDataHoraAsc();
    }

}
