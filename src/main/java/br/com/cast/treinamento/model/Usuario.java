package br.com.cast.treinamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_usuario", schema = "treinamento")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8094151844369206797L;

	public Usuario() {
		// Default Constructor for JPA
	}

	public Usuario(Long id, String email, String nome, String senha) {
		setId(id);
		setEmail(email);
		setNome(nome);
		setSenha(senha);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "DS_EMAIL")
	private String email;

	@Column(name = "NM_NOME")
	private String nome;

	@Column(name = "DS_SENHA")
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
