package com.cursoMc.SpringFast.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoMc.SpringFast.domain.City;
import com.cursoMc.SpringFast.repositories.CityRepository;

@Service
public class CidadeService {

	@Autowired
	CityRepository cityRepository;
	
	
	public List<City> findByEstado(Integer estado_id) {
		return cityRepository.findByEstado(estado_id);
	}
}
