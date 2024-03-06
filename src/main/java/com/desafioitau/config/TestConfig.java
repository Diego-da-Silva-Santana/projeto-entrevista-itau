package com.desafioitau.config;

import com.desafioitau.entities.DadosCadastro;
import com.desafioitau.repositories.DadosCadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private DadosCadastroRepository cadastroRepository;

    @Override
    public void run(String... args) throws Exception {

        DadosCadastro c1 = new DadosCadastro("Diego da Silva Santana", "417.701.438-90", 36, "Brasil");
        DadosCadastro c2 = new DadosCadastro("Pedro da Silva Santana", "208.941.580-07", 33, "Argentina");
        DadosCadastro c3 = new DadosCadastro("Joao da Silva Santana", "556.448.560-20", 39, "Chile");


        cadastroRepository.saveAll(Arrays.asList(c1, c2, c3));
    }
}
