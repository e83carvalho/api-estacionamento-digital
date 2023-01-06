package br.com.egc.apiestacionamentodigital.controller;

import java.util.List;

import br.com.egc.apiestacionamentodigital.converter.UsuarioConverter;
import br.com.egc.apiestacionamentodigital.domain.model.StatusUsuario;
import br.com.egc.apiestacionamentodigital.domain.model.TipoVeiculo;
import br.com.egc.apiestacionamentodigital.domain.model.Usuario;
import br.com.egc.apiestacionamentodigital.domain.model.Veiculo;
import br.com.egc.apiestacionamentodigital.domain.request.UsuarioAddRequest;
import br.com.egc.apiestacionamentodigital.domain.request.UsuarioEditRequest;
import br.com.egc.apiestacionamentodigital.domain.request.VeiculoAddRequest;
import br.com.egc.apiestacionamentodigital.domain.request.VeiculoEditRequest;
import br.com.egc.apiestacionamentodigital.domain.response.UsuarioResponse;
import br.com.egc.apiestacionamentodigital.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.egc.apiestacionamentodigital.service.UsuarioService;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private VeiculoService veiculoService;

	@Autowired
	private UsuarioConverter usuarioConverter;

	@PostMapping
	public UsuarioResponse saveUsuario(@RequestBody UsuarioAddRequest usuarioAddRequest) throws Exception {
		Usuario usuario = new Usuario(usuarioAddRequest.getNome(), usuarioAddRequest.getCpfCnpj(),
				usuarioAddRequest.getEmail(), usuarioAddRequest.getSenha(), StatusUsuario.ATIVO);

		usuarioService.save(usuario);

		return usuarioConverter.toUsuarioResponse(usuario);
	}

	@PatchMapping(path = "/{uuidUsuario}")
	public void editUsuario(@RequestBody UsuarioEditRequest usuarioEditRequest, @PathVariable String uuidUsuario) {
		Usuario usuario = usuarioService.findByUuidUsuario(uuidUsuario);

		if (usuario != null) {
			usuario.setNome(usuarioEditRequest.getNome());
			usuario.setSenha(usuarioEditRequest.getSenha());

			usuarioService.edit(usuario);
		}
	}

	@GetMapping(path = "/{uuidUsuario}")
	public UsuarioResponse findUsuarioByUuid(@PathVariable String uuidUsuario) {
		UsuarioResponse usuarioResponse = usuarioConverter
				.toUsuarioResponse(usuarioService.findByUuidUsuario(uuidUsuario));

		return usuarioResponse;
	}

	@GetMapping
	public List<UsuarioResponse> findAllUsuario() {
		List<UsuarioResponse> listUsuarioResponse = usuarioConverter.toListUsuarioResponse(usuarioService.findAll());

		return listUsuarioResponse;
	}

	@PostMapping(path = "/{uuidUsuario}/veiculos")
	public void saveVeiculo(@RequestBody VeiculoAddRequest veiculoAddRequest, @PathVariable String uuidUsuario) {
		Usuario usuario = usuarioService.findByUuidUsuario(uuidUsuario);

		Veiculo veiculo = new Veiculo(veiculoAddRequest.getPlaca(), veiculoAddRequest.getModelo(),
				veiculoAddRequest.isFavorito(), TipoVeiculo.valueOf(veiculoAddRequest.getTipoVeiculo()), usuario);

		veiculoService.save(veiculo);
	}

	@PatchMapping(path = "/{uuidUsuario}/veiculos/{uuidVeiculo}")
	public void updateVeiculo(@RequestBody VeiculoEditRequest veiculoEditdRequest, @PathVariable String uuidUsuario,
							  @PathVariable String uuidVeiculo) {
		Usuario usuario = usuarioService.findByUuidUsuario(uuidUsuario);

		usuario.getListVeiculo().forEach(obj -> {
			if (obj.getUuidVeiculo().equals(uuidVeiculo)) {
				obj.setPlaca(veiculoEditdRequest.getPlaca());
				obj.setModelo(veiculoEditdRequest.getModelo());
				obj.setFavorito(veiculoEditdRequest.isFavorito());
				obj.setTipoVeiculo(TipoVeiculo.valueOf(veiculoEditdRequest.getTipoVeiculo()));

				veiculoService.edit(obj);

				return;
			}
		});

	}
	
	@DeleteMapping(path = "/{uuidUsuario}/veiculos/{uuidVeiculo}")
	public void deleteVeiculo( @PathVariable String uuidUsuario,
			@PathVariable String uuidVeiculo) {
		Usuario usuario = usuarioService.findByUuidUsuario(uuidUsuario);
		
		usuario.getListVeiculo().forEach(obj -> {
			if (obj.getUuidVeiculo().equals(uuidVeiculo)) {
				veiculoService.delete(obj);
				return;
			}
		});
		
	}

}
