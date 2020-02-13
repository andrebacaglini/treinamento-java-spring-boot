package br.com.cast.treinamento.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cast.treinamento.model.CategoriaCurso;
import br.com.cast.treinamento.model.Curso;
import br.com.cast.treinamento.model.dto.CategoriaCursoDTO;
import br.com.cast.treinamento.model.dto.CursoDTO;

@Component
public class CursoConverter {

	@Autowired
	private CategoriaCursoConverter categoriaConverter;

	public List<CursoDTO> toEntityToDto(List<Curso> lista) {
		List<CursoDTO> retorno = new ArrayList<>();
		lista.forEach(item -> {

			CursoDTO cursoDTO = new CursoDTO();
			cursoDTO.setId(item.getId());
			cursoDTO.setNome(item.getNome());
			cursoDTO.setCategoriaCursoDTO(categoriaConverter.toEntityToDto(item.getCategoria()));
			cursoDTO.setAnosDuracao(item.getAnosDuracao());
			cursoDTO.setHabilitadoPCD(item.isHabilitadoPCD());
			cursoDTO.setExcluido(item.isExcluido());

			retorno.add(cursoDTO);

		});

		return retorno;
	}

	public Curso toDtoToEntity(CursoDTO cursoDTO) {

		CategoriaCurso cc = categoriaConverter.toDtoToEntity(cursoDTO.getCategoriaCursoDTO());

		return new Curso(cursoDTO.getId(), cursoDTO.getNome(), cc, cursoDTO.getAnosDuracao(),
				cursoDTO.isHabilitadoPCD());
	}

	public CursoDTO toEntityToDto(Curso cursoEntity) {

		CategoriaCursoDTO ccDTO = categoriaConverter.toEntityToDto(cursoEntity.getCategoria());

		return new CursoDTO(cursoEntity.getId(), cursoEntity.getNome(), ccDTO, cursoEntity.getAnosDuracao(),
				cursoEntity.isHabilitadoPCD(), cursoEntity.isExcluido());

	}
}
