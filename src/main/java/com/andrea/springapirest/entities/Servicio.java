package com.andrea.springapirest.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "servicio")
public class Servicio implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Integer idServicio;

    private LocalDate fecha;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    private String estado;
    private String tipo;
    private String descripcion;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "id_reserva", nullable = false)
    @JsonIgnoreProperties({"servicio","incidencia"})
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "id_incidencia")
    @JsonIgnoreProperties({"servicio", "incidencia"})
    private Incidencia incidencia;
    
    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    @JsonIgnoreProperties({"servicio", "incidencia"})
    private Usuario empleado;

	public Integer getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public Usuario getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Usuario empleado) {
		this.empleado = empleado;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Incidencia getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}

    // getters y setters
    
}
