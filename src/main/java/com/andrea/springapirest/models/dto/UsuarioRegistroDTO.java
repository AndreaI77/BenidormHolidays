package com.andrea.springapirest.models.dto;

public class UsuarioRegistroDTO {
	  private String nombre;
	    private String apellidos;
	    private String email;
	    private String password;
	    public String dni;          
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
