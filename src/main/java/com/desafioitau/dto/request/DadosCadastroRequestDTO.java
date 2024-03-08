package com.desafioitau.dto.request;

import com.desafioitau.entities.DadosCadastro;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class DadosCadastroRequestDTO {
    @Pattern(regexp = "^[a-zA-Z]*$", message = "O campo deve conter apenas letras")
    private String nome;
    @CPF
    private String cpf;

    private Integer idade;
    @Pattern(regexp = "^[a-zA-Z]*$", message = "O campo deve conter apenas letras")
    private String pais;

    public DadosCadastroRequestDTO() {
    }

    public DadosCadastroRequestDTO(String nome, String cpf, Integer idade, String pais) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.pais = pais;
    }

    public DadosCadastro ToDadosCadastro() {
        return new DadosCadastro(nome, cpf, idade, pais);
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
