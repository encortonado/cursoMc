package com.cursoMc.SpringFast.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoMc.SpringFast.domain.Cliente;
import com.cursoMc.SpringFast.repositories.ClienteRepository;
import com.cursoMc.SpringFast.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClienteRepository repos;

	public Cliente find(Integer id) {

		Optional<Cliente> obj = repos.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
