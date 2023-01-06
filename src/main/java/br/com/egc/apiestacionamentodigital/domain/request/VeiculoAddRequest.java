package br.com.egc.apiestacionamentodigital.domain.request;

import lombok.Data;

@Data
public class VeiculoAddRequest {

	private String placa;
	private String modelo;
	private boolean favorito;
	private String tipoVeiculo;

}
