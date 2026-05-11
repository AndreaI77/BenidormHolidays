package com.andrea.springapirest.servicesImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.andrea.springapirest.entities.Usuario;
import com.andrea.springapirest.exceptions.DNIExistsException;
import com.andrea.springapirest.exceptions.DNIYEmailExistsException;
import com.andrea.springapirest.exceptions.EmailExistsException;
import com.andrea.springapirest.exceptions.InvalidCredentialException;
import com.andrea.springapirest.models.dto.ChangePasswordRequest;
import com.andrea.springapirest.models.dto.LoginRequest;
import com.andrea.springapirest.models.dto.PropietarioDTO;
import com.andrea.springapirest.models.dto.UsuarioDTO;
import com.andrea.springapirest.models.dto.UsuarioMapper;
import com.andrea.springapirest.models.dto.UsuarioProfileDTO;
import com.andrea.springapirest.models.dto.UsuarioRegistroDTO;
import com.andrea.springapirest.models.dto.UsuarioSaveResponse;
import com.andrea.springapirest.repositories.IUsuario;
import com.andrea.springapirest.security.JwtUtil;
import com.andrea.springapirest.services.IUsuarioService;
import com.andrea.springapirest.utils.Utils;



@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuario usuarioRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailServiceImpl emailService;
    
    @Autowired
    private FileStorageService fileStorageService;
    
    @Value("${file.upload-dir}")
	private String uploadDir;

    
    private final JwtUtil jwtUtil;

    private final UsuarioMapper mapper;

    public UsuarioServiceImpl(JwtUtil jwtUtil, UsuarioMapper mapper) {
        this.jwtUtil = jwtUtil;
		this.mapper = mapper;
    }
   
    @Override
   public void registrar(UsuarioRegistroDTO dto) {

        if (usuarioRepo.existsByEmail(dto.getEmail())) {
        	 throw new EmailExistsException("EMAIL_EXISTS");
        }
        if (usuarioRepo.existsByDNI(dto.getDni())) {
       	 throw new DNIExistsException("DNI_EXISTS");
       }

        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setApellidos(dto.getApellidos());
        usuario.setEmail(dto.getEmail());
        usuario.setDNI(dto.getDni());
        usuario.setDomicilio(dto.getDomicilio());

        // 🔐 ENCRIPTAR PASSWORD
        usuario.setContrasena(passwordEncoder.encode(dto.getPassword()));      
        usuario.setCliente("S");
        

        usuario.setFechaAlta(LocalDate.now());
        usuarioRepo.save(usuario);
    }  
    
    public String login(LoginRequest dto) {

        Usuario user = usuarioRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new InvalidCredentialException("INVALID_CREDENTIALS"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getContrasena())) {
            throw new InvalidCredentialException("INVALID_CREDENTIALS");
        }
        List<String> roles = new ArrayList<>();

        if ("S".equals(user.getPropietario())) roles.add("PROPIETARIO");
        if ("S".equals(user.getAdministrador())) roles.add("ADMIN");
        if ("S".equals(user.getCliente())) roles.add("CLIENTE");
        if ("S".equals(user.getEmpleado())) roles.add("EMPLEADO");

        return jwtUtil.generateToken(user.getIdUsuario(),user.getEmail(), roles);
    }
    
    public void resetPassword(String email) {

       Usuario user = usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("EMAIL_NOT_FOUND"));

        String newPassword = Utils.generateRandomPassword();

        user.setContrasena(passwordEncoder.encode(newPassword));

        usuarioRepo.save(user);

        emailService.sendPasswordEmail(user.getEmail(), newPassword);
    }

    @Override
    public Usuario findById(Integer id) {
        return usuarioRepo.findById(id).orElse(null);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
    	
    	 validarEmailYDni(usuario);
    	/* Optional<Usuario> byEmail = usuarioRepo.findByEmail(usuario.getEmail());
    	  Optional<Usuario> byDni = usuarioRepo.findByDNI(usuario.getDNI());

    	    if (usuario.getIdUsuario() == null) {
    	        // CREATE
    	    	 usuario.setFechaAlta(LocalDate.now());
    	        if (byEmail.isPresent()) {
    	            throw new EmailExistsException("EMAIL_EXISTS");
    	        }
    	        if (byDni.isPresent()) {
    	            throw new DNIExistsException("DNI_EXISTS");
    	        }
    	    } else {
    	        // UPDATE (excluyendo el mismo usuario)
    	        if (byEmail.isPresent() && !byEmail.get().getIdUsuario().equals(usuario.getIdUsuario())) {
    	            throw new EmailExistsException("EMAIL_EXISTS");
    	        }

    	        if (byDni.isPresent() && !byDni.get().getIdUsuario().equals(usuario.getIdUsuario())) {
    	            throw new DNIExistsException("DNI_EXISTS");
    	        }
    	    }*/
    	    usuario.setFechaAlta(LocalDate.now());
    	    
        return  usuarioRepo.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Integer id, Usuario updated) {
    	 
    	validarEmailYDni(updated);
    	 
        return usuarioRepo.findById(id).map(u -> {
            u.setNombre(updated.getNombre());
            u.setApellidos(updated.getApellidos());
            u.setDomicilio(updated.getDomicilio());
            u.setDNI(updated.getDNI());
            u.setEmail(updated.getEmail());
          
            u.setTelefono(updated.getTelefono());
            u.setFechaNac(updated.getFechaNac());
           // u.setFechaAlta(updated.getFechaAlta());
            u.setFechaBaja(updated.getFechaBaja());
            u.setObservaciones(updated.getObservaciones());
           
            u.setPropietario(updated.getPropietario());
            u.setEmpleado(updated.getEmpleado());
            if(!updated.getEmpleado().equals("S")) {
            	u.setAdministrador("N");
            }else {
            	 u.setAdministrador(updated.getAdministrador());
            }
            u.setCliente(u.getCliente());
            
            return usuarioRepo.save(u);
        }).orElse(null);
    }

    private void validarEmailYDni(Usuario usuario) {

    	 Optional<Usuario> emailExistente =usuarioRepo.findByEmail(usuario.getEmail());

    	    Optional<Usuario> dniExistente =usuarioRepo.findByDNI(usuario.getDNI());

    	    if (emailExistente.isPresent() &&
    	        !emailExistente.get()
    	            .getIdUsuario()
    	            .equals(usuario.getIdUsuario())) {

    	        throw new EmailExistsException("EMAIL_EXISTS");
    	    }

    	    if (dniExistente.isPresent() &&
    	        !dniExistente.get()
    	            .getIdUsuario()
    	            .equals(usuario.getIdUsuario())) {

    	        throw new DNIExistsException("DNI_EXISTS");
    	    }
    }
    
    @Override
    public void deleteUsuario(Integer id) {
        usuarioRepo.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<PropietarioDTO> findPropietariosActivos() {

        return usuarioRepo.findByPropietarioAndFechaBaja("S", null)
                .stream()
                .map(u -> new PropietarioDTO(
                        u.getIdUsuario(),
                        u.getNombre(),
                        u.getApellidos()
                ))
                .toList();
    }

	@Override
	public UsuarioDTO getMe(Integer userId) {
		System.out.print("entrada getMe");
		 Usuario user = usuarioRepo.findById(userId)
		            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		 List<String> roles = new ArrayList<>();

		 if ("S".equals(user.getAdministrador())) {
		     roles.add("ADMIN");
		 }

		 if ("S".equals(user.getCliente())) {
		     roles.add("CLIENTE");
		 }

		 if ("S".equals(user.getPropietario())) {
		     roles.add("PROPIETARIO");
		 }

		 if ("S".equals(user.getEmpleado())) {
		     roles.add("EMPLEADO");
	                                                                      	 }
	
		    return new UsuarioDTO(
		            user.getIdUsuario(),
		            user.getEmail(),
		            user.getNombre(),
		            user.getApellidos(),
		            roles,
		            user.getFotoPerfil()
		    );		       
	}

	@Override
	public void changePassword(Integer userId, ChangePasswordRequest request) {
		 Usuario user = usuarioRepo.findById(userId)
		            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		    if (!passwordEncoder.matches(request.getCurrentPassword(), user.getContrasena())) {
		    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contraseña incorrecta");
		    }

		    user.setContrasena(passwordEncoder.encode(request.getNewPassword()));

		    usuarioRepo.save(user);
		
	}
	
	public UsuarioProfileDTO getProfile(Integer id) {
	    Usuario u = usuarioRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	    
	    return mapper.toDTO(u);
	}

	@Override
	@Transactional
	public UsuarioProfileDTO updateProfile (
	        Integer userId,
	        UsuarioProfileDTO dto,
	        MultipartFile foto,
	        boolean deleteFoto
	) {
		  System.out.println(">>> ENTRANDO EN updateProfile");
	    Usuario usuario = usuarioRepo.findById(userId)
	            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	    
	  System.out.print("usuario recuperado de la bd: "+usuario.getIdUsuario());
	  

	    
	    usuario.setNombre(dto.getNombre());
	    usuario.setApellidos(dto.getApellidos());
	    usuario.setEmail(dto.getEmail());
	    usuario.setTelefono(dto.getTelefono());
	    usuario.setDomicilio(dto.getDomicilio());
	    usuario.setDNI(dto.getDNI());
	   usuario.setFechaNac(dto.getFechaNac());

	    usuarioRepo.flush();
	    System.out.println("ANTES VALIDACION: " + usuario.getEmail());
	    validarEmailYDni(usuario); //compruebo los datos del usuario antes de guardar la foto.
	    
	    System.out.println("despues VALIDACION: " + usuario.getEmail());
	    String basePath = uploadDir + "/usuarios/" + userId + "/";

	   
	    if (deleteFoto && usuario.getFotoPerfil() != null) {
	        fileStorageService.deleteFile(basePath + usuario.getFotoPerfil());
	        usuario.setFotoPerfil(null);
	    }

	    
	    if (foto != null && !foto.isEmpty()) {

	        // borrar anterior si existe
	        if (usuario.getFotoPerfil() != null) {
	            fileStorageService.deleteFile(basePath + usuario.getFotoPerfil());
	        }

	        try {
	            String fileName = fileStorageService.storeFile(basePath, foto);
	            usuario.setFotoPerfil(fileName);

	        } catch (IOException e) {
	            throw new RuntimeException("Error subiendo foto de perfil", e);
	        }
	    }

	    usuarioRepo.save(usuario);
	    
	    return mapper.toDTO(usuario);
	}

	@Override
	public List<Usuario> findAllPropietarios() {
		return usuarioRepo.findByPropietarioOrderByFechaAltaDesc("S");
		
	}
	@Override
	public List<Usuario> findAllEmpleados() {
		return usuarioRepo.findByEmpleadoOrderByFechaAltaDesc("S");
		
	}

	@Override
	public UsuarioSaveResponse saveOrFind(Usuario usuario) {
		 Optional<Usuario> porEmail = usuarioRepo.findByEmail(usuario.getEmail());
		    Optional<Usuario> porDni = usuarioRepo.findByDNI(usuario.getDNI());

		    
		    if (porEmail.isPresent() && porDni.isPresent()
		        && !porEmail.get().getIdUsuario().equals(porDni.get().getIdUsuario())) {

		        throw new DNIYEmailExistsException("DNI Y EMAIL_EXISTS");
		    }

		   
		    if (porEmail.isPresent()) {
		        return new UsuarioSaveResponse(true, porEmail.get());
		    }

		    if (porDni.isPresent()) {
		        return new UsuarioSaveResponse(true, porDni.get());
		    }

		    
		    usuario.setFechaAlta(LocalDate.now());
		    String newPassword = Utils.generateRandomPassword();		    
            usuario.setContrasena(passwordEncoder.encode(newPassword));
		    Usuario guardado = usuarioRepo.save(usuario);
		    

            emailService.sendNewUserEmail(usuario.getEmail(), newPassword);

		    return new UsuarioSaveResponse(false, guardado);
		
	}
}