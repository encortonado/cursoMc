package com.cursoMc.SpringFast;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursoMc.SpringFast.domain.Category;
import com.cursoMc.SpringFast.repositories.CategoryRepository;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}
