package com.andrea.springapirest.models.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ComentarioDTO {
	private int idUsuario;
	 private String nombre;
	    private LocalDate fechaEntrada;
	    private Integer valoracion; // 1 a 5
	    private String comentario;

	    public ComentarioDTO(
	    		int idCliente,
	            String nombre,
	            LocalDate fechaEntrada,
	            Integer valoracion,
	            String comentario
	        ) {
	    	this.idUsuario=idCliente;
	            this.nombre = nombre;
	            this.fechaEntrada = fechaEntrada;
	            this.valoracion = valoracion;
	            this.comentario = comentario;
	        }

		public int getIdUsuario() {
			return idUsuario;
		}

		public void setIdUsuario(int idUsuario) {
			this.idUsuario = idUsuario;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public LocalDate getFechaEntrada() {
			return fechaEntrada;
		}

		public void setFechaEntrada(LocalDate fechaEntrada) {
			this.fechaEntrada = fechaEntrada;
		}

		public Integer getValoracion() {
			return valoracion;
		}

		public void setValoracion(Integer valoracion) {
			this.valoracion = valoracion;
		}

		public String getComentario() {
			return comentario;
		}

		public void setComentario(String comentario) {
			this.comentario = comentario;
		}
	    
}
