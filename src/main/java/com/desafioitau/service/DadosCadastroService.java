package com.desafioitau.service;

import com.desafioitau.dto.request.DadosCadastroRequestDTO;
import com.desafioitau.dto.response.DadosCadastroResponseDTO;

import java.util.List;

public interface DadosCadastroService {

   DadosCadastroResponseDTO findById(Long id);

   List<DadosCadastroResponseDTO> findAll();

   DadosCadastroResponseDTO cadastrar(DadosCadastroRequestDTO dadosCadastroDTO);

   DadosCadastroResponseDTO update(DadosCadastroRequestDTO dadosCadastroDTO, Long id);

   String delete(Long id);

}
