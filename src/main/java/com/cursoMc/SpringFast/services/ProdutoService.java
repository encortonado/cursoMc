package com.cursoMc.SpringFast.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursoMc.SpringFast.domain.Category;
import com.cursoMc.SpringFast.domain.Product;
import com.cursoMc.SpringFast.repositories.CategoryRepository;
import com.cursoMc.SpringFast.repositories.ProductRepository;
import com.cursoMc.SpringFast.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProductRepository repos;
	
	@Autowired
	private CategoryRepository categoryRepository;

	public Product find(Integer id) {

		Optional<Product> obj = repos.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Product.class.getName()));
	}
	
	public Page<Product> search(String nome,  List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categorias = categoryRepository.findAllById(ids);
		return repos.search(nome, categorias, pageRequest);
	}

}
