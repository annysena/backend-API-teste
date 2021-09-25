package com.desafio.anny.repository;

import com.desafio.anny.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT p FROM Pessoa p WHERE p.id = :id AND (p.cpf = :cpf OR p.rg = :rg)")
    Optional<Pessoa> procurarPessoaPorParametros(@Param("id") long id, @Param("cpf") String cpf, @Param("rg")String rg);
	
}
