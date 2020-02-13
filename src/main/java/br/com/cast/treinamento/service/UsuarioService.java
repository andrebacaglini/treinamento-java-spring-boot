package br.com.cast.treinamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.treinamento.converter.UsuarioConverter;
import br.com.cast.treinamento.model.Usuario;
import br.com.cast.treinamento.model.dto.UsuarioDTO;
import br.com.cast.treinamento.repository.UsuarioRespository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRespository repository;

	@Autowired
	private UsuarioConverter converter;

	public List<Usuario> consultarTodos() {
		return repository.findAll();
	}

	public void incluir(UsuarioDTO userDTO) {
		repository.save(converter.toDtoToEntity(userDTO));
	}

}
