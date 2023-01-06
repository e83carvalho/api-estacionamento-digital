package br.com.egc.apiestacionamentodigital.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table
public class Usuario {

	public Usuario(String nome, String cpfCnpj, String email, String senha, StatusUsuario status) {
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.senha = senha;
		this.status = status;
		this.uuidUsuario = UUID.randomUUID().toString();

	}

	public Usuario() {

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
	private String uuidUsuario;

	@Column(unique = true, nullable = false)
	private String cpfCnpj;

	@Column(unique = true, nullable = false)
	private String email;

	private String nome;

	private String senha;

	@Enumerated(EnumType.STRING)
	private StatusUsuario status;

	@Transient
	private List<Veiculo> listVeiculo;

}
