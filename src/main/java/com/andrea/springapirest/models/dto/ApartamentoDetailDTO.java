package com.andrea.springapirest.models.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApartamentoDetailDTO implements Serializable{
	
	
	
	  
	private Integer idApto;
	    private String titulo;
	    private String descripcion;
	    private Integer capacidad;
	    private Integer dormitorios;
	    private Integer banos;
	    private String direccion;
	    private Double coordenadax;
	    private Double coordenaday;
	    private  List<String> fotos;

	    // rating
	    private Double valoracionMedia;
	    private Long totalComentarios;
	    
	    
	    
	    //constructor
	    public ApartamentoDetailDTO(
		        Integer idApto,
		        String titulo,
		        String descripcion,
		        Integer capacidad,
		        Integer dormitorios,
		        Integer banos,
		        String direccion,
		        Double coordenadax,
		        Double coordenaday,
		        List<String> fotos,
		        Double valoracionMedia,
		        Long totalComentarios
		    ) {
		        this.idApto = idApto;
		        this.titulo = titulo;
		        this.descripcion = descripcion;
		        this.capacidad = capacidad;
		        this.dormitorios = dormitorios;
		        this.banos = banos;
		        this.direccion = direccion;
		        this.coordenadax=coordenadax;
		        this.coordenaday=coordenaday;
		        this.fotos = fotos;
		        this.valoracionMedia = valoracionMedia;
		        this.totalComentarios = totalComentarios;
		    }
	    
	    
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
		public Integer getBanos() {
			return banos;
		}
		public void setBanos(Integer banos) {
			this.banos = banos;
		}
		public String getDireccion() {
			return direccion;
		}
		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}
		public List<String> getFotos() {
			return fotos;
		}
		public void setFotos(List<String> fotos) {
			this.fotos = fotos;
		}
		public Double getValoracionMedia() {
			return valoracionMedia;
		}
		public void setValoracionMedia(Double valoracionMedia) {
			this.valoracionMedia = valoracionMedia;
		}
		public Long getTotalComentarios() {
			return totalComentarios;
		}
		public void setTotalComentarios(Long totalComentarios) {
			this.totalComentarios = totalComentarios;
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
