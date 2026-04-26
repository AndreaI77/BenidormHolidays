package com.andrea.springapirest.models.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PrecioDiaDTO {
	private Long id;
    private Integer idApto;
    private LocalDate fecha;
    private BigDecimal precioNoche;
    private String motivo;
    
    
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getIdApto() {
		return idApto;
	}
	public void setIdApto(Integer idApto) {
		this.idApto = idApto;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public BigDecimal getPrecioNoche() {
		return precioNoche;
	}
	public void setPrecioNoche(BigDecimal precioNoche) {
		this.precioNoche = precioNoche;
	}
    
}
