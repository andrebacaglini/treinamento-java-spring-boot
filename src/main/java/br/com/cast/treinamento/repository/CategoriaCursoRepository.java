package br.com.cast.treinamento.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cast.treinamento.model.CategoriaCurso;

@Repository
public interface CategoriaCursoRepository extends JpaRepository<CategoriaCurso, Long>, CategoriaCursoCustomRepository {
	
	CategoriaCurso findByDescricao(String descricao);
	
	Page<CategoriaCurso> findByDescricao(String descricao, Pageable paginavel);	
	
	@Query("SELECT cc "
			+ "FROM CategoriaCurso cc "
			+ "WHERE cc.descricao = :descricao AND cc.id != :id")
	CategoriaCurso validarDescricaoAlteracao(String descricao, Long id);

	

}
