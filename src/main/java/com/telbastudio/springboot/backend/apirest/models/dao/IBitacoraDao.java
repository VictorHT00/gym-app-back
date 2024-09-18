package com.telbastudio.springboot.backend.apirest.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.telbastudio.springboot.backend.apirest.models.entity.Bitacora;
import com.telbastudio.springboot.backend.apirest.models.entity.Cliente;

public interface IBitacoraDao extends JpaRepository<Bitacora, Long> {

	public List<Bitacora> findAllByFecha(Date fecha);

	@Query("SELECT b FROM Bitacora b WHERE b.operacion = :operacion AND b.fecha = :fecha")
	List<Bitacora> findByOperacionAndFecha(@Param("operacion") int operacion, @Param("fecha") Date fecha);
	
	@Query("SELECT b FROM Bitacora b WHERE b.operacion <> :operacion AND MONTH(b.fecha) = MONTH(:fecha) AND YEAR(b.fecha) = YEAR(:fecha)")
    List<Bitacora> findByMes(@Param("operacion") int operacion, @Param("fecha") Date fecha);
	
	List<Bitacora> findByCliente(Cliente cliente);
}
