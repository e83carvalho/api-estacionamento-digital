package br.com.egc.apiestacionamentodigital.domain.request;

import lombok.Data;

@Data
public class UsuarioEditRequest {

	private String nome;
	private String senha;

}
