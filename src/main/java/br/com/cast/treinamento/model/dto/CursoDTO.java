package br.com.cast.treinamento.model.dto;

import java.io.Serializable;

public class CursoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3383467812051544117L;

	private Long id;
	private String nome;
	private CategoriaCursoDTO categoriaCursoDTO;
	private int anosDuracao;
	private boolean habilitadoPCD;
	private boolean excluido;

	public CursoDTO() {
		// Default constructor for JPA
	}

	public CursoDTO(Long id, String nome, CategoriaCursoDTO categoriaCursoDTO, int anosDuracao, boolean habilitadoPCD,
			boolean excluido) {
		setId(id);
		setNome(nome);
		setCategoriaCursoDTO(categoriaCursoDTO);
		setAnosDuracao(anosDuracao);
		setHabilitadoPCD(habilitadoPCD);
		setExcluido(excluido);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CategoriaCursoDTO getCategoriaCursoDTO() {
		return categoriaCursoDTO;
	}

	public void setCategoriaCursoDTO(CategoriaCursoDTO categoriaCursoDTO) {
		this.categoriaCursoDTO = categoriaCursoDTO;
	}

	public int getAnosDuracao() {
		return anosDuracao;
	}

	public void setAnosDuracao(int anosDuracao) {
		this.anosDuracao = anosDuracao;
	}

	public boolean isHabilitadoPCD() {
		return habilitadoPCD;
	}

	public void setHabilitadoPCD(boolean habilitadoPCD) {
		this.habilitadoPCD = habilitadoPCD;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

}
