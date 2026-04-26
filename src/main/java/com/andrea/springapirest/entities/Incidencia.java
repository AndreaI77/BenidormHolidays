package com.andrea.springapirest.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "incidencia")
public class Incidencia implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incidencia")
    private Integer idIncidencia;

    private LocalDate fecha;
    private String tipo;
    private String descripcion;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    @JsonIgnoreProperties({"incidencias"})
    private Usuario empleado;

    @ManyToOne
    @JoinColumn(name = "id_reserva", nullable = false)
    @JsonIgnoreProperties({"incidencia"})
    private Reserva reserva;

	public Integer getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(Integer idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Usuario empleado) {
		this.empleado = empleado;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

    // getters y setters
    
}