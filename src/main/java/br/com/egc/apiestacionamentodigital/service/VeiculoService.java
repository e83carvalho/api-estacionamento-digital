package br.com.egc.apiestacionamentodigital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.egc.apiestacionamentodigital.domain.model.Veiculo;
import br.com.egc.apiestacionamentodigital.repository.VeiculoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	public void save(Veiculo veiculo) {
		try {
			log.info("Salvando ve�culo");

			veiculoRepository.save(veiculo);

		} catch (DataIntegrityViolationException e) {
			log.error("Tratando viola��o de chave, tentativa de incluir um novo ve�culo com mesma placa");
		}

	}

	public void edit(Veiculo veiculo) {
		log.info("Editando ve�culo");

		veiculoRepository.save(veiculo);
	}

	public void delete(Veiculo veiculo) {
		log.info("Deletando ve�culo");

		veiculoRepository.delete(veiculo);
	}

	public List<Veiculo> findByUsuarioId(Long usuarioId) {
		log.info("Buscando ve�culo por usu�rio");


		List<Veiculo> listVeiculo = veiculoRepository.findByUsuarioId(usuarioId);

		return listVeiculo;
	}

	public Veiculo findById(Long id) {
		log.info("Buscando ve�culo");

		Optional<Veiculo> usuarioOptional = veiculoRepository.findById(id);

		if (usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		}
		return null;
	}

}
