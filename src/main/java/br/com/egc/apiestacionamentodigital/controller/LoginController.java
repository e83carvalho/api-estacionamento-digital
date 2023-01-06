package br.com.egc.apiestacionamentodigital.controller;

import java.util.UUID;

import br.com.egc.apiestacionamentodigital.converter.UsuarioConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.egc.apiestacionamentodigital.domain.model.Usuario;
import br.com.egc.apiestacionamentodigital.domain.request.UsuarioLoginRequest;
import br.com.egc.apiestacionamentodigital.domain.response.UsuarioResponse;
import br.com.egc.apiestacionamentodigital.service.UsuarioService;

@RestController
@RequestMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioConverter usuarioConverter;

	@PostMapping
	public UsuarioResponse login(@RequestBody UsuarioLoginRequest usuarioLoginRequest) throws Exception {

		Usuario usuario = usuarioService.login(usuarioLoginRequest);

		UsuarioResponse usuarioResponse = usuarioConverter.toUsuarioResponse(usuario);
		usuarioResponse.setToken(UUID.randomUUID().toString());
			
		return usuarioResponse;

	}

}
