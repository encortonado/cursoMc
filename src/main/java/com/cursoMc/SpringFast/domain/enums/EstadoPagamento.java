package com.cursoMc.SpringFast.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	// Variaveis para puxar um id de Estado Pagamento
	private int cod;
	private String descricao;
		
	// metodo construtor
	private EstadoPagamento(int cod, String descricao) {
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
		public static EstadoPagamento toEnum(Integer cod) {
			if(cod == null) {
				return null;
			}
			
			for (EstadoPagamento x : EstadoPagamento.values()) {
				if (cod.equals(x.getCod())) {
					return x;
				}
			}
			
			throw new IllegalArgumentException("ID invalido: " + cod);
		}
	
}
