package com.telbastudio.springboot.backend.apirest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telbastudio.springboot.backend.apirest.models.entity.Rol;
import com.telbastudio.springboot.backend.apirest.models.entity.Usuario;
import com.telbastudio.springboot.backend.apirest.models.services.UsuarioService;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost", "*"})
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/usuarios")
	public List<Usuario> index(){
		return usuarioService.findAll();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Usuario show(@PathVariable Long id) {
		return usuarioService.findById(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario crear(@RequestBody Usuario usuario) {
		List<Rol> rolesCargados = new ArrayList<>();
		for (Rol rol : usuario.getRoles()) {
		    Rol rolPersistido = usuarioService.findRol(rol.getId());
		    if (rolPersistido != null) {
		        rolesCargados.add(rolPersistido);
		    }
		}
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setRoles(rolesCargados);
		return usuarioService.save(usuario);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario update(@RequestBody Usuario usuario,@PathVariable Long id) {
		Usuario usuarioActual = usuarioService.findById(id);
		
		usuarioActual.setNombre(usuario.getNombre());
		usuarioActual.setApellido(usuario.getApellido());
		usuarioActual.setEnabled(usuario.getEnabled());
		usuarioActual.setUsername(usuario.getUsername());
		
		return usuarioService.save(usuarioActual);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/usuarios/password/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario updatePassword(@RequestBody Usuario usuario,@PathVariable Long id) {
		Usuario usuarioActual = usuarioService.findById(id);
		
		usuarioActual.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		return usuarioService.save(usuarioActual);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Usuario usuario = usuarioService.findById(id);
		usuario.getRoles().clear();
		
		usuarioService.delete(id);
	}
	
}
