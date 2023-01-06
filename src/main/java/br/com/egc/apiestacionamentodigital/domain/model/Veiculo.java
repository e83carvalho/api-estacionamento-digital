package br.com.egc.apiestacionamentodigital.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table
public class Veiculo {

	public Veiculo() {
	}

	public Veiculo(String placa, String modelo, boolean favorito, TipoVeiculo tipoVeiculo, Usuario usuario) {
		this.placa = placa;
		this.modelo = modelo;
		this.favorito = favorito;
		this.tipoVeiculo = tipoVeiculo;
		this.usuario = usuario;
		this.uuidVeiculo = UUID.randomUUID().toString();

	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	@CreationTimestamp
	private LocalDateTime dataCriacao;

	@UpdateTimestamp
	private LocalDateTime dataAtualizacao;

	@Column(unique = true, nullable = false)
	private String uuidVeiculo;

	@Column(unique = true, nullable = false)
	private String placa;

	private String modelo;

	private boolean favorito;

	@Enumerated(EnumType.STRING)
	private TipoVeiculo tipoVeiculo;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

}
