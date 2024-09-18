package com.telbastudio.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telbastudio.springboot.backend.apirest.models.dao.IClienteDao;
import com.telbastudio.springboot.backend.apirest.models.dao.ICostoPlanDao;
import com.telbastudio.springboot.backend.apirest.models.dao.IMensualidadDao;
import com.telbastudio.springboot.backend.apirest.models.entity.Cliente;
import com.telbastudio.springboot.backend.apirest.models.entity.CostoPlan;
import com.telbastudio.springboot.backend.apirest.models.entity.Mensualidad;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IMensualidadDao mensualidadDao;
	
	@Autowired ICostoPlanDao costoPlanDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Cliente cliente) {
		clienteDao.delete(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Mensualidad findMensualidadById(Long id) {
		return mensualidadDao.findById(id).orElse(null);
	}

	@Override
	public Mensualidad saveMensualidad(Mensualidad mensualidad) {
		return mensualidadDao.save(mensualidad);
	}

	@Override
	public void deleteMensualidadById(Long id) {
		mensualidadDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findByTelefono(String telefono) {
		return clienteDao.findByTelefono(telefono);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CostoPlan> findAllCostoPlanes() {
		return costoPlanDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public CostoPlan findByIdCosto(Long id) {
		return costoPlanDao.findById(id).orElse(null);
	}

	@Override
	public CostoPlan saveCosto(CostoPlan costoPlan) {
		return costoPlanDao.save(costoPlan);
	}

	@Override
	public List<Mensualidad> findMensualidadByCliente(Cliente cliente) {
		return mensualidadDao.findByCliente(cliente);
	}

	@Override
	public Cliente findByNoCliente(int noCliente) {
		return clienteDao.findByNoCliente(noCliente);
	}

	@Override
	public List<Cliente> findByNombreContaining(String nombre) {
		return clienteDao.findByNombreContainingOrderByApellido(nombre);
	}

	@Override
	public List<Cliente> findByNoClienteStartingWith(String noCliente) {
		return clienteDao.findByNoClienteStartingWith(noCliente);
	}

	@Override
	public List<Cliente> findByApellidoContainingOrderByApellido(String apellido) {
		return clienteDao.findByApellidoContainingOrderByApellido(apellido);
	}

	@Override
	public List<Cliente> findByTelefonoContaining(String telefono) {
		return clienteDao.findByTelefonoContainingOrderByNoCliente(telefono);
	}
}
