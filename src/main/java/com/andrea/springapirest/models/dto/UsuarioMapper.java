package com.andrea.springapirest.models.dto;

import org.mapstruct.Mapper;

import com.andrea.springapirest.entities.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioProfileDTO toDTO(Usuario usuario);

}
