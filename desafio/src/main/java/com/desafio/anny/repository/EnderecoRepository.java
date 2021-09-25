package com.desafio.anny.repository;

import com.desafio.anny.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	public List<Endereco> findAllByCepContainingIgnoreCase (String cep);
}
