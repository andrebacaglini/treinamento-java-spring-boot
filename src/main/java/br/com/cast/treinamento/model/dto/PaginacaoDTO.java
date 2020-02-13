package br.com.cast.treinamento.model.dto;

import java.io.Serializable;

public class PaginacaoDTO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6282566950025852591L;

	private Integer qtdPorPagina;
	private Integer numeroPagina;
	private String ordernarPor;
	private T entidadeFiltrada;

	public Integer getQtdPorPagina() {
		return qtdPorPagina;
	}

	public void setQtdPorPagina(Integer qtdPorPagina) {
		this.qtdPorPagina = qtdPorPagina;
	}

	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(Integer numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public String getOrdernarPor() {
		return ordernarPor;
	}

	public void setOrdernarPor(String ordernarPor) {
		this.ordernarPor = ordernarPor;
	}

	public T getEntidadeFiltrada() {
		return entidadeFiltrada;
	}

	public void setEntidadeFiltrada(T entidadeFiltrada) {
		this.entidadeFiltrada = entidadeFiltrada;
	}

}
