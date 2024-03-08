package com.desafioitau.controller;

import com.desafioitau.dto.request.DadosCadastroRequestDTO;
import com.desafioitau.dto.response.DadosCadastroResponseDTO;
import com.desafioitau.service.DadosCadastroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cadastros")
public class DadosCadastroController {

    @Autowired
    private DadosCadastroService cadastroService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<DadosCadastroResponseDTO> detalharCadastro(@PathVariable Long id) {
        DadosCadastroResponseDTO cadastroResponseDTO = cadastroService.detalharCadastro(id);
        return ResponseEntity.ok().body(cadastroResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<DadosCadastroResponseDTO>> listarCadastros() {
        List<DadosCadastroResponseDTO> list = cadastroService.listarCadastros();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<DadosCadastroResponseDTO> cadastrar(@Valid @RequestBody DadosCadastroRequestDTO cadastroRequestDTO) {
        DadosCadastroResponseDTO cadastroResponseDTO = cadastroService.cadastrar(cadastroRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cadastroResponseDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(cadastroResponseDTO);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<DadosCadastroResponseDTO> atualizarDados(@Valid @RequestBody DadosCadastroRequestDTO cadastroRequestDTO, @PathVariable("id") Long id) {
        DadosCadastroResponseDTO dadosCadastro = cadastroService.atualizarDados(id, cadastroRequestDTO);
        return ResponseEntity.ok().body(dadosCadastro);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarCadastro(@PathVariable Long id){
        cadastroService.deletarCadastro(id);
        return ResponseEntity.noContent().build();
    }
}
