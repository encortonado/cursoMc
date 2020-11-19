package com.cursoMc.SpringFast.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cursoMc.SpringFast.services.CidadeService;

@RestController
public class CidadeResources {
	
	@Autowired
	CidadeService cidadeService;

	
	
}
