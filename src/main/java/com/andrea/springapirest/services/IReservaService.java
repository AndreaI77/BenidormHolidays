package com.andrea.springapirest.services;

import java.time.LocalDate;
import java.util.List;


import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;


import com.andrea.springapirest.entities.Reserva;
import com.andrea.springapirest.models.dto.ComentarioDTO;
import com.andrea.springapirest.models.dto.CrearReservaRequest;
import com.andrea.springapirest.models.dto.IngresoMensualDTO;
import com.andrea.springapirest.models.dto.OcupacionMensualDTO;
import com.andrea.springapirest.models.dto.ValoracionRequest;

public interface IReservaService {
	 List<Reserva> findAll();
	 Reserva findById(Integer id);
	 void delete(Integer id);

	 List<ComentarioDTO> findComentariosByApartamento(@Param("id") Integer id);
	 Double mediaValoracionByApartamento(@Param("id") Integer id);
	 Long countValoracionesByApartamento(@Param("id") Integer id);	
	 boolean existsByApartamentoId(Integer id);
	Reserva crearReserva(CrearReservaRequest reserva);
	List<Reserva> getReservasCliente(Authentication auth);
	void valorarReserva(Integer id, ValoracionRequest request, Authentication auth);
	List<Reserva>findByApartamento(Integer id);
	List<Reserva> findByRango(LocalDate inicio, LocalDate fin);
	List<IngresoMensualDTO> getIngresos(int year);
	List<OcupacionMensualDTO> calcularOcupacion(int year);
	
}
