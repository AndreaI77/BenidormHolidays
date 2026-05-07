package com.andrea.springapirest.repositories;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrea.springapirest.entities.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer> {

	

	Collection<Usuario> findByPropietarioAndFechaBaja(String string,Date fechaBaja);
	List<Usuario> findByPropietarioOrderByFechaAltaDesc(String string);
	List<Usuario> findByEmpleadoOrderByFechaAltaDesc(String string);
	boolean existsByEmail(String email);
	Optional<Usuario> findByEmail(String email);
	Optional<Usuario> findByDNI(String dni);
	boolean existsByDNI(String email);
}
