package com.desafioitau.dto.request;

public class DadosCadastroRequestDTO {

    private String nome;
    private String cpf;
    private Integer idade;
    private String pais;

    public DadosCadastroRequestDTO() {
    }

    public DadosCadastroRequestDTO(String nome, String cpf, Integer idade, String pais) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.pais = pais;
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
