package com.desafio.anny.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CepGateway {

	@Autowired
	private CepClient client;
	
	public CepResponse consultaCep(String cepRequest) {
		try {
			CepResponse cep = client.consultarCep(cepRequest);
			return cep;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
		
	}
}
