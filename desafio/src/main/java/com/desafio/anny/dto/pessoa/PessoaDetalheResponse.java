package com.desafio.anny.dto.pessoa;

import com.desafio.anny.dto.endereco.EnderecoResponse;
import com.desafio.anny.model.Pessoa;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;
import java.util.stream.Collectors;

public class PessoaDetalheResponse {

    private long id;
    private String nome;
    private String cpf;
    private String rg;
    private Set<EnderecoResponse> enderecos;

    public PessoaDetalheResponse(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.cpf = pessoa.getCpf();
        this.rg = pessoa.getRg();
        if(!pessoa.getEnderecos().isEmpty()){
            this.enderecos = pessoa.getEnderecos().stream().map(end -> new EnderecoResponse(end)).collect(Collectors.toSet());
        }
    }

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

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Set<EnderecoResponse> getEnderecos() {
        return enderecos;
    }
}
