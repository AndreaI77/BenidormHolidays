package com.andrea.springapirest.entities;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "precios_dia",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = {"id_apto", "fecha"})
       })
public class PrecioDia implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_precio_dia")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_apto", nullable = false)
    private Apartamento apartamento;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "precio_noche", nullable = false)
    private BigDecimal precioNoche;

    @Column(name = "motivo")
    private String motivo;

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

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

    // getters & setters
}
