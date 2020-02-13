package br.com.cast.treinamento.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.cast.treinamento.model.CategoriaCurso;

public interface CategoriaCursoCustomRepository {
	
	Page<CategoriaCurso> findByDescricao(String descricao, Pageable paginavel);

}
