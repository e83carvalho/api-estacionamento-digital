package br.com.egc.apiestacionamentodigital.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.egc.apiestacionamentodigital.domain.model.Usuario;
import br.com.egc.apiestacionamentodigital.domain.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioConverter {

	private final ModelMapper modelMapper;

	public UsuarioResponse toUsuarioResponse(Usuario usuario) {
		return this.modelMapper.map(usuario, UsuarioResponse.class);
	}

	public List<UsuarioResponse> toListUsuarioResponse(List<Usuario> listUsuario) {
		return listUsuario.stream().map(this::toUsuarioResponse).collect(Collectors.toList());
	}

}
