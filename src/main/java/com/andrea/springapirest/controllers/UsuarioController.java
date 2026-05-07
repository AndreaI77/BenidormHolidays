package com.andrea.springapirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.andrea.springapirest.entities.Usuario;
import com.andrea.springapirest.exceptions.EmailExistsException;
import com.andrea.springapirest.exceptions.InvalidCredentialException;
import com.andrea.springapirest.models.dto.ChangePasswordRequest;
import com.andrea.springapirest.models.dto.LoginRequest;
import com.andrea.springapirest.models.dto.PropietarioDTO;
import com.andrea.springapirest.models.dto.UsuarioDTO;
import com.andrea.springapirest.models.dto.UsuarioProfileDTO;
import com.andrea.springapirest.models.dto.UsuarioRegistroDTO;
import com.andrea.springapirest.models.dto.UsuarioSaveResponse;
import com.andrea.springapirest.services.IUsuarioService;
import com.andrea.springapirest.utils.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;
 
    @PreAuthorize("hasRole('EMPLEADO')")
    @GetMapping("/propietarios")
    public ResponseEntity<List<PropietarioDTO>> getPropietarios(){

        return ResponseEntity.ok(usuarioService.findPropietariosActivos());
    }
    @PreAuthorize("hasRole('EMPLEADO')")
    @GetMapping("/propietarios/all")
    public ResponseEntity<List<Usuario>> getAllPropietarios(){

        return ResponseEntity.ok(usuarioService.findAllPropietarios());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/empleados/all")
    public ResponseEntity<List<Usuario>> getAllEmpleados(){

        return ResponseEntity.ok(usuarioService.findAllEmpleados());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Usuario getById(@PathVariable Integer id) {
        return usuarioService.findById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<UsuarioSaveResponse> create(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.saveOrFind(usuario));
    }

    @PostMapping("auth/register")
    public ResponseEntity<ApiResponse<Void>> registrar(@RequestBody UsuarioRegistroDTO dto)throws EmailExistsException {

        usuarioService.registrar(dto);

        return ResponseEntity.ok( ApiResponse.success("Usuario registrado correctamente", null));
    }
    @PostMapping("auth/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest dto) throws InvalidCredentialException{

        String token = usuarioService.login(dto);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(auth);
        System.out.println(auth.getAuthorities());

        return ResponseEntity.ok(
            ApiResponse.success("Login correcto", token)
        );
    }
    
    @PostMapping("auth/recuperar")
    public ResponseEntity<ApiResponse<Void>> forgotPassword(@RequestBody Map<String, String> body) {

        String email = body.get("email");

        usuarioService.resetPassword(email);

        return ResponseEntity.ok(
                ApiResponse.success("Se ha enviado una nueva contraseña al email", null)
        );
    }
    
    @GetMapping("/me")
    public ResponseEntity<UsuarioDTO> getMe(Authentication authentication) {

        Integer userId = Integer.parseInt(authentication.getName());

        UsuarioDTO dto = usuarioService.getMe(userId);

        return ResponseEntity.ok(dto);
    }
    
    @PutMapping("/change-password")
    public ResponseEntity<ApiResponse<?>> changePassword(
            Authentication authentication,
            @RequestBody ChangePasswordRequest request) {

        Integer userId = Integer.parseInt(authentication.getName());

        usuarioService.changePassword(userId, request);

        return ResponseEntity.ok(
            ApiResponse.success("Contraseña actualizada correctamente", null)
        );
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Usuario update(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(id, usuario);
    }

    /*@DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
    }*/
    
    @GetMapping("/profile")
    public ResponseEntity<UsuarioProfileDTO> getProfile(Authentication authentication) {

        Integer userId = Integer.parseInt(authentication.getName());

        return ResponseEntity.ok(usuarioService.getProfile(userId));
    }
    
    @PutMapping(value = "/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UsuarioProfileDTO> updateProfile(
            Authentication authentication,

            @Valid @RequestPart("usuario") UsuarioProfileDTO usuario,

            @RequestPart(required = false) MultipartFile foto,

            @RequestParam(defaultValue = "false") boolean deleteFoto
    ) {

        Integer userId = Integer.parseInt(authentication.getName());

        UsuarioProfileDTO updated = usuarioService.updateProfile(
                userId,
                usuario,
                foto,
                deleteFoto
        );

        return ResponseEntity.ok(updated);
    }
    
    
}
