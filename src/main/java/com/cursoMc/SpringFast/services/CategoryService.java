package com.cursoMc.SpringFast.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursoMc.SpringFast.domain.Category;
import com.cursoMc.SpringFast.repositories.CategoryRepository;
import com.cursoMc.SpringFast.services.exceptions.DataIntegrityException;
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

	
	public Category insert(Category obj) {
		obj.setId(null);
		return repos.save(obj);
	}
	
	public Category update(Category obj) {
		find(obj.getId());
		return repos.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repos.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possivel excluir uma categoria cujo possui produtos.");
			
		}
	}
	
	public List<Category> findAll() {
		return repos.findAll();
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repos.findAll(pageRequest);
	}
	
}










