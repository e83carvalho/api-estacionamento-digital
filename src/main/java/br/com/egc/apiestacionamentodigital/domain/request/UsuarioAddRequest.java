package br.com.egc.apiestacionamentodigital.domain.request;

import lombok.Data;

@Data
public class UsuarioAddRequest {

	private String cpfCnpj;
	private String nome;
	private String email;
	private String senha;

}
