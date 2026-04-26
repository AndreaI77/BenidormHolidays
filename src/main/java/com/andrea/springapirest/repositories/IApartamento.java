package com.andrea.springapirest.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.andrea.springapirest.entities.Apartamento;
import com.andrea.springapirest.models.dto.ApartamentoDTO;
import com.andrea.springapirest.models.dto.ApartamentoDetailDTO;


@RepositoryRestResource(path = "apartamentos",collectionResourceRel = "apartamentos")
public interface IApartamento extends JpaRepository<Apartamento, Integer> {
 
	 // Query para buscar apartamentos disponibles

	
	@Query("""
		    SELECT a
		FROM Apartamento a
		WHERE a.capacidad >= :capacidad
		AND a.estado = 'A'
		AND NOT EXISTS (
		    SELECT 1
		    FROM Reserva r
		    WHERE r.apartamento.idApto = a.idApto
		      AND r.fechaPrincipio < :fechaFin
		      AND r.fechaFin > :fechaInicio
		)
		""")
    List<Apartamento> buscarDisponibles(
        @Param("fechaInicio") LocalDate fechaInicio,
        @Param("fechaFin") LocalDate fechaFin,
        @Param("capacidad") int capacidad
        );

	List<Apartamento> findAllByOrderByEstadoAsc();
       
	
}