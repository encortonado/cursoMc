package com.cursoMc.SpringFast.domain.enums;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	
	// Variaveis para puxar um id de Estado Pagamento
	private int cod;
	private String descricao;
		
	// metodo construtor
	private Perfil(int cod, String descricao) {
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
		public static Perfil toEnum(Integer cod) {
			if(cod == null) {
				return null;
			}
			
			for (Perfil x : Perfil.values()) {
				if (cod.equals(x.getCod())) {
					return x;
				}
			}
			
			throw new IllegalArgumentException("ID invalido: " + cod);
		}
	
}
