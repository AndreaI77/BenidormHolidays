package com.andrea.springapirest.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andrea.springapirest.entities.Reserva;
import com.andrea.springapirest.models.dto.ComentarioDTO;
import com.andrea.springapirest.models.dto.TipoReserva;


@Repository
public interface IReserva extends JpaRepository<Reserva, Integer> {

	@Query("""
		     SELECT new com.andrea.springapirest.models.dto.ComentarioDTO(
			    c.idUsuario,
			    c.nombre,
			    r.fechaPrincipio,
			    r.valoracion,
			    r.comentario
			)
			FROM Reserva r
			JOIN r.cliente c
			WHERE r.apartamento.idApto = :id
			  AND r.valoracion IS NOT NULL
			ORDER BY r.fechaPrincipio DESC
		""") 
		List<ComentarioDTO> findComentariosByApartamento(@Param("id") Integer id);

    @Query("""
        SELECT AVG(r.valoracion)
        FROM Reserva r
        WHERE r.apartamento.idApto = :id
          AND r.valoracion IS NOT NULL
    """)
    Double mediaValoracionByApartamento(@Param("id") Integer id);

    @Query("""
        SELECT COUNT(r)
        FROM Reserva r
        WHERE r.apartamento.idApto = :id
          AND r.valoracion IS NOT NULL
    """)
    Long countValoracionesByApartamento(@Param("id") Integer id);

   
	boolean existsByApartamento_IdAptoAndTipo(Integer idApto, TipoReserva tipo);
}