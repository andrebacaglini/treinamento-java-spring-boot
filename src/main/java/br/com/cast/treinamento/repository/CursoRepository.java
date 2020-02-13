package br.com.cast.treinamento.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cast.treinamento.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

	Curso findByNomeAndExcluido(String nome, boolean excluido);

	Page<Curso> findByNomeContaining(String nome, Pageable paginavel);

	Page<Curso> findByNomeContainingAndExcluido(String nome, boolean excluido, Pageable paginavel);

	Curso findByNomeAndCategoriaId(String nome, Long idCategoria);

	List<Curso> findAllByCategoriaId(Long idCategoriaCurso);

	boolean existsByCategoriaId(Long idCategoriaCurso);

}
