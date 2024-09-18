package com.telbastudio.springboot.backend.apirest.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.telbastudio.springboot.backend.apirest.models.entity.Mensualidad;
import com.telbastudio.springboot.backend.apirest.models.services.IClienteService;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost", "*"})
@RestController
@RequestMapping("/api")
public class MensualidadRestController {


	@Autowired
	private IClienteService clienteService;
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/mensualidades/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mensualidad show(@PathVariable Long id) {
		return clienteService.findMensualidadById(id);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@DeleteMapping("/mensualidades/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteService.deleteMensualidadById(id);
	}
	
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/mensualidades")
	@ResponseStatus(HttpStatus.CREATED)
	public Mensualidad crear(@RequestBody Mensualidad mensualidad) {
		return clienteService.saveMensualidad(mensualidad);
	}
}
