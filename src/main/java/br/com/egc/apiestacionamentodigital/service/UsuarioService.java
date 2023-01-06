package br.com.egc.apiestacionamentodigital.service;

import br.com.egc.apiestacionamentodigital.domain.dto.EmailDto;
import br.com.egc.apiestacionamentodigital.domain.model.Usuario;
import br.com.egc.apiestacionamentodigital.domain.request.UsuarioLoginRequest;
import br.com.egc.apiestacionamentodigital.exception.ResourceNotFoundException;
import br.com.egc.apiestacionamentodigital.exception.ResourceValidatorException;
import br.com.egc.apiestacionamentodigital.repository.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void save(Usuario usuario) throws Exception {

        log.info("Salvando usuário");

        Optional<Usuario> optionalUsuario = usuarioRepository.findByCpfCnpjOrEmail(usuario.getCpfCnpj(), usuario.getEmail());

        if (!optionalUsuario.isPresent()) {

            try {
                usuarioRepository.save(usuario);
                EmailDto emailDto = new EmailDto();
                emailDto.setName(usuario.getNome());
                emailDto.setEmailTo(usuario.getEmail());
                emailDto.setSubject("Bem Vindo");
                emailDto.setText("Obrigado por se cadastrar...");

                String json = new ObjectMapper().writeValueAsString(emailDto);

                rabbitTemplate.convertAndSend("email", json);

            } catch (DataIntegrityViolationException | JsonProcessingException e) {
                log.error("Erro ao salvar usuario");
                log.error("erro", e);
            }
        } else {
            throw new ResourceValidatorException("Já existe um Usuário cadastrado com os dados informados (cpf/cnpj ou email)");
        }


    }

    public void edit(Usuario usuario) {
        log.info("Editando usuário");

        usuarioRepository.save(usuario);
    }

    public void delete(Usuario usuario) {
        log.info("Deletando usuário");

        usuarioRepository.delete(usuario);
    }

    public Usuario findByUuidUsuario(String uuid) {
        log.info("Buscando usuário");

        Optional<Usuario> usuarioOptional = usuarioRepository.findByUuidUsuario(uuid);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setListVeiculo(veiculoService.findByUsuarioId(usuario.getId()));
            return usuario;
        } else {
            throw new ResourceNotFoundException("Usuário não localizado");
        }

    }

    public List<Usuario> findAll() {
        log.info("Buscando todos os usuários");

        List<Usuario> list = usuarioRepository.findAll();
        for (Usuario usuario : list) {
            usuario.setListVeiculo(veiculoService.findByUsuarioId(usuario.getId()));

        }

        return usuarioRepository.findAll();


    }

    public Usuario login(UsuarioLoginRequest usuarioLoginRequest) throws Exception {

        Optional<Usuario> usuarioOptional = usuarioRepository.findByCpfCnpj(usuarioLoginRequest.getCpfCnpj());

        if (usuarioOptional.isPresent()) {

            Usuario usuario = usuarioOptional.get();

            if (usuario.getSenha().equals(usuarioLoginRequest.getSenha())) {
                return usuario;
            } else {
                log.info("Senha inválida");
                throw new Exception("Senha inválida");
            }

        } else {
            log.info("Usuário não localizado");
            throw new Exception("Usuário não localizado");

        }

    }

}
