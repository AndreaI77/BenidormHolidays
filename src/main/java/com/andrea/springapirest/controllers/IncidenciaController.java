package com.andrea.springapirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.andrea.springapirest.entities.Incidencia;
import com.andrea.springapirest.services.IIncidenciaService;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {

    @Autowired
    private IIncidenciaService incidenciaService;

    @GetMapping
    public List<Incidencia> getAll() {
        return incidenciaService.findAll();
    }

    @GetMapping("/{id}")
    public Incidencia getById(@PathVariable Integer id) {
        return incidenciaService.findById(id);
    }

    @PostMapping
    public Incidencia create(@RequestBody Incidencia incidencia) {
        return incidenciaService.saveIncidencia(incidencia);
    }

    @PutMapping("/{id}")
    public Incidencia update(@PathVariable Integer id, @RequestBody Incidencia incidencia) {
        return incidenciaService.updateIncidencia(id, incidencia);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        incidenciaService.deleteIncidencia(id);
    }
}
