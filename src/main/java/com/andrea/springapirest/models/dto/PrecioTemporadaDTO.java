package com.andrea.springapirest.models.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PrecioTemporadaDTO {
	private Integer	 id;
    private Integer idApto;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal precioNoche;
    private String nombreTemporada;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdApto() {
		return idApto;
	}
	public void setIdApto(Integer idApto) {
		this.idApto = idApto;
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
