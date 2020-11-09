package com.cursoMc.SpringFast;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursoMc.SpringFast.domain.Category;
import com.cursoMc.SpringFast.domain.City;
import com.cursoMc.SpringFast.domain.Cliente;
import com.cursoMc.SpringFast.domain.Endereco;
import com.cursoMc.SpringFast.domain.Product;
import com.cursoMc.SpringFast.domain.States;
import com.cursoMc.SpringFast.domain.enums.TipoCliente;
import com.cursoMc.SpringFast.repositories.CategoryRepository;
import com.cursoMc.SpringFast.repositories.CityRepository;
import com.cursoMc.SpringFast.repositories.ClienteRepository;
import com.cursoMc.SpringFast.repositories.EnderecoRepository;
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
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// instanciando objetos
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");

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
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(pro1, pro2, pro3));

		estadoRepository.saveAll(Arrays.asList(st1, st2));
		cidadeRepository.saveAll(Arrays.asList(ct1, ct2, ct3));
		
		clienteRepository.saveAll(Arrays.asList(cl1));
		enderecoRepository.saveAll(Arrays.asList(en1, en2));
	}

}
