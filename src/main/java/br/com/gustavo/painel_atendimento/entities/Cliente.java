package br.com.gustavo.painel_atendimento.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String servico;
    private Integer senha;
    private Instant momento;
    private boolean chamada;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String servico, Integer senha, Instant momento, boolean chamada) {
        this.id = id;
        this.nome = nome;
        this.servico = servico;
        this.senha = senha;
        this.momento = momento;
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

    public Integer getSenha() {
        return senha;
    }

    public void setSenha(Integer senha) {
        this.senha = senha;
    }

    public Instant getMomento() {
        return momento;
    }

    public void setMomento(Instant momento) {
        this.momento = momento;
    }

    public boolean isChamada() {
        return chamada;
    }

    public void setChamada(boolean chamada) {
        this.chamada = chamada;
    }
}
