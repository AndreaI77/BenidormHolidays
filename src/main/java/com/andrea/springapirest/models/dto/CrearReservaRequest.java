package com.andrea.springapirest.models.dto;

import java.time.LocalDate;

public class CrearReservaRequest {
	 private LocalDate fechaPrincipio;
	    private LocalDate fechaFin;
	    private Integer personas;
	    private Double precio;
	    private TipoReserva tipo;
	    private Integer apartamentoId;
	    
		public LocalDate getFechaPrincipio() {
			return fechaPrincipio;
		}
		public void setFechaPrincipio(LocalDate fechaPrincipio) {
			this.fechaPrincipio = fechaPrincipio;
		}
		public LocalDate getFechaFin() {
			return fechaFin;
		}
		public void setFechaFin(LocalDate fechaFin) {
			this.fechaFin = fechaFin;
		}
		public Integer getPersonas() {
			return personas;
		}
		public void setPersonas(Integer personas) {
			this.personas = personas;
		}
		public Double getPrecio() {
			return precio;
		}
		public void setPrecio(Double precio) {
			this.precio = precio;
		}

		public TipoReserva getTipo() {
			return tipo;
		}
		public void setTipo(TipoReserva tipo) {
			this.tipo = tipo;
		}
		public Integer getApartamentoId() {
			return apartamentoId;
		}
		public void setApartamentoId(Integer apartamentoId) {
			this.apartamentoId = apartamentoId;
		}
	    
	    

}
