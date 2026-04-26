package com.andrea.springapirest.models.dto;

public class PropietarioDTO {

	 private Integer idUsuario;
	    private String nombre;
	    private String apellidos;

	    public PropietarioDTO(Integer idUsuario, String nombre, String apellidos) {
	        this.idUsuario = idUsuario;
	        this.nombre = nombre;
	        this.apellidos = apellidos;
	    }

		public Integer getIdUsuario() {
			return idUsuario;
		}

		public void setIdUsuario(Integer idUsuario) {
			this.idUsuario = idUsuario;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellidos() {
			return apellidos;
		}

		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}

	
}
