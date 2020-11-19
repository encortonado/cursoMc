package com.cursoMc.SpringFast.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cursoMc.SpringFast.domain.City;
import com.cursoMc.SpringFast.domain.States;
import com.cursoMc.SpringFast.dto.CidadeDTO;
import com.cursoMc.SpringFast.dto.EstadoDTO;
import com.cursoMc.SpringFast.services.CidadeService;
import com.cursoMc.SpringFast.services.StatesService;

@RestController
@RequestMapping("/estados")
public class StatesResources {

	@Autowired
	private StatesService statesService;

	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {

		List<States> list = statesService.findAll();
		List<EstadoDTO> listDTO = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}

	@RequestMapping(value = "/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		
		List<City> list = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listDTO = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
		
	}

}
