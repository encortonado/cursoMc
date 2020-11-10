package com.cursoMc.SpringFast;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursoMc.SpringFast.domain.Category;
import com.cursoMc.SpringFast.domain.City;
import com.cursoMc.SpringFast.domain.Cliente;
import com.cursoMc.SpringFast.domain.Endereco;
import com.cursoMc.SpringFast.domain.ItemPedido;
import com.cursoMc.SpringFast.domain.Pagamento;
import com.cursoMc.SpringFast.domain.PagamentoBoleto;
import com.cursoMc.SpringFast.domain.PagamentoCartao;
import com.cursoMc.SpringFast.domain.Pedido;
import com.cursoMc.SpringFast.domain.Product;
import com.cursoMc.SpringFast.domain.States;
import com.cursoMc.SpringFast.domain.enums.EstadoPagamento;
import com.cursoMc.SpringFast.domain.enums.TipoCliente;
import com.cursoMc.SpringFast.repositories.CategoryRepository;
import com.cursoMc.SpringFast.repositories.CityRepository;
import com.cursoMc.SpringFast.repositories.ClienteRepository;
import com.cursoMc.SpringFast.repositories.EnderecoRepository;
import com.cursoMc.SpringFast.repositories.ItemPedidoRepository;
import com.cursoMc.SpringFast.repositories.PagamentoRepository;
import com.cursoMc.SpringFast.repositories.PedidoRepository;
import com.cursoMc.SpringFast.repositories.ProductRepository;
import com.cursoMc.SpringFast.repositories.StatesRepository;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoriaRepository;

	@Autowired
	private ProductRepository produtoRepository;

	@Autowired
	private StatesRepository estadoRepository;

	@Autowired
	private CityRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// instanciando objetos
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Computadores");
		Category cat4 = new Category(null, "Servidores");
		Category cat5 = new Category(null, "Roupa e Moda");
		Category cat6 = new Category(null, "Brinquedotecaria");
		Category cat7 = new Category(null, "Cervejaria");
		Category cat8 = new Category(null, "Almoxarifados");
		Category cat9 = new Category(null, "Locadoura");
		Category cat10 = new Category(null, "Móveis de sala de estar");

		Product pro1 = new Product(null, "Computador", 2000.00);
		Product pro2 = new Product(null, "Impressora", 800.00);
		Product pro3 = new Product(null, "Mouse", 80.00);

		States st1 = new States(null, "Minas Gerais");
		States st2 = new States(null, "São Paulo");

		City ct1 = new City(null, "Uberlandia", st1);
		City ct2 = new City(null, "Campinas", st2);
		City ct3 = new City(null, "São Paulo", st2);

		Cliente cl1 = new Cliente(null, "Maria da Silva", "mariagatinha27@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		Endereco en1 = new Endereco(null, "Rua das Dores", "300", "Apto 303", "Jardins", "38220834", cl1, ct1);
		Endereco en2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cl1, ct2);
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, df.parse("28/02/2020 14:22"), cl1, en1);
		Pedido ped2 = new Pedido(null, df.parse("30/04/2020 11:54"), cl1, en2);
		
		Pagamento pgt1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pgt2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, df.parse("31/05/2020 23:59"), null);
		
		ItemPedido ip1 = new ItemPedido(ped1, pro1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, pro3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, pro2, 100.00, 1, 800.00);
		
		ped1.setPagamento(pgt1);
		ped2.setPagamento(pgt2);
		
		// adicionando a ligação dos valores de produto a suas respectivas categorias
		cat1.getProdutos().addAll(Arrays.asList(pro1, pro2, pro3));
		cat2.getProdutos().addAll(Arrays.asList(pro2));

		pro1.getCategorias().addAll(Arrays.asList(cat1));
		pro2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		pro3.getCategorias().addAll(Arrays.asList(cat1));

		st1.getCidades().addAll(Arrays.asList(ct1));
		st2.getCidades().addAll(Arrays.asList(ct2, ct3));

		cl1.getTelefones().addAll(Arrays.asList("27363323", "93838383")); // adiciona os numeros ao telefone a partir do cliente gerado
		cl1.getEnderecos().addAll(Arrays.asList(en1, en2));
		
		cl1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		pro1.getItens().addAll(Arrays.asList(ip1));
		pro2.getItens().addAll(Arrays.asList(ip3));
		pro3.getItens().addAll(Arrays.asList(ip2));
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10));
		produtoRepository.saveAll(Arrays.asList(pro1, pro2, pro3));

		estadoRepository.saveAll(Arrays.asList(st1, st2));
		cidadeRepository.saveAll(Arrays.asList(ct1, ct2, ct3));
		
		clienteRepository.saveAll(Arrays.asList(cl1));
		enderecoRepository.saveAll(Arrays.asList(en1, en2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgt1, pgt2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
