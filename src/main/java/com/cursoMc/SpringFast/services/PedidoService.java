package com.cursoMc.SpringFast.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursoMc.SpringFast.domain.Cliente;
import com.cursoMc.SpringFast.domain.ItemPedido;
import com.cursoMc.SpringFast.domain.PagamentoBoleto;
import com.cursoMc.SpringFast.domain.Pedido;
import com.cursoMc.SpringFast.domain.enums.EstadoPagamento;
import com.cursoMc.SpringFast.repositories.ItemPedidoRepository;
import com.cursoMc.SpringFast.repositories.PagamentoRepository;
import com.cursoMc.SpringFast.repositories.PedidoRepository;
import com.cursoMc.SpringFast.security.UserSS;
import com.cursoMc.SpringFast.services.exceptions.AuthorizationException;
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

	@Autowired
	ClientService clienteService;

	@Autowired
	private EmailService emailService;

	public Pedido find(Integer id) {

		Optional<Pedido> obj = repos.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
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
			x.setProduto(produtoService.find(x.getProduto().getId()));
			x.setPreco(x.getProduto().getPreco());
			x.setPedido(obj);

		}
		itemPedidoRepository.saveAll(obj.getItens());
		// emailService.sendOrderConfirmationEmail(obj);
		emailService.sendOrderConfirmationHtmlEmail(obj);
		;
		// System.out.println(obj);
		return obj;
	}

	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso Negado");
		}

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente = clienteService.find(user.getId());

		return repos.findByCliente(cliente, pageRequest);
	}

}
