package br.com.egc.apiestacionamentodigital.domain.response;

import java.util.List;

import lombok.Data;

@Data
public class UsuarioResponse {

	private String uuidUsuario;
	private String cpfCnpj;
	private String nome;
	private String email;
	private String status;
	private String token;
	
	private List<VeiculoResponse> listVeiculo;

}
