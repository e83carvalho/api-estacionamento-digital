package br.com.egc.apiestacionamentodigital.domain.response;

import br.com.egc.apiestacionamentodigital.domain.model.TipoVeiculo;
import lombok.Data;

@Data
public class VeiculoResponse {

	private String uuidVeiculo;

	private String placa;

	private String modelo;

	private boolean favorito;

	private TipoVeiculo tipoVeiculo;
}
