package com.desafioitau.service;

import com.desafioitau.dto.request.DadosCadastroRequestDTO;
import com.desafioitau.dto.response.DadosCadastroResponseDTO;
import com.desafioitau.entities.DadosCadastro;
import com.desafioitau.exceptions.ResourceNotFoundException;
import com.desafioitau.exceptions.ResourceNotValidException;
import com.desafioitau.repositories.DadosCadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DadosCadastroServiceImpl implements DadosCadastroService {
    @Autowired
    private DadosCadastroRepository repository;

    @Override
    public DadosCadastroResponseDTO findById(Long id) {
        Optional<DadosCadastro> optionalDadosCadastro = repository.findById(id);

        return optionalDadosCadastro.orElseThrow(() -> new ResourceNotFoundException("O cadastro com ID: " + id + " não foi encontrado na base de dados.")).toDadosCadastroResponseDTO();
    }

    @Override
    public List<DadosCadastroResponseDTO> findAll() {
        List<DadosCadastro> dadosCadastros = repository.findAll();

        return dadosCadastros.stream().map(DadosCadastro::toDadosCadastroResponseDTO).collect(Collectors.toList());
    }

    @Override
    public DadosCadastroResponseDTO cadastrar(DadosCadastroRequestDTO dadosCadastroRequestDTODTO) {
        if (repository.existsBycpf(dadosCadastroRequestDTODTO.getCpf())) {
            throw new ResourceNotValidException("O CPF: " + dadosCadastroRequestDTODTO.getCpf() + " já está cadastrado na base de dados");
        }
        DadosCadastro cadastroRetornado = repository.save(dadosCadastroRequestDTODTO.ToDadosCadastro());
        DadosCadastroResponseDTO dadosCadastroResponseDTO = new DadosCadastroResponseDTO(cadastroRetornado);

        return dadosCadastroResponseDTO;
    }

    @Override
    public DadosCadastroResponseDTO atualizarDados(Long id, DadosCadastroRequestDTO dadosCadastroDTO) {

        Optional<DadosCadastro> possivelDadosCadastroEntity = this.repository.findById(id);
        if (possivelDadosCadastroEntity.isEmpty()) {
            throw new ResourceNotFoundException("Dados do cadastro não foi encontrado na base de dados.");
        }
        DadosCadastro dadosCadastro = possivelDadosCadastroEntity.get();
        dadosCadastro.atualizacaoDadosCadastro(dadosCadastroDTO);
        repository.save(dadosCadastro);

        return dadosCadastro.toDadosCadastroResponseDTO();
    }

    @Override
    public void deletarCadastro(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("O ID " + id + " não foi encontrado na base de dados.");
        }
        repository.deleteById(id);
    }
}
