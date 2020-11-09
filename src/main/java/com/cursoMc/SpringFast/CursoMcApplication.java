package com.cursoMc.SpringFast;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursoMc.SpringFast.domain.Category;
import com.cursoMc.SpringFast.domain.Product;
import com.cursoMc.SpringFast.repositories.CategoryRepository;
import com.cursoMc.SpringFast.repositories.ProductRepository;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoriaRepository;
	
	@Autowired
	private ProductRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");

		Product pro1 = new Product(null, "Computador", 2000.00);
		Product pro2 = new Product(null, "Impressora", 800.00);
		Product pro3 = new Product(null, "Mouse", 80.00);
		// adicionando a ligação dos valores de produto a suas respectivas categorias
		cat1.getProdutos().addAll(Arrays.asList(pro1, pro2, pro3));
		cat2.getProdutos().addAll(Arrays.asList(pro2));
		
		pro1.getCategorias().addAll(Arrays.asList(cat1));
		pro2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		pro3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(pro1, pro2, pro3));
	}

}
