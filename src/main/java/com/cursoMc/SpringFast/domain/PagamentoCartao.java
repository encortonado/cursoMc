package com.cursoMc.SpringFast.domain;

import javax.persistence.Entity;

import com.cursoMc.SpringFast.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoCartao")
public class PagamentoCartao extends Pagamento{
	private static final long serialVersionUID = 1L;

	private Integer numeroParcel;
	
	public PagamentoCartao() {
		
	}

	public PagamentoCartao(Integer id, EstadoPagamento status, Pedido pedido, Integer numeroParcel) {
		super(id, status, pedido);
		this.numeroParcel = numeroParcel;
	}

	public Integer getNumeroParcel() {
		return numeroParcel;
	}

	public void setNumeroParcel(Integer numeroParcel) {
		this.numeroParcel = numeroParcel;
	}
	
	
	
	
}
