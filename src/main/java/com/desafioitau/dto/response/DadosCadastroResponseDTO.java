package com.desafioitau.dto.response;

import com.desafioitau.entities.DadosCadastro;

public class DadosCadastroResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private Integer idade;
    private String pais;

    public DadosCadastroResponseDTO() {
    }

    public DadosCadastroResponseDTO(Long id, String nome, String cpf, Integer idade, String pais) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.pais = pais;
    }

    public DadosCadastroResponseDTO(DadosCadastro dadosCadastro) {
        this.id = dadosCadastro.getId();
        this.nome = dadosCadastro.getNome();
        this.cpf = dadosCadastro.getCpf();
        this.idade = dadosCadastro.getIdade();
        this.pais = dadosCadastro.getPais();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getPais() {
        return pais;
    }
}
