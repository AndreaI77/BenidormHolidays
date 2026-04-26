package com.andrea.springapirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.andrea.springapirest.entities.Servicio;
import com.andrea.springapirest.services.IServicioService;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private IServicioService servicioService;

    @GetMapping
    public List<Servicio> getAll() {
        return servicioService.findAll();
    }

    @GetMapping("/{id}")
    public Servicio getById(@PathVariable Integer id) {
        return servicioService.findById(id);
    }

    @PostMapping
    public Servicio create(@RequestBody Servicio servicio) {
        return servicioService.saveServicio(servicio);
    }

    @PutMapping("/{id}")
    public Servicio update(@PathVariable Integer id, @RequestBody Servicio servicio) {
        return servicioService.updateServicio(id, servicio);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        servicioService.deleteServicio(id);
    }
}
