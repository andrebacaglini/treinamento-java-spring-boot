package br.com.cast.treinamento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cast.treinamento.model.Usuario;
import br.com.cast.treinamento.repository.UsuarioRespository;
import br.com.cast.treinamento.security.AuthorizationUserDetail;

@Service
public class AuthenticationUserService implements UserDetailsService {

	@Autowired
	private UsuarioRespository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optional = repository.findByEmail(username);

		if (optional.isPresent()) {
			return new AuthorizationUserDetail(optional.get().getNome(), optional.get().getSenha());
		}

		return new AuthorizationUserDetail();
	}

}
