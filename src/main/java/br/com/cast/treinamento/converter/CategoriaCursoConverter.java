package br.com.cast.treinamento.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.cast.treinamento.model.CategoriaCurso;
import br.com.cast.treinamento.model.dto.CategoriaCursoDTO;

@Component
public class CategoriaCursoConverter {
	
	
	public List<CategoriaCursoDTO> toEntityToDto(List<CategoriaCurso> lista){
		List<CategoriaCursoDTO> listaRetorno = new ArrayList<>();
		lista.forEach(item -> {
			CategoriaCursoDTO dto = new CategoriaCursoDTO();
			dto.setId(item.getId());
			dto.setDescricao(item.getDescricao());
			listaRetorno.add(dto);
		});
		
		return listaRetorno;
	}

	public CategoriaCurso toDtoToEntity(CategoriaCursoDTO categoriaCursoDTO) {
		return new CategoriaCurso(categoriaCursoDTO.getId(), categoriaCursoDTO.getDescricao());		
	}

	public CategoriaCursoDTO toEntityToDto(CategoriaCurso entity) {
		return new CategoriaCursoDTO(entity.getId(),entity.getDescricao());

	}

}
