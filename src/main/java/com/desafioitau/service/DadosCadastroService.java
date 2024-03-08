package com.desafioitau.service;

import com.desafioitau.dto.request.DadosCadastroRequestDTO;
import com.desafioitau.dto.response.DadosCadastroResponseDTO;

import java.util.List;

public interface DadosCadastroService {

    DadosCadastroResponseDTO detalharCadastro(Long id);

    List<DadosCadastroResponseDTO> listarCadastros();

    DadosCadastroResponseDTO cadastrar(DadosCadastroRequestDTO dadosCadastroDTO);

    DadosCadastroResponseDTO atualizarDados(Long id, DadosCadastroRequestDTO dadosCadastroDTO );

    void deletarCadastro(Long id);
}
