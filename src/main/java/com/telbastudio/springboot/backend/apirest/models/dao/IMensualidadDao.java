package com.telbastudio.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.telbastudio.springboot.backend.apirest.models.entity.Cliente;
import com.telbastudio.springboot.backend.apirest.models.entity.Mensualidad;

public interface IMensualidadDao extends CrudRepository<Mensualidad, Long>{

	public List<Mensualidad> findByCliente(Cliente cliente);

}
