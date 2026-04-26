package com.andrea.springapirest.models.dto;

import com.andrea.springapirest.entities.Usuario;

public class UsuarioSaveResponse {
	private boolean exists;
	private Usuario usuario;

	public UsuarioSaveResponse(boolean exists, Usuario usuario) {
	        this.exists = exists;
	        this.usuario = usuario;
	 }

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
