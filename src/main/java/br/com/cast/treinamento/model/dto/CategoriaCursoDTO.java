package br.com.cast.treinamento.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoriaCursoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2573992238514718686L;

	private Long id;

	@NotNull(message = "O campo descrição é obrigatório")
	@Size(max = 255, message = "Tamanho não pode ser maior que 255 caracteres.", min = 1)
	private String descricao;

	public CategoriaCursoDTO() {
		// Default constructor
	}

	public CategoriaCursoDTO(Long id, String descricao) {
		setId(id);
		setDescricao(descricao);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
