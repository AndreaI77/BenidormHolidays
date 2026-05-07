package com.andrea.springapirest.models.dto;

public class IngresoMensualDTO {
	 private int mes;
	 private Double precio;
	 
	public IngresoMensualDTO(int mes, Double precio) {
		super();
		this.mes = mes;
		this.precio = precio;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	 	 
}
