package com.cursoMc.SpringFast.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cursoMc.SpringFast.domain.States;

@Repository
public interface StatesRepository extends JpaRepository<States, Integer> {

	@Transactional(readOnly = true)
	public List<States> findAllByOrderByNome();
	
}
