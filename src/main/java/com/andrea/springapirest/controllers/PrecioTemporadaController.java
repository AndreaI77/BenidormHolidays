package com.andrea.springapirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.andrea.springapirest.entities.PrecioTemporada;
import com.andrea.springapirest.models.dto.PrecioTemporadaDTO;
import com.andrea.springapirest.services.IPrecioTemporadaService;

@RestController
@RequestMapping("/api/precios/temporada")
public class PrecioTemporadaController {
	
	 @Autowired
	 private IPrecioTemporadaService service;

	 @GetMapping("/apto/{idApto}")
	 public List<PrecioTemporada> listar(@PathVariable Integer idApto) {
	     return service.listarPorApartamento(idApto);
	 }
	 @GetMapping("/{id}")
	 public PrecioTemporada obtenerPorId(@PathVariable Integer id) {
	     return service.obtenerPorId(id);
	 }
	 @PreAuthorize("hasRole('EMPLEADO')")
	 @PostMapping
	 public PrecioTemporada crear(@RequestBody PrecioTemporadaDTO dto) {
	     return service.crear(dto);
	 }

	 @PreAuthorize("hasRole('EMPLEADO')")
	 @PutMapping("/{id}")
	 public PrecioTemporada actualizar(@PathVariable Integer id,
	                                      @RequestBody PrecioTemporadaDTO dto) {
	     return service.actualizar(id, dto);
	 }
	 @PreAuthorize("hasRole('EMPLEADO')")
	@DeleteMapping("/{id}")
	 public void eliminar(@PathVariable Integer id) {
	     service.eliminar(id);
	 }

}
