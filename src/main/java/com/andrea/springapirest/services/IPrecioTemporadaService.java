package com.andrea.springapirest.services;

import java.util.List;
import java.util.Optional;

import com.andrea.springapirest.entities.PrecioTemporada;
import com.andrea.springapirest.models.dto.PrecioTemporadaDTO;

public interface IPrecioTemporadaService {
	 PrecioTemporada crear(PrecioTemporadaDTO dto);

	    PrecioTemporada actualizar(Integer id, PrecioTemporadaDTO dto);

	    void eliminar(Integer id);

	    List<PrecioTemporada> listarPorApartamento(Integer idApto);
	    
	    PrecioTemporada obtenerPorId(Integer id);
}
