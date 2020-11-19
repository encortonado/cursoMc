package com.cursoMc.SpringFast.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoMc.SpringFast.domain.States;
import com.cursoMc.SpringFast.repositories.StatesRepository;

@Service
public class StatesService {

	@Autowired
	StatesRepository repository;

	
	
	public List<States> findAll() {
		return repository.findAllByOrderByNome();
	}
	
}
