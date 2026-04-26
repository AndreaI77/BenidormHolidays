package com.andrea.springapirest.models.dto;

import java.util.List;

public class UsuarioDTO {
	 private Integer id;
	 private String email;
	 private String nombre;
	 private String apellidos;
	 private List<String> roles;
	 private String avatarUrl;
	 
	 
	
	public UsuarioDTO(Integer id, String email, String nombre, String apellidos, List<String> roles, String avatarUrl) {
		super();
		this.id = id;
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.roles = roles;
		this.avatarUrl = avatarUrl;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	 
	 
}
