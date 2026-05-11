package com.andrea.springapirest.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChangePasswordRequest {
	
	 private String currentPassword;

	 @NotBlank(message = "Nueva contraseña es obligatoria")
	 @Size(min = 8, max = 15,
	          message = "La nueva contraseña debe tener entre 8 y 15 caracteres")
	 private String newPassword;
	 
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	    
}
