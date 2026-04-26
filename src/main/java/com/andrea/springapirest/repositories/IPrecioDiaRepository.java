package com.andrea.springapirest.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.andrea.springapirest.entities.PrecioDia;

public interface IPrecioDiaRepository extends JpaRepository<PrecioDia, Integer> {
	
	@Query("""
			SELECT p.precioNoche
			FROM PrecioDia p
			WHERE p.apartamento.idApto = :idApto
			AND p.fecha = :fecha
			""")
	Optional<BigDecimal> findPrecioByApartamentoIdAptoAndFecha(
			 @Param("idApto") Integer idApto,
		        @Param("fecha") LocalDate fecha);

	List<PrecioDia> findByApartamento_IdApto(Integer idApto);
	boolean existsByApartamento_IdAptoAndFecha(Integer idApto, LocalDate fecha);
}
