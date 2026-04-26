package com.andrea.springapirest.repositories;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.andrea.springapirest.entities.PrecioTemporada;

@Repository
public interface IPrecioTemporadaRepository extends JpaRepository<PrecioTemporada, Integer> {
	  @Query("""
		        SELECT p.precioNoche
		        FROM PrecioTemporada p
		        WHERE p.apartamento.id = :idApto
		        AND :fecha BETWEEN p.fechaInicio AND p.fechaFin
		    """)
	Optional<BigDecimal> findPrecioByApartamentoIdAptoAndFecha(
		            @Param("idApto") Integer idApto,
		            @Param("fecha") LocalDate fecha
		    );

	List<PrecioTemporada> findByApartamento_IdApto(Integer idApto);

	Optional<PrecioTemporada> findById(Integer id);
	
	
	  @Query("""
		        SELECT p FROM PrecioTemporada p
		        WHERE p.apartamento.idApto = :idApto
		        AND (:inicio <= p.fechaFin AND :fin >= p.fechaInicio)
		    """)
	  List<PrecioTemporada> findSolapados(Integer idApto,
		                                        LocalDate inicio,
		                                        LocalDate fin);

}
