package br.com.egc.apiestacionamentodigital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.egc.apiestacionamentodigital.domain.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	List<Veiculo> findByUsuarioId(Long usuarioId);

}
