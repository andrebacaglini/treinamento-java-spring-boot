package br.com.cast.treinamento.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_log", schema = "treinamento")
public class LogAuditoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2469048142273607703L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "DS_ACAO")
	private String acao;

	@Column(name = "DT_DATA_HORA")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;

	@Column(name = "NM_USUARIO")
	private String usuario;

	@OneToOne
	@JoinColumn(name = "ID_CURSO")
	private Curso curso;

	public LogAuditoria() {
		// Default Constructor for JPA
	}

	public LogAuditoria(String acao, Date dataHora, String usuario, Curso curso) {		
		setAcao(acao);
		setDataHora(dataHora);
		setUsuario(usuario);
		setCurso(curso);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
