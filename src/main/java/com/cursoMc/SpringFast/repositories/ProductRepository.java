package com.cursoMc.SpringFast.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursoMc.SpringFast.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	
	
}
