package com.andrea.springapirest.models.dto;

import java.time.LocalDate;

public class ApartamentoFiltroDTO {
	 private LocalDate fechaInicio;
	 private LocalDate fechaFin;
	 private Integer capacidad;

	 public ApartamentoFiltroDTO() {}

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

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	 
}
