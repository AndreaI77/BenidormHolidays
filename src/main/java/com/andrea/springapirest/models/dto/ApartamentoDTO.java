package com.andrea.springapirest.models.dto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.andrea.springapirest.entities.Apartamento;

public class ApartamentoDTO {
    private Integer idApto;
    private String descripcion;
    private Integer capacidad;
    private Integer dormitorios;
    private BigDecimal precio;
    private Double coordenadax;
    private Double coordenaday;
    private List<String> fotos;
    private String direccion;
    private String titulo;

    public ApartamentoDTO(Apartamento a, BigDecimal bigDecimal) {
        this.idApto = a.getIdApto();
        this.descripcion = a.getDescripcion();
        this.capacidad = a.getCapacidad();
        this.dormitorios = a.getDormitorios();
        this.precio = bigDecimal;
        this.direccion = a.getDireccion();
        this.titulo = a.getTitulo();
        this.coordenadax = a.getCoordenadax();
        this.coordenaday = a.getCoordenaday();
        if (a.getFotos() != null && !a.getFotos().isBlank()) {
            this.fotos = Arrays.stream(a.getFotos().split(";"))
                               .map(String::trim)
                               .toList();
        } else {
            this.fotos = List.of();
        }
        }

	public Integer getIdApto() {
		return idApto;
	}

	public void setIdApto(Integer idApto) {
		this.idApto = idApto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public Integer getDormitorios() {
		return dormitorios;
	}

	public void setDormitorios(Integer dormitorios) {
		this.dormitorios = dormitorios;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	

	public List<String> getFotos() {
		return fotos;
	}

	public void setFotos(List<String> fotos) {
		this.fotos = fotos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

    
    
    
}