package com.andrea.springapirest.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.andrea.springapirest.models.dto.TipoReserva;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Integer idReserva;

    @Column(name = "fecha_principio", nullable = false)
    private LocalDate fechaPrincipio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    private Integer duracion;
    private Integer personas;
    

    private Double precio;

    private String comentario;
    private Integer valoracion;
    
    @Enumerated(EnumType.STRING)
    private TipoReserva tipo;

    public TipoReserva getTipo() {
		return tipo;
	}

	public void setTipo(TipoReserva tipo) {
		this.tipo = tipo;
	}

	@ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonIgnoreProperties({"reservas"})
    private Usuario cliente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_apto", nullable = false)
    private Apartamento apartamento;

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
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

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
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

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getValoracion() {
		return valoracion;
	}

	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	
	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	

    // getters y setters
    
    
}
