package com.andrea.springapirest.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrea.springapirest.entities.Apartamento;
import com.andrea.springapirest.services.IApartamentoService;

@RestController
@RequestMapping("/api/propietario")
public class PropietarioController {

    private final IApartamentoService apartamentoService;

    public PropietarioController(IApartamentoService apartamentoService) {
        this.apartamentoService = apartamentoService;
    }
    @PreAuthorize("hasRole('PROPIETARIO')")
    @GetMapping("/apartamentos")
    public List<Apartamento> getMisApartamentos(Authentication auth) {

        String userId = auth.getName();

        return apartamentoService.getApartamentosPropietario(userId);
    }
}
