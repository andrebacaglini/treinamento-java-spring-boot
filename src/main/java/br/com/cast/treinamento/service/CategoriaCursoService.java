package br.com.cast.treinamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cast.treinamento.converter.CategoriaCursoConverter;
import br.com.cast.treinamento.exceptions.BadRequestException;
import br.com.cast.treinamento.exceptions.NotFoundException;
import br.com.cast.treinamento.model.CategoriaCurso;
import br.com.cast.treinamento.model.dto.CategoriaCursoDTO;
import br.com.cast.treinamento.model.dto.PaginacaoDTO;
import br.com.cast.treinamento.repository.CategoriaCursoRepository;
import br.com.cast.treinamento.repository.CursoRepository;

@Service
public class CategoriaCursoService {

	@Autowired
	private CategoriaCursoConverter converter;

	@Autowired
	private CategoriaCursoRepository repository;
	
	@Autowired
	private CursoRepository cursoRepository;

	public List<CategoriaCursoDTO> consultarTodos() {
		List<CategoriaCurso> lista = repository.findAll();
		if (lista.isEmpty()) {
			throw new NotFoundException("Nenhum registro retornado!");
		}

		return converter.toEntityToDto(lista);
	}

	public CategoriaCursoDTO salvar(CategoriaCursoDTO categoriaCursoDTO) {
		CategoriaCurso entity = converter.toDtoToEntity(categoriaCursoDTO);

		if (verificarDescricaoDuplicada(categoriaCursoDTO)) {
			throw new BadRequestException("Descrição informada já existe.");
		}

		return converter.toEntityToDto(repository.save(entity));

	}

	private boolean verificarDescricaoDuplicada(CategoriaCursoDTO categoriaCursoDTO) {
		CategoriaCurso categoriaCurso = repository.findByDescricao(categoriaCursoDTO.getDescricao());
		return categoriaCurso != null && !categoriaCurso.getId().equals(categoriaCursoDTO.getId());
	}

	public void excluir(Long idCategoriaCurso) {

		if (!repository.findById(idCategoriaCurso).isPresent()) {
			throw new NotFoundException("Id não encontrado para exclusão");
		}
		
//		if(!cursoRepository.findAllByCategoriaId(idCategoriaCurso).isEmpty()) {
//			throw new BadRequestException("Não é possível excluir a categoria. Ela está sendo usada por algum curso");
//		}
		
		if(cursoRepository.existsByCategoriaId(idCategoriaCurso)) {
			throw new BadRequestException("Não é possível excluir a categoria. Ela está sendo usada por algum curso");
		}

		repository.deleteById(idCategoriaCurso);
	}

	public Page<CategoriaCursoDTO> consultarPaginado(PaginacaoDTO<CategoriaCursoDTO> filtroPaginado) {

		Pageable paginavel = PageRequest.of(filtroPaginado.getNumeroPagina(), filtroPaginado.getQtdPorPagina());

		Page<CategoriaCurso> paginasEntidade = repository
				.findByDescricao(filtroPaginado.getEntidadeFiltrada().getDescricao(), paginavel);

		if (!paginasEntidade.hasContent()) {
			throw new NotFoundException("Nenhum registro retornado!");
		}

		return new PageImpl<>(converter.toEntityToDto(paginasEntidade.getContent()));
	}
}
