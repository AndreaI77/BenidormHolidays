package com.andrea.springapirest.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "precios_temporada")
public class PrecioTemporada implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_precio_temporada")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_apto", nullable = false)
    private Apartamento apartamento;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "precio_noche", nullable = false)
    private BigDecimal precioNoche;

    @Column(name = "nombre_temporada")
    private String nombreTemporada;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public BigDecimal getPrecioNoche() {
		return precioNoche;
	}

	public void setPrecioNoche(BigDecimal precioNoche) {
		this.precioNoche = precioNoche;
	}

	public String getNombreTemporada() {
		return nombreTemporada;
	}

	public void setNombreTemporada(String nombreTemporada) {
		this.nombreTemporada = nombreTemporada;
	}

}
