package com.telbastudio.springboot.backend.apirest.models.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telbastudio.springboot.backend.apirest.models.dao.IBitacoraDao;
import com.telbastudio.springboot.backend.apirest.models.entity.Bitacora;
import com.telbastudio.springboot.backend.apirest.models.entity.Cliente;

@Service
public class BitacoraService implements IBitacoraService{

	@Autowired
	private IBitacoraDao bitacoraDao;
	
	@Override
	public List<Bitacora> findAll() {
		return bitacoraDao.findAll();
	}

	@Override
	public List<Bitacora> findAllByFecha(Date fecha) {
		return bitacoraDao.findAllByFecha(fecha);
	}

	@Override
	public Bitacora save(Bitacora bitacora) {
		return bitacoraDao.save(bitacora);
	}

	@Override
	public List<Bitacora> findByOperacionAndFecha(int operacion, Date fecha) {
		return bitacoraDao.findByOperacionAndFecha(operacion, fecha);
	}

	@Override
	public List<Bitacora> findByCliente(Cliente cliente) {
		return bitacoraDao.findByCliente(cliente);
	}

	@Override
	public void delete(Long id) {
		bitacoraDao.deleteById(id);
	}

	@Override
	public List<Bitacora> findByMes(Date fecha) {
		return bitacoraDao.findByMes(3, fecha);
	}
}
