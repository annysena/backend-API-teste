package com.desafio.anny.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pessoas")
public class Pessoa {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;

	private String cpf;

	private String rg;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Endereco> enderecos = new HashSet<>();


	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public Pessoa(String nome, String cpf, String rg, Set<Endereco> novosEnderecos) {
		novosEnderecos.forEach(end -> end.setPessoa(this));
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.enderecos.addAll(novosEnderecos);
	}

	public Pessoa(String nome, String cpf, String rg) {
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
	}

	public Pessoa() {
	}
}
