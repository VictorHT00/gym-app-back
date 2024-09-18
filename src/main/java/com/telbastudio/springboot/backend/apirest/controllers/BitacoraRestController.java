package com.telbastudio.springboot.backend.apirest.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telbastudio.springboot.backend.apirest.models.entity.Bitacora;
import com.telbastudio.springboot.backend.apirest.models.services.IBitacoraService;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost", "*"})
@RestController
@RequestMapping("/api")
public class BitacoraRestController {

	@Autowired
	private IBitacoraService bitacoraService;
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/bitacoras")
	public List<Bitacora> index(){
		return bitacoraService.findAll();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/bitacoras/{id}")
	public List<Bitacora> show(@PathVariable Date id){
		return bitacoraService.findAllByFecha(id);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/bitacoras/of")
	public List<Bitacora> getBitacoras( @RequestParam("operacion") int operacion,
            @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha){
		return bitacoraService.findByOperacionAndFecha(operacion, fecha);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/bitacoras/mes")
	public List<Bitacora> getBitacorasPorMes( @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha){
		return bitacoraService.findByMes(fecha);
	}
	
	@PostMapping("/bitacoras")
	public Bitacora create(@RequestBody Bitacora bitacora) {
		return bitacoraService.save(bitacora);
	}
}
