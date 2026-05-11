package com.andrea.springapirest.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRegistroDTO {
	@NotBlank
	@Size(min= 2)
	  private String nombre;
	@NotBlank
	@Size(min= 2)
	    private String apellidos;
	@Email(message = "Email es obligatorio")
	    private String email;
	@NotBlank
	@Size(min= 8, message = "La contraseña debe tener al menos 8 carácteres")
	    private String password;
	    public String dni; 
	  @NotBlank
		@Size(min= 5, message = "El domicilio debe tener al menos 5 carácteres")
		public String domicilio; 
		
		
		public String getDni() {
			return dni;
		}
		public void setDni(String dni) {
			this.dni = dni;
		}
		public String getDomicilio() {
			return domicilio;
		}
		public void setDomicilio(String domicilio) {
			this.domicilio = domicilio;
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	    
	    

}
