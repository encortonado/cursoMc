package com.cursoMc.SpringFast.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cursoMc.SpringFast.domain.Product;
import com.cursoMc.SpringFast.dto.ProdutoDTO;
import com.cursoMc.SpringFast.resources.utils.URL;
import com.cursoMc.SpringFast.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResources {

	@Autowired
	private ProdutoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> finder(@PathVariable Integer id) {

		Product obj = service.find(id);

		return ResponseEntity.ok().body(obj);

	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "nome", defaultValue = "") String nome, 
			@RequestParam(value = "categorias", defaultValue = "24") String categorias, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Product> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> pageDTO = list.map(obj -> new ProdutoDTO(obj)); 
		return ResponseEntity.ok().body(pageDTO);

	}


}
