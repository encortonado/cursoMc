package com.cursoMc.SpringFast.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursoMc.SpringFast.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
