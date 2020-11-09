package com.cursoMc.SpringFast.domain.enums;

public enum TipoCliente {

	//Atributos do TipoCliente
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Juridica");
	// Variaveis para puxar um id de tipoCliente
	private int cod;
	private String descricao;
	// metodo construtor
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	// getters e setters
	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	// função da qual verifica se o id existe, se nao existir dropa um erro
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("ID invalido: " + cod);
	}
	
	
	
}
