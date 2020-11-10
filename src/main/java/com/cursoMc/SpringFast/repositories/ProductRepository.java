package com.cursoMc.SpringFast.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.cursoMc.SpringFast.domain.Category;
import com.cursoMc.SpringFast.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Product> search(@Param("nome") String nome, @Param("categorias") List<Category> categorias, Pageable pageRequest);
	
	// Page<Product> findDistinctByNomeContainingAndCategoriasIn (String nome,List<Category> categorias, Pageable pageRequest);
	// Maneira mais facil de se fazer acima
	
}
