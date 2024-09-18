package com.telbastudio.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.telbastudio.springboot.backend.apirest.models.entity.Cliente;
import com.telbastudio.springboot.backend.apirest.models.entity.CostoPlan;
import com.telbastudio.springboot.backend.apirest.models.entity.Mensualidad;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public Cliente findById(Long id);
	
	public Cliente findByTelefono(String telefono);
	
	public Cliente findByNoCliente(int noCliente);
	
	public List<Cliente> findByNombreContaining(String nombre);
	
	public List<Cliente> findByNoClienteStartingWith(String noCliente);
	
	public List<Cliente> findByApellidoContainingOrderByApellido(String apellido);
	
	public List<Cliente> findByTelefonoContaining(String telefono);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Cliente cliente);
	
	public Mensualidad findMensualidadById(Long id);
	
	public Mensualidad saveMensualidad(Mensualidad mensualidad);
	
	public List<Mensualidad> findMensualidadByCliente(Cliente cliente);
	
	public void deleteMensualidadById(Long id);
	
	public List<CostoPlan> findAllCostoPlanes();
	
	public CostoPlan findByIdCosto(Long id); 
	
	public CostoPlan saveCosto(CostoPlan costoPlan);
	
	
}
