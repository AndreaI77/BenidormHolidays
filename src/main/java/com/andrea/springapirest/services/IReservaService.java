package com.andrea.springapirest.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.andrea.springapirest.entities.Reserva;
import com.andrea.springapirest.models.dto.ComentarioDTO;

public interface IReservaService {
	 List<Reserva> findAll();
	 Reserva findById(Integer id);
	 Reserva save(Reserva reserva);
	 Reserva update(Integer id, Reserva reserva);
	 void delete(Integer id);

	 List<ComentarioDTO> findComentariosByApartamento(@Param("id") Integer id);
	 Double mediaValoracionByApartamento(@Param("id") Integer id);
	 Long countValoracionesByApartamento(@Param("id") Integer id);	
	 boolean existsByApartamentoId(Integer id);
}
