package br.com.egc.apiestacionamentodigital.repository;

import java.util.Optional;

import br.com.egc.apiestacionamentodigital.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByUuidUsuario(String uuid);

	Optional<Usuario> findByCpfCnpj(String cpfCnpj);

	Optional<Usuario> findByCpfCnpjOrEmail(String cpfCnpj, String email);
	
	

}
