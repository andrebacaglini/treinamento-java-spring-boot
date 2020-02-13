package br.com.cast.treinamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cast.treinamento.model.Usuario;

public interface UsuarioRespository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String username);

}
