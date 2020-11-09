package com.cursoMc.SpringFast.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cursoMc.SpringFast.domain.Category;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResources {

	@RequestMapping(method = RequestMethod.GET)
	public List<Category> listar() {

		Category cat1 = new Category(1, "Informática");
		Category cat2 = new Category(2, "Escritório");

		List<Category> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);

		return lista;
	}

}
