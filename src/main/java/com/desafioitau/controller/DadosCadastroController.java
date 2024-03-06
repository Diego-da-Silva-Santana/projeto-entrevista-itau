package com.desafioitau.controller;

import com.desafioitau.dto.response.DadosCadastroResponseDTO;
import com.desafioitau.service.DadosCadastroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cadastros")
public class DadosCadastroController {

    @Autowired
    private DadosCadastroServiceImpl cadastroService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<DadosCadastroResponseDTO> findById(@PathVariable Long id) {
        DadosCadastroResponseDTO cadastroResponseDTO = cadastroService.findById(id);
        return ResponseEntity.ok().body(cadastroResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<DadosCadastroResponseDTO>> findAll() {
        List<DadosCadastroResponseDTO> list = cadastroService.findAll();
        return ResponseEntity.ok().body(list);
    }

}
