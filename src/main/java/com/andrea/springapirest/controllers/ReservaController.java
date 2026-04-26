package com.andrea.springapirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.andrea.springapirest.entities.Reserva;
import com.andrea.springapirest.services.IReservaService;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private IReservaService reservaService;

    @GetMapping
    public List<Reserva> getAll() {
        return reservaService.findAll();
    }

    @GetMapping("/{id}")
    public Reserva getById(@PathVariable Integer id) {
        return reservaService.findById(id);
    }

    @PostMapping
    public Reserva create(@RequestBody Reserva reserva) {
        return reservaService.save(reserva);
    }

    @PutMapping("/{id}")
    public Reserva update(@PathVariable Integer id, @RequestBody Reserva reserva) {
        return reservaService.update(id, reserva);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        reservaService.delete(id);
    }
}
