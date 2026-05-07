package com.andrea.springapirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.andrea.springapirest.entities.Reserva;
import com.andrea.springapirest.models.dto.CrearReservaRequest;
import com.andrea.springapirest.models.dto.IngresoMensualDTO;
import com.andrea.springapirest.models.dto.OcupacionMensualDTO;
import com.andrea.springapirest.models.dto.ValoracionRequest;
import com.andrea.springapirest.services.IReservaService;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private IReservaService reservaService;

    @GetMapping
    public List<Reserva> getAll(){
    	System.out.print("getting all");
    	return reservaService.findAll();
    }
    @GetMapping("/rango")
    public ResponseEntity<List<Reserva>> getPorRango(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
    	System.out.print(fechaInicio + "fecha fin: "+fechaFin);
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);

        return ResponseEntity.ok(reservaService.findByRango(inicio, fin));
    }
    @GetMapping("/mis")
    public ResponseEntity<List<Reserva>> misReservas(Authentication auth) {

        List<Reserva> reservas = reservaService.getReservasCliente(auth);

        return ResponseEntity.ok(reservas);
    }
    @GetMapping("/apartamento/{id}")
    public ResponseEntity<List<Reserva>> getByApartamento(@PathVariable Integer id) {
        return ResponseEntity.ok(reservaService.findByApartamento(id));
    }

    @PreAuthorize("hasRole('EMPLEADO')")
    @GetMapping("/{id}")
    public Reserva getById(@PathVariable Integer id) {
        return reservaService.findById(id);
    }
    
    @PreAuthorize("hasRole('EMPLEADO')")
    @GetMapping("/ingresos")
    public List<IngresoMensualDTO> getIngresos(@RequestParam int year) {
        return reservaService.getIngresos(year);
    }
    
    @PreAuthorize("hasRole('EMPLEADO')")
    @GetMapping("/ocupacion")
    public List<OcupacionMensualDTO> getOcupacion(@RequestParam int year) {
        return reservaService.calcularOcupacion(year);
    }
    
   
    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody CrearReservaRequest reserva) {
        try {
            Reserva nueva = reservaService.crearReserva(reserva);
            return ResponseEntity.ok(nueva);

        } catch (RuntimeException e) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("La disponibilidad del apartamento ha cambiado. realiza nueva búsqueda.");
        }
    }

    @PutMapping("/{id}/valoracion")
    public ResponseEntity<Void> valorarReserva(
            @PathVariable Integer id,
            @RequestBody ValoracionRequest request,
            Authentication auth) {

        reservaService.valorarReserva(id, request, auth);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        reservaService.delete(id);
    }
}
