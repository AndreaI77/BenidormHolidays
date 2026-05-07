package com.andrea.springapirest.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andrea.springapirest.entities.Reserva;
import com.andrea.springapirest.models.dto.ComentarioDTO;
import com.andrea.springapirest.models.dto.IngresoMensualDTO;
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
	
	   @Query("""
			    SELECT COUNT(r) > 0 FROM Reserva r
			    WHERE r.apartamento.id = :apartamentoId
			    AND (
			        :fechaInicio < r.fechaFin AND
			        :fechaFin > r.fechaPrincipio
			    )
			    """)
	boolean hayDisponibilidad(
			        @Param("apartamentoId") Integer apartamentoId,
			        @Param("fechaInicio") LocalDate fechaInicio,
			        @Param("fechaFin") LocalDate fechaFin
			    );
	   
	List<Reserva> findByClienteIdUsuarioAndTipoOrderByFechaPrincipioDesc(Integer clienteId, TipoReserva tipo);

	List<Reserva> findByApartamentoIdAptoOrderByFechaPrincipioDesc(Integer id);
	
	@Query("""
		    SELECT r FROM Reserva r
		    WHERE r.fechaFin >= :inicio
		    AND r.fechaPrincipio <= :fin
		""")
		List<Reserva> findByRango(LocalDate inicio, LocalDate fin);
	
	@Query("""
			SELECT new com.andrea.springapirest.models.dto.IngresoMensualDTO(
			    CAST(EXTRACT(MONTH FROM r.fechaPrincipio) AS integer),
			    COALESCE(SUM(r.precio), 0)
			)
			FROM Reserva r
			WHERE EXTRACT(YEAR FROM r.fechaPrincipio) = :year
			GROUP BY EXTRACT(MONTH FROM r.fechaPrincipio)
			ORDER BY EXTRACT(MONTH FROM r.fechaPrincipio)
			""")
			List<IngresoMensualDTO> ingresosPorMes(@Param("year") int year);

	@Query("""
			SELECT r
			FROM Reserva r
			WHERE EXTRACT(YEAR FROM r.fechaPrincipio) = :year
			""")
	List<Reserva> findByYear(int year);
}