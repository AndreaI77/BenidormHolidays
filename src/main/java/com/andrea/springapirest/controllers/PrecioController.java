package com.andrea.springapirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.andrea.springapirest.entities.Precio;
import com.andrea.springapirest.services.IPrecioService;

import java.util.List;

@RestController
@RequestMapping("/api/precios")
public class PrecioController {

    /*@Autowired
    private IPrecioService precioService;

    @GetMapping
    public List<Precio> getAll() {
        return precioService.findAll();
    }

    @GetMapping("/{id}")
    public Precio getById(@PathVariable Integer id) {
        return precioService.findById(id);
    }

    @PostMapping
    public Precio create(@RequestBody Precio precio) {
        return precioService.savePrecio(precio);
    }

    @PutMapping("/{id}")
    public Precio update(@PathVariable Integer id, @RequestBody Precio precio) {
        return precioService.updatePrecio(id, precio);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        precioService.deletePrecio(id);
    }*/
}
