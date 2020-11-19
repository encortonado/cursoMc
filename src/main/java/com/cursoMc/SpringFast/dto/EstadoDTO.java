package com.cursoMc.SpringFast.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.cursoMc.SpringFast.domain.States;



public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	
	@NotEmpty(message = "Preenchimento Obrigatório!")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 Caracteres.")
	private String nome;


	public EstadoDTO() {
		
	}
	
	
	
	public EstadoDTO(States estado) {
		super();
		id = estado.getId();
		nome = estado.getNome();
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
