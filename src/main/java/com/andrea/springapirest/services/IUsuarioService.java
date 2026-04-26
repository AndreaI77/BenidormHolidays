package com.andrea.springapirest.services;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.web.multipart.MultipartFile;

import com.andrea.springapirest.entities.Usuario;
import com.andrea.springapirest.models.dto.ChangePasswordRequest;
import com.andrea.springapirest.models.dto.LoginRequest;
import com.andrea.springapirest.models.dto.PropietarioDTO;
import com.andrea.springapirest.models.dto.UsuarioDTO;
import com.andrea.springapirest.models.dto.UsuarioProfileDTO;
import com.andrea.springapirest.models.dto.UsuarioRegistroDTO;
import com.andrea.springapirest.models.dto.UsuarioSaveResponse;

public interface IUsuarioService {
	 
	 Usuario findById(Integer id);
	 Usuario saveUsuario(Usuario usuario);
	 Usuario updateUsuario(Integer id, Usuario usuario);
	 void deleteUsuario(Integer id);
	 List<PropietarioDTO> findPropietariosActivos();
	 public void registrar(UsuarioRegistroDTO dto);
	 public String login(LoginRequest dto);
	void resetPassword(String email);
	UsuarioDTO getMe(Integer userId);
	void changePassword(Integer userId, ChangePasswordRequest request);
	public UsuarioProfileDTO  getProfile(Integer userId);
	UsuarioProfileDTO updateProfile(Integer userId, UsuarioProfileDTO usuario, MultipartFile foto, boolean deleteFoto);
	List<Usuario> findAllPropietarios();	
	List<Usuario> findAllEmpleados();
	UsuarioSaveResponse saveOrFind(Usuario usuario);
	
	
	
	
}
