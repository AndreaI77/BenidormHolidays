package com.andrea.springapirest.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "apartamento")
public class Apartamento implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_apto")
    private Integer idApto;

    private String direccion;
    private String descripcion;
    private Double coordenadax;
    private Double coordenaday;
    private Integer dormitorios;

    @Column(name = "baños")
    private Integer banos;
    private String titulo;
    private String fotos;
    private Integer capacidad;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_propietario", nullable = false)
    @JsonIgnoreProperties({"apartamentos"})
    private Usuario propietario;

    
    @OneToMany(mappedBy = "apartamento")
    @JsonIgnoreProperties({"apartamento"})
    private List<Reserva> reservas;
    

	public Integer getIdApto() {
		return idApto;
	}

	public void setIdApto(Integer idApto) {
		this.idApto = idApto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getCoordenadax() {
		return coordenadax;
	}

	public void setCoordenadax(Double coordenadax) {
		this.coordenadax = coordenadax;
	}

	public Double getCoordenaday() {
		return coordenaday;
	}

	public void setCoordenaday(Double coordenaday) {
		this.coordenaday = coordenaday;
	}

	public Integer getDormitorios() {
		return dormitorios;
	}

	public void setDormitorios(Integer dormitorios) {
		this.dormitorios = dormitorios;
	}

	public Integer getBanos() {
		return banos;
	}

	public void setBanos(Integer banos) {
		this.banos = banos;
	}

	public String getFotos() {
		return fotos;
	}

	public void setFotos(String fotos) {
		this.fotos = fotos;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}



    // getters y setters
    
}