package br.com.gustavo.painel_atendimento.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Senha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String servico;
    private String senha;
    private LocalDateTime dataHora;
    private boolean chamada;

    public Senha() {
    }

    public Senha(Long id, String nome, String servico, String senha, LocalDateTime dataHora, boolean chamada) {
        this.id = id;
        this.nome = nome;
        this.servico = servico;
        this.senha = senha;
        this.dataHora = dataHora;
        this.chamada = chamada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public boolean isChamada() {
        return chamada;
    }

    public void setChamada(boolean chamada) {
        this.chamada = chamada;
    }
}
