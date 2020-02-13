package br.com.cast.treinamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_curso", schema = "treinamento")
public class Curso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3481731965679349901L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NM_CURSO")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA")
	private CategoriaCurso categoria;

	@Column(name = "QT_ANOS_DURACAO")
	private int anosDuracao;

	@Column(name = "IN_HABILITADO_PCD")
	private boolean habilitadoPCD;

	@Column(name = "IN_EXCLUIDO")
	private boolean excluido;

	public Curso() {
		// Defaul constructor for JPA
	}

	public Curso(Long id, String nome, CategoriaCurso categoriaCurso, int anosDuracao, boolean habilitadoPCD) {
		setId(id);
		setNome(nome);
		setCategoria(categoriaCurso);
		setAnosDuracao(anosDuracao);
		setHabilitadoPCD(habilitadoPCD);
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

	public CategoriaCurso getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaCurso categoria) {
		this.categoria = categoria;
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
