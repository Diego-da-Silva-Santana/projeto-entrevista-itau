package com.desafioitau.service;

import com.desafioitau.dto.request.DadosCadastroRequestDTO;
import com.desafioitau.dto.response.DadosCadastroResponseDTO;

import java.util.List;

public interface DadosCadastroService {

    DadosCadastroResponseDTO findById(Long id);

    List<DadosCadastroResponseDTO> findAll();

    DadosCadastroResponseDTO cadastrar(DadosCadastroRequestDTO dadosCadastroDTO);

    DadosCadastroResponseDTO atualizarDados(Long id, DadosCadastroRequestDTO dadosCadastroDTO );

    void deletarCadastro(Long id);
}
