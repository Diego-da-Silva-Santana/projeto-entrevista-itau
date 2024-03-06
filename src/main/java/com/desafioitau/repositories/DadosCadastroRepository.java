package com.desafioitau.repositories;

import com.desafioitau.entities.DadosCadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosCadastroRepository extends JpaRepository<DadosCadastro, Long> {
}
