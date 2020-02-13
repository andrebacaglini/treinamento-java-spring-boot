package br.com.cast.treinamento.converter;

import org.springframework.stereotype.Component;

import br.com.cast.treinamento.model.Usuario;
import br.com.cast.treinamento.model.dto.UsuarioDTO;

@Component
public class UsuarioConverter {

	public UsuarioDTO toEntityToDto(Usuario entity) {
		return new UsuarioDTO(entity.getId(), entity.getEmail(), entity.getNome(), entity.getSenha());
	}

	public Usuario toDtoToEntity(UsuarioDTO userDTO) {
		return new Usuario(userDTO.getId(), userDTO.getEmail(), userDTO.getNome(), userDTO.getSenha());
	}

}
