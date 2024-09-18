package com.telbastudio.springboot.backend.apirest.models.services;

import java.util.List;

import com.telbastudio.springboot.backend.apirest.models.entity.Rol;
import com.telbastudio.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);
	
	public List<Usuario> findAll();

	public Usuario findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void delete(Long id);
	
	public Rol findRol(Long id);
}
