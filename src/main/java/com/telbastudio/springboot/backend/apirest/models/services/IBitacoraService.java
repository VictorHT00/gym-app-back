package com.telbastudio.springboot.backend.apirest.models.services;

import java.util.Date;
import java.util.List;

import com.telbastudio.springboot.backend.apirest.models.entity.Bitacora;
import com.telbastudio.springboot.backend.apirest.models.entity.Cliente;

public interface IBitacoraService {
	public List<Bitacora> findAll();
	
	public List<Bitacora> findAllByFecha(Date fecha);
	
	public List<Bitacora> findByOperacionAndFecha(int operacion, Date fecha);
	
	public List<Bitacora> findByMes(Date fecha);
	
	public List<Bitacora> findByCliente(Cliente cliente);
	
	public Bitacora save(Bitacora bitacora);
	
	public void delete(Long id);
}
