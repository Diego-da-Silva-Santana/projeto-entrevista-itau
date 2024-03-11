package com.desafioitau.service;

import com.desafioitau.dto.request.DadosCadastroRequestDTO;
import com.desafioitau.dto.response.DadosCadastroResponseDTO;
import com.desafioitau.entities.DadosCadastro;
import com.desafioitau.exceptions.ResourceNotFoundException;
import com.desafioitau.exceptions.ResourceNotValidException;
import com.desafioitau.repositories.DadosCadastroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DadosCadastroServiceImplTest {

    @Mock
    private DadosCadastroRepository cadastroRepository;
    @InjectMocks
    private DadosCadastroServiceImpl dadosCadastroService;

    @Test
    void detalharCadastro_QuandoExisterCadastroComId_deveRetornarOsDadosdesseCadastro() {
        Long id = 1L;
        DadosCadastro dadosCadastro = new DadosCadastro("Diego da Silva Santana", "417.701.438-90", 36, "Brasil");
        when(cadastroRepository.findById(id)).thenReturn(Optional.of(dadosCadastro));

        dadosCadastroService.detalharCadastro(id);
        verify(cadastroRepository, times(1)).findById(id);
    }

    @Test
    void cadastrar_QuandoReceberRequisicaoEOCpfNaoExisteNaBaseDados_DeveCadastrarERetornarDadosCadastroResponseDTO() {
        DadosCadastroRequestDTO requestDTO = new DadosCadastroRequestDTO("Diego da Silva Santana", "417.701.438-90", 36, "Brasil");
        DadosCadastro cadastro = new DadosCadastro("Diego da Silva Santana", "417.701.438-90", 36, "Brasil");

        when(cadastroRepository.existsBycpf(requestDTO.getCpf())).thenReturn(false);
        when(cadastroRepository.save(any(DadosCadastro.class))).thenReturn(cadastro);
        DadosCadastroResponseDTO responseDTO = dadosCadastroService.cadastrar(requestDTO);

        assertNotNull(responseDTO);
    }

    @Test
    void listarCadastros_QuandoReceberRequisicao_DeveRetornarListaDeDadosCadastroResponseDTO() {
        DadosCadastro dadosCadastro1 = new DadosCadastro("Diego da Silva Santana", "417.701.438-90", 36, "Brasil");
        DadosCadastro dadosCadastro2 = new DadosCadastro("Pedro da Silva Santana", "208.941.580-07", 33, "Argentina");
        when(cadastroRepository.findAll()).thenReturn(Arrays.asList(dadosCadastro1, dadosCadastro2));
        List<DadosCadastroResponseDTO> responseDTOs = dadosCadastroService.listarCadastros();

        assertNotNull(responseDTOs);
        assertEquals(2, responseDTOs.size());
    }

    @Test
    void cadastrar_QuandoReceberRequisicaoEOCpfJaExiste_DeveLancarException() {
        DadosCadastroRequestDTO cadastro = new DadosCadastroRequestDTO("Diego da Silva Santana", "417.701.438-90", 36, "Brasil");

        when(cadastroRepository.existsBycpf(cadastro.getCpf())).thenReturn(true);

        assertThrows(ResourceNotValidException.class, () -> dadosCadastroService.cadastrar(cadastro));
    }

    @Test
    void atualizarDados_QuandoIdExiste_DeveRetornarDadosAtualizados() {
        Long id = 1L;
        DadosCadastro dadosCadastro = new DadosCadastro("Diego da Silva Santana", "417.701.438-90", 36, "Brasil");
        DadosCadastroRequestDTO requestDTO = new DadosCadastroRequestDTO("Diego da Silva Santana", "417.701.438-90", 36, "Brasil");

        when(cadastroRepository.findById(id)).thenReturn(Optional.of(dadosCadastro));
        when(cadastroRepository.save(any(DadosCadastro.class))).thenAnswer(i -> i.getArguments()[0]);

        DadosCadastroResponseDTO responseDTO = dadosCadastroService.atualizarDados(id, requestDTO);

        assertNotNull(responseDTO);
        assertEquals(36, dadosCadastro.getIdade());
        assertEquals("Brasil", dadosCadastro.getPais());
    }

    @Test
    void atualizarDados_QuandoIdNaoExiste_DeveLancarException() {
        Long id = 1L;
        DadosCadastroRequestDTO requestDTO = new DadosCadastroRequestDTO("Diego da Silva Santana", "417.701.438-90", 36, "Brasil");

        when(cadastroRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> dadosCadastroService.atualizarDados(id, requestDTO));
    }

    @Test
    void deletarCadastro_QuandoReceberRequisicaoComIdComoParametroENaoExistir_DeveLancarException() {
        Long id = 1L;
        when(cadastroRepository.existsById(id)).thenReturn(true);
        dadosCadastroService.deletarCadastro(id);

        Mockito.verify(cadastroRepository, times(1)).deleteById(id);
    }

    @Test
    public void deletarCadastro_QuandoReceberRequisicaoComIdECarroNaoExistir_DeveLancarException() {
        Long id = 1L;
        when(cadastroRepository.existsById(id)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> {
            dadosCadastroService.deletarCadastro(id);
        });

        Mockito.verify(cadastroRepository, times(0)).deleteById(id);
    }

    @Test
    void detalharCadastro_QuandoNaoExistirCadastroComId_DeveLancarException() {
        Long id = 1L;
        when(cadastroRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            dadosCadastroService.detalharCadastro(id);
        });

        verify(cadastroRepository, times(1)).findById(id);
    }
}