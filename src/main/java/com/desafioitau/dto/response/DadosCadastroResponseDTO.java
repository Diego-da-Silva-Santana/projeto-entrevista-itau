package com.desafioitau.dto.response;

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
