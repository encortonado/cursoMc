package com.cursoMc.SpringFast.dto;

import java.io.Serializable;

import com.cursoMc.SpringFast.domain.Category;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	
	public CategoriaDTO() {
		
	}

	public CategoriaDTO(Category obj) {
		id = obj.getId();
		nome = obj.getNome();
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
