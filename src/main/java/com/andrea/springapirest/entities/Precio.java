package com.andrea.springapirest.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "precio")
public class Precio implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_precio")
    private Integer idPrecio;

    @Column(name = "fecha_principio", nullable = false)
    private LocalDate fechaPrincipio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "precio_noche")
    private Double precioNoche;

    private Double extra;

    @ManyToOne
    @JoinColumn(name = "id_apto")
    @JsonIgnoreProperties({"precio"})
    private Apartamento apartamento;

	public Integer getIdPrecio() {
		return idPrecio;
	}

	public void setIdPrecio(Integer idPrecio) {
		this.idPrecio = idPrecio;
	}

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

	public Double getPrecioNoche() {
		return precioNoche;
	}

	public void setPrecioNoche(Double precioNoche) {
		this.precioNoche = precioNoche;
	}

	public Double getExtra() {
		return extra;
	}

	public void setExtra(Double extra) {
		this.extra = extra;
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

}
