package br.com.gustavo.painel_atendimento.dto;

import br.com.gustavo.painel_atendimento.entities.Cliente;

import java.time.Instant;

public class ClienteDTO {

    private Long id;
    private String nome;
    private String servico;
    private Integer senha;
    private Instant momento;
    private boolean chamada;


    public ClienteDTO() {
    }

    public ClienteDTO(Long id, String nome, String servico, Integer senha, Instant momento, boolean chamada) {
        this.id = id;
        this.nome = nome;
        this.servico = servico;
        this.senha = senha;
        this.momento = momento;
        this.chamada = chamada;
    }

    public ClienteDTO(Cliente entity) {
        id = entity.getId();
        nome = entity.getNome();
        servico = entity.getServico();
        senha = entity.getSenha();
        momento = entity.getMomento();
        chamada = entity.isChamada();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getServico() {
        return servico;
    }

    public Integer getSenha() {
        return senha;
    }

    public Instant getMomento() {
        return momento;
    }

    public boolean isChamada() {
        return chamada;
    }
}
