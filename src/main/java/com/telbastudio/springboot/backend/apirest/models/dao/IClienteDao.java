package com.telbastudio.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.telbastudio.springboot.backend.apirest.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long>{
	
	public Cliente findByTelefono(String telefono);
	
	public Cliente findByNoCliente(int noCliente);
	
	@Query("SELECT c FROM Cliente c ORDER BY c.id")
	List<Cliente> findAllOrderedById();
	
	public List<Cliente> findByNombreContainingOrderByApellido(String nombre);
	
	@Query("SELECT c FROM Cliente c WHERE CAST(c.noCliente AS string) LIKE :numero% ORDER BY c.noCliente")
	public List<Cliente> findByNoClienteStartingWith(@Param("numero") String noCliente);
	
	public List<Cliente> findByApellidoContainingOrderByApellido(String apellido);
	
	public List<Cliente> findByTelefonoContainingOrderByNoCliente(String telefono);
}
