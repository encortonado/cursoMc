package com.cursoMc.SpringFast.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoMc.SpringFast.domain.ItemPedido;
import com.cursoMc.SpringFast.domain.PagamentoBoleto;
import com.cursoMc.SpringFast.domain.Pedido;
import com.cursoMc.SpringFast.domain.enums.EstadoPagamento;
import com.cursoMc.SpringFast.repositories.ItemPedidoRepository;
import com.cursoMc.SpringFast.repositories.PagamentoRepository;
import com.cursoMc.SpringFast.repositories.PedidoRepository;
import com.cursoMc.SpringFast.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repos;

	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	PagamentoRepository pagamentoRepository;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {

		Optional<Pedido> obj = repos.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setStatus(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagto = (PagamentoBoleto) obj.getPagamento();
			boletoService.preencherPagamentoBoleto(pagto, obj.getInstante());
		}
		obj = repos.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido x : obj.getItens()) {
			x.setDesconto(0.00);
			x.setPreco(produtoService.find(x.getProduto().getId()).getPreco());
			x.setPedido(obj);
			
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
}


