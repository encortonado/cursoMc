package com.cursoMc.SpringFast.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoMc.SpringFast.domain.Category;
import com.cursoMc.SpringFast.repositories.CategoryRepository;

import com.cursoMc.SpringFast.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repos;

	public Category find(Integer id) {

		Optional<Category> obj = repos.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
	}

}
