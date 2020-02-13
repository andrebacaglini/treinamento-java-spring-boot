package br.com.cast.treinamento.repository;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.cast.treinamento.model.CategoriaCurso;

public class CategoriaCursoCustomRepositoryImpl implements CategoriaCursoCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Page<CategoriaCurso> findByDescricao(String descricao, Pageable paginavel) {

		HashMap<String, Object> parametros = new HashMap<>();

		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT cc FROM CategoriaCurso cc ");
		sb.append(" WHERE 1 = 1 ");

		if (!StringUtils.isEmpty(descricao)) {
			sb.append(" AND cc.descricao = :descricao ");
			parametros.put("descricao", descricao);
		}

		Query query = entityManager.createQuery(sb.toString());
		parametros.forEach(query::setParameter);
		query.setFirstResult(paginavel.getPageNumber() * paginavel.getPageSize());
		query.setMaxResults(paginavel.getPageSize());

		return new PageImpl<>(query.getResultList());
	}

}
