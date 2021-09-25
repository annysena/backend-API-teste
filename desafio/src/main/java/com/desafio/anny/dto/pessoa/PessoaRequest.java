package com.desafio.anny.dto.pessoa;

import com.desafio.anny.dto.endereco.EnderecoRequest;
import com.desafio.anny.model.Endereco;
import com.desafio.anny.model.Pessoa;

import java.util.Set;

public class PessoaRequest {

	private String nome;

	private String cpf;

	private String rg;

	private Set<EnderecoRequest> enderecos;

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public Set<EnderecoRequest> getEnderecos() {
		return enderecos;
	}
	
	public Pessoa paraEntidade(Set<Endereco> enderecosRequest) {
		if(enderecosRequest != null) {
			return new Pessoa(nome, cpf, rg, enderecosRequest);
		}
		return new Pessoa(nome, cpf, rg);
	}

}
