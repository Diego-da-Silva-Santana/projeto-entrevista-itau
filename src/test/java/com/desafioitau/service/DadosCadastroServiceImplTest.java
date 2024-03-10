package com.desafioitau.service;

import com.desafioitau.dto.request.DadosCadastroRequestDTO;
import com.desafioitau.dto.response.DadosCadastroResponseDTO;
import com.desafioitau.entities.DadosCadastro;
import com.desafioitau.exceptions.ResourceNotFoundException;
import com.desafioitau.repositories.DadosCadastroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void cadastrar_QuandoReceberRequisicaoEOCpfNaoExiste_DeveRetornarDadosCadastroResponseDTO() {
        DadosCadastroRequestDTO requestDTO = new DadosCadastroRequestDTO("Diego da Silva Santana", "417.701.438-90", 36, "Brasil");
        DadosCadastro cadastro = new DadosCadastro("Diego da Silva Santana", "417.701.438-90", 36, "Brasil");

        when(cadastroRepository.existsBycpf(requestDTO.getCpf())).thenReturn(false);
        when(cadastroRepository.save(any(DadosCadastro.class))).thenReturn(cadastro);
        DadosCadastroResponseDTO responseDTO = dadosCadastroService.cadastrar(requestDTO);
        assertNotNull(responseDTO);
    }

//    @Test
//    void cadastrar_QuandoReceberRequisicaoEOCpfJaExiste_DeveLancarException() {
//        DadosCadastroRequestDTO cadastro = new DadosCadastroRequestDTO("Diego da Silva Santana", "417.701.438-90", 36, "Brasil");
//
//        when(cadastroRepository.existsBycpf(cadastro.getCpf())).thenReturn(true);
//
//        assertThrows(ResourceNotFoundException.class, () -> dadosCadastroService.cadastrar(cadastro));
//    }

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