package com.desafioitau.controller;

import com.desafioitau.dto.request.DadosCadastroRequestDTO;
import com.desafioitau.dto.response.DadosCadastroResponseDTO;
import com.desafioitau.service.DadosCadastroServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DadosCadastroController.class)
class DadosCadastroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DadosCadastroServiceImpl cadastroService;

    @Test
    void detalharCadastro_QuandoReceberRequisicaoComParametroId_DeveRetornar200ComDadosDoCadastroReferenteAoId() throws Exception {
        DadosCadastroResponseDTO responseDTO = new DadosCadastroResponseDTO(1L, "Diego da Silva Santana", "417.701.438-90", 36, "Brasil");

        when(cadastroService.detalharCadastro(1L)).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/cadastros/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Diego da Silva Santana"))
                .andExpect(jsonPath("$.cpf").value("417.701.438-90"))
                .andExpect(jsonPath("$.idade").value(36))
                .andExpect(jsonPath("$.pais").value("Brasil"));
    }

    @Test
    void listarCadastros_QuandoReceberRequisicao_DeveRetornar200ComListaDeCadastros() throws Exception {
        DadosCadastroResponseDTO responseDTO1 = new DadosCadastroResponseDTO(1L, "Diego da Silva Santana", "417.701.438-90", 36, "Brasil");
        DadosCadastroResponseDTO responseDTO2 = new DadosCadastroResponseDTO(2L, "Pedro da Silva Santana", "208.941.580-07", 33, "Argentina");

        when(cadastroService.listarCadastros()).thenReturn(Arrays.asList(responseDTO1, responseDTO2));

        mockMvc.perform(MockMvcRequestBuilders.get("/cadastros")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome").value("Diego da Silva Santana"))
                .andExpect(jsonPath("$[0].cpf").value("417.701.438-90"))
                .andExpect(jsonPath("$[0].idade").value(36))
                .andExpect(jsonPath("$[0].pais").value("Brasil"))
                .andExpect(jsonPath("$[1].nome").value("Pedro da Silva Santana"))
                .andExpect(jsonPath("$[1].cpf").value("208.941.580-07"))
                .andExpect(jsonPath("$[1].idade").value(33))
                .andExpect(jsonPath("$[1].pais").value("Argentina"));
    }

    @Test
    void cadastrar_QuandoReceberRequisicaoComDadosCadastro_deveRetornar201Created() throws Exception {
        DadosCadastroRequestDTO requestDTO = new DadosCadastroRequestDTO("Diego da Silva Santana", "417.701.438-90", 36, "Brasil");
        DadosCadastroResponseDTO responseDTO = new DadosCadastroResponseDTO(1L, "Diego da Silva Santana", "417.701.438-90", 36, "Brasil");

        when(cadastroService.cadastrar(any(DadosCadastroRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/cadastros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(requestDTO.getNome()))
                .andExpect(jsonPath("$.cpf").value(requestDTO.getCpf()))
                .andExpect(jsonPath("$.idade").value(requestDTO.getIdade()))
                .andExpect(jsonPath("$.pais").value(requestDTO.getPais()));

        verify(cadastroService, times(1)).cadastrar(any(DadosCadastroRequestDTO.class));
    }

    @Test
    void atualizarDados_QuandoReceberRequisicaoComDados_deveRetonar200comDadosAtualizado() throws Exception {
        DadosCadastroRequestDTO requestDTO = new DadosCadastroRequestDTO("Diego da Silva Santana", "417.701.438-90", 36, "Brasil");
        DadosCadastroResponseDTO responseDTO = new DadosCadastroResponseDTO(1L, "Diego da Silva Santana", "417.701.438-90", 36, "Brasil");

        Mockito.when(cadastroService.atualizarDados(Mockito.anyLong(), Mockito.any(DadosCadastroRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(patch("/cadastros/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pais").value(requestDTO.getPais()))
                .andExpect(jsonPath("$.idade").value(requestDTO.getIdade()));

        verify(cadastroService, Mockito.times(1)).atualizarDados(Mockito.anyLong(), Mockito.any(DadosCadastroRequestDTO.class));
    }

    @Test
    void deletarCadastro_QuandoRecebeberRequisicaocomParametroId_deveRetornar204NoContent() throws Exception {
        Long cadastroId = 1L;
        doNothing().when(cadastroService).deletarCadastro(cadastroId);

        mockMvc.perform(delete("/cadastros/{id}", cadastroId))
                .andExpect(status().isNoContent());

        verify(cadastroService, times(1)).deletarCadastro(cadastroId);
    }
}