package com.telbastudio.springboot.backend.apirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telbastudio.springboot.backend.apirest.models.services.ExportResult;
import com.telbastudio.springboot.backend.apirest.models.services.ExportarService;

@RestController
@RequestMapping("/api")
public class ExportarController {
	@Autowired
    private ExportarService exportarService;
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/exportar")
	public ResponseEntity<byte[]> exportarBaseDatos() {
        ExportResult exportResult = exportarService.exportarBaseDatos();

        if (exportResult != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(exportResult.getExportedData());
        } else {
            // Manejar el caso en el que la exportación no fue exitosa
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
