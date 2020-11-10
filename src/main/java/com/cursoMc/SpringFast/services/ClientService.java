package com.cursoMc.SpringFast.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursoMc.SpringFast.domain.Cliente;
import com.cursoMc.SpringFast.dto.ClienteDTO;
import com.cursoMc.SpringFast.repositories.ClienteRepository;
import com.cursoMc.SpringFast.services.exceptions.DataIntegrityException;
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
	
	
	

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repos.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repos.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possivel excluir um cliente cujo está com pedidos na cesta.");
			
		}
	}
	
	public List<Cliente> findAll() {
		return repos.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repos.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}

	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	

}









