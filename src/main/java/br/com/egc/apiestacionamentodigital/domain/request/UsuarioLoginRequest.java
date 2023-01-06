package br.com.egc.apiestacionamentodigital.domain.request;

import lombok.Data;

@Data
public class UsuarioLoginRequest {

	private String cpfCnpj;
	private String senha;

}
