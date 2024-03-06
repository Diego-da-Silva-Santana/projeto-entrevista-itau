package com.desafioitau.entities;

import com.desafioitau.dto.response.DadosCadastroResponseDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_cadastro")
public class DadosCadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;
    @Column(name = "idade", nullable = false)
    private Integer idade;

    @Column(name = "pais", nullable = false)
    private String pais;

    public DadosCadastro() {
    }

    public DadosCadastro(String nome, String cpf, Integer idade, String pais) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.pais = pais;
    }

    public DadosCadastroResponseDTO toDadosCadastroResponseDTO() {
        return new DadosCadastroResponseDTO(id, nome, cpf, idade, pais);
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
