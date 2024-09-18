package com.telbastudio.springboot.backend.apirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.telbastudio.springboot.backend.apirest.models.entity.Bitacora;
import com.telbastudio.springboot.backend.apirest.models.entity.Cliente;
import com.telbastudio.springboot.backend.apirest.models.entity.CostoPlan;
import com.telbastudio.springboot.backend.apirest.models.entity.Mensualidad;
import com.telbastudio.springboot.backend.apirest.models.services.IBitacoraService;
import com.telbastudio.springboot.backend.apirest.models.services.IClienteService;
import com.telbastudio.springboot.backend.apirest.models.services.IUploadFileService;

import javax.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost", "*" })
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IBitacoraService bitacoraService;

	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.findAll();
	}

	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 20);
		return clienteService.findAll(pageable);
	}

	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();

		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@GetMapping("/clientes/check-in/{id}")
	public ResponseEntity<?> checkIn(@PathVariable String id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();

		try {
			cliente = clienteService.findByTelefono(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@GetMapping("/clientes/check-in/nocliente/{id}")
	public ResponseEntity<?> checkInNoCliente(@PathVariable int id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();

		try {
			cliente = clienteService.findByNoCliente(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (cliente == null) {
			response.put("mensaje",
					"El cliente ID: ".concat((id + "").toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@GetMapping("/clientes/buscar-id")
	public List<Cliente> buscarClientesById(@RequestParam(required = true) String id) {
		return clienteService.findByNoClienteStartingWith(id);
	}

	@GetMapping("/clientes/buscar-nombre")
	public List<Cliente> buscarClientesByNombre(@RequestParam(required = true) String nombre) {
		return clienteService.findByNombreContaining(nombre);
	}
	
	@GetMapping("/clientes/buscar-apellido")
	public List<Cliente> buscarClientesByApellido(@RequestParam(required = true) String apellido) {
		return clienteService.findByApellidoContainingOrderByApellido(apellido);
	}
	
	@GetMapping("/clientes/buscar-telefono")
	public List<Cliente> buscarClientesByTelefono(@RequestParam(required = true) String telefono) {
		return clienteService.findByTelefonoContaining(telefono);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido creado con éxito");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
		Cliente clienteActual = clienteService.findById(id);
		Cliente clienteUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (clienteActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setTelefono(cliente.getTelefono());
			clienteActual.setTelefonoEmergencia(cliente.getTelefonoEmergencia());
			clienteActual.setMesesSuscripcion(cliente.getMesesSuscripcion());
			clienteActual.setSiguientePago(cliente.getSiguientePago());
			clienteActual.setVigenciaDia(cliente.getVigenciaDia());
			clienteActual.setPagoConsecutivo(cliente.isPagoConsecutivo());
			clienteActual.setCostoPlan(cliente.getCostoPlan());
			clienteActual.setFechaInscripcion(cliente.getFechaInscripcion());
			clienteActual.setNoCliente(cliente.getNoCliente());

			clienteUpdated = clienteService.save(clienteActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido actualizado con éxito");
		response.put("cliente", clienteUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		Cliente clienteEliminar = clienteService.findById(id);
		if (clienteEliminar == null) {
			response.put("mensaje",
					"El cliente con el id: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			Cliente cliente = clienteService.findById(id);

			List<Bitacora> bitacoras; bitacoras = bitacoraService.findByCliente(cliente);
			
			for(Bitacora bitacora: bitacoras) { bitacoraService.delete(bitacora.getId());
			}
			  
			 System.out.println("correcto 1");
			  
			  List<Mensualidad> mensualidades; mensualidades =
			  clienteService.findMensualidadByCliente(cliente);
			  
			  for(Mensualidad mensualidad: mensualidades) {
			  clienteService.deleteMensualidadById(mensualidad.getId()); }
			  System.out.println("correcto 2");
			 
			String nombreFoto = cliente.getFoto();

			uploadFileService.eliminar(nombreFoto);

			clienteService.delete(cliente);
			System.out.println("correcto 3");

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Cliente cliente = clienteService.findById(id);

		if (!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadFileService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = cliente.getFoto();

			uploadFileService.eliminar(nombreFotoAnterior);

			cliente.setFoto(nombreArchivo);
			clienteService.save(cliente);

			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			response.put("cliente", cliente);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/clientes/costos")
	public List<CostoPlan> listarCostos() {
		return clienteService.findAllCostoPlanes();
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@GetMapping("/clientes/costos/{id}")
	public CostoPlan listarCostoById(@PathVariable Long id) {
		return clienteService.findByIdCosto(id);
	}

	@Secured({ "ROLE_ADMIN" })
	@PutMapping("/clientes/costos/{id}")
	public CostoPlan updateCosto(@RequestBody CostoPlan costo, @PathVariable Long id) {

		CostoPlan costoActual = clienteService.findByIdCosto(id);

		costoActual.setCosto(costo.getCosto());

		return clienteService.saveCosto(costoActual);
	}
}

//spring.jpa.hibernate.ddl-auto= create-drop