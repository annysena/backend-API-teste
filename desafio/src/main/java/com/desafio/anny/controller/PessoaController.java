package com.desafio.anny.controller;


import com.desafio.anny.client.CepGateway;
import com.desafio.anny.dto.pessoa.PessoaDetalheResponse;
import com.desafio.anny.dto.pessoa.PessoaRequest;
import com.desafio.anny.dto.pessoa.PessoaResponse;
import com.desafio.anny.model.Endereco;
import com.desafio.anny.model.Pessoa;
import com.desafio.anny.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	PessoaRepository repository;
	
	@Autowired
	CepGateway gateway;

	@GetMapping("/todas")
	public ResponseEntity<List<PessoaDetalheResponse>> consultarPessoas() {
		List<PessoaDetalheResponse> pessoas = repository.findAll()
				.stream()
				.map(pessoa -> new PessoaDetalheResponse(pessoa))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(pessoas);
	}

	@GetMapping
	public ResponseEntity<PessoaDetalheResponse> consultaParametrizada(@RequestParam(required = false) long id,
																	   @RequestParam(required = false) String cpf,
																	   @RequestParam(required = false) String rg) {
		Optional<Pessoa> pessoa = repository.procurarPessoaPorParametros(id, cpf, rg);

		if(!pessoa.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
		}

		return ResponseEntity.ok().body(new PessoaDetalheResponse(pessoa.get()));
	}

	@PostMapping
	public ResponseEntity<PessoaResponse> cadastrarPessoa(@RequestBody PessoaRequest request) {
		Set<Endereco> enderecos = new HashSet<>();
		if(request.getEnderecos() != null) {
			enderecos = request.getEnderecos().stream().map(enderecoRequest -> {
				Endereco end = new Endereco(gateway.consultaCep(enderecoRequest.getCep()));
				end.setNumero(enderecoRequest.getNumero());
				return end;
			}).collect(Collectors.toSet());
		}
		
		Pessoa pessoa = request.paraEntidade(enderecos);
		pessoa = repository.save(pessoa);
		return ResponseEntity.status(HttpStatus.CREATED).body(new PessoaResponse(pessoa));
	}
	
}
