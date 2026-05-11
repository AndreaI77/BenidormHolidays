package com.andrea.springapirest.models.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UsuarioProfileDTO {
	 private Integer idUsuario;

	 @NotBlank(message = "El nombre es obligatorio")
	    private String nombre;

	    @NotBlank(message = "Los apellidos son obligatorios")
	    private String apellidos;

	    @Email(message = "Email inválido")
	    private String email;

	    @NotBlank(message = "La dirección es obligatoria")
	    private String domicilio;
	    
	 
	    private String telefono;
	    private LocalDate fechaNac;
	    private String DNI;
	    private String fotoPerfil;
	    
	    
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
		public String getDomicilio() {
			return domicilio;
		}
		public void setDomicilio(String domicilio) {
			this.domicilio = domicilio;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getTelefono() {
			return telefono;
		}
		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
		public LocalDate getFechaNac() {
			return fechaNac;
		}
		public void setFechaNac(LocalDate fechaNac) {
			this.fechaNac = fechaNac;
		}
		public String getDNI() {
			return DNI;
		}
		public void setDNI(String dni) {
			this.DNI = dni;
		}
		public String getFotoPerfil() {
			return fotoPerfil;
		}
		public void setFotoPerfil(String fotoPerfil) {
			this.fotoPerfil = fotoPerfil;
		}
	    
	    
	    
}
