package com.andrea.springapirest.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.andrea.springapirest.entities.Apartamento;
import com.andrea.springapirest.entities.PrecioDia;
import com.andrea.springapirest.models.dto.PrecioDiaDTO;
import com.andrea.springapirest.repositories.IPrecioDiaRepository;
import com.andrea.springapirest.services.IPrecioService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/precios/dia")
public class PrecioDiaController {

    private final IPrecioDiaRepository precioDiaRepository;
    private final IPrecioService precioService;

    public PrecioDiaController(IPrecioDiaRepository precioDiaRepository,  IPrecioService precioService) {
        this.precioDiaRepository = precioDiaRepository;
		this.precioService = precioService;
    }
    @PreAuthorize("hasRole('EMPLEADO')")
    @PostMapping
    public ResponseEntity<PrecioDia> crear(@RequestBody PrecioDiaDTO dto) {
        return ResponseEntity.ok(precioService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<PrecioDia>> obtenerTodos() {
        return ResponseEntity.ok(precioDiaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrecioDia> obtenerPorId(@PathVariable Integer id) {
        return precioDiaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/apto/{idApto}")
    public List<PrecioDia> porApartamento(@PathVariable Integer idApto) {
        return precioDiaRepository.findByApartamento_IdApto(idApto);
    }
    @PreAuthorize("hasRole('EMPLEADO')")
    @PutMapping("/{id}")
    public ResponseEntity<PrecioDia> actualizar(
            @PathVariable Integer id,
            @RequestBody PrecioDia precioDia) {

        return precioDiaRepository.findById(id)
                .map(existing -> {
                    precioDia.setId(id);
                    return ResponseEntity.ok(precioDiaRepository.save(precioDia));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('EMPLEADO')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!precioDiaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        precioDiaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
