package br.com.cast.treinamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.cast.treinamento.converter.CursoConverter;
import br.com.cast.treinamento.enums.EnumAcoes;
import br.com.cast.treinamento.exceptions.BadRequestException;
import br.com.cast.treinamento.exceptions.NotFoundException;
import br.com.cast.treinamento.model.Curso;
import br.com.cast.treinamento.model.dto.CursoDTO;
import br.com.cast.treinamento.model.dto.PaginacaoDTO;
import br.com.cast.treinamento.repository.CursoRepository;

@Service
public class CursoService extends BaseService {

	@Autowired
	private CursoConverter converter;

	@Autowired
	private CursoRepository repository;

	public List<CursoDTO> consultarTodos() {
		List<Curso> lista = repository.findAll();
		if (lista.isEmpty()) {
			throw new NotFoundException("Nenhum registro retornado!");
		}
		return converter.toEntityToDto(lista);
	}

	@Transactional
	public CursoDTO salvar(CursoDTO cursoDTO) {

		if (validarCursoExistente(cursoDTO)) {
			throw new BadRequestException("Já existe um curso com este nome e categoria.");
		}
		if (validarDuracaoCurso(cursoDTO)) {
			throw new BadRequestException("O curso não pode ter mais que 5 anos de duração.");
		}

		Curso curso = converter.toDtoToEntity(cursoDTO);

		curso = repository.save(curso);

		inserirLogAuditoria(cursoDTO.getId() == 0 ? EnumAcoes.INCLUIR : EnumAcoes.ALTERAR, curso);

		return converter.toEntityToDto(curso);
	}

	@Transactional
	public CursoDTO excluir(long idCurso) {
		Optional<Curso> optionalCurso = repository.findById(idCurso);

		if (!optionalCurso.isPresent()) {
			throw new NotFoundException("Id não encontrado para exclusão");
		}

		Curso curso = optionalCurso.get();
		curso.setExcluido(true);
		curso = repository.save(curso);
		inserirLogAuditoria(EnumAcoes.EXCLUIR, curso);

		return converter.toEntityToDto(curso);
	}

	public Page<CursoDTO> consultarPaginado(PaginacaoDTO<CursoDTO> filtroPaginado) {

		Pageable paginavel = PageRequest.of(filtroPaginado.getNumeroPagina(), filtroPaginado.getQtdPorPagina());

		Page<Curso> paginasEntidade;

		if (StringUtils.isEmpty(filtroPaginado.getEntidadeFiltrada().getNome())) {
			paginasEntidade = repository.findAll(paginavel);
		} else {
			paginasEntidade = repository.findByNomeContaining(filtroPaginado.getEntidadeFiltrada().getNome(),
					paginavel);
		}

		if (!paginasEntidade.hasContent()) {
			throw new NotFoundException("Nenhum registro retornado.");
		}

		return new PageImpl<>(converter.toEntityToDto(paginasEntidade.getContent()));
	}

	private boolean validarDuracaoCurso(CursoDTO cursoDTO) {
		return cursoDTO.getAnosDuracao() > 5;
	}

	private boolean validarCursoExistente(CursoDTO cursoDTO) {

		Curso curso = repository.findByNomeAndCategoriaId(cursoDTO.getNome(), cursoDTO.getCategoriaCursoDTO().getId());
		return curso != null && !curso.getId().equals(cursoDTO.getId());
	}

}
