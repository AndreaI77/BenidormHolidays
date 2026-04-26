package com.andrea.springapirest.controllers;

import java.io.IOException;
import java.nio.file.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fotos")
public class FotoController {
	@Value("${file.upload-dir}")
	private String uploadDir;

    //private final String UPLOAD_DIR = "uploads/apartamentos/";

    @GetMapping("/apartamentos/{idApto}/{nombre}")
    public ResponseEntity<Resource> getFoto(
            @PathVariable Integer idApto,
            @PathVariable String nombre) throws IOException {

        //Path ruta = Paths.get(UPLOAD_DIR + idApto + "/fotos/" + nombre);
        Path ruta = Paths.get(uploadDir+"/apartamentos/" + idApto + "/" + nombre);

        Resource recurso = new UrlResource(ruta.toUri());

        if (!recurso.exists()) {
            return ResponseEntity.notFound().build();
        }

        String tipo = Files.probeContentType(ruta);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(tipo))
                .body(recurso);
    }
    
    @GetMapping("/usuarios/{idUsuario}/{nombre}")
    public ResponseEntity<Resource> getFotoPerfil(
            @PathVariable Integer idUsuario,
            @PathVariable String nombre) throws IOException {

        //Path ruta = Paths.get(UPLOAD_DIR + idApto + "/fotos/" + nombre);
        Path ruta = Paths.get(uploadDir+"/usuarios/" + idUsuario + "/" + nombre);

        Resource recurso = new UrlResource(ruta.toUri());

        if (!recurso.exists()) {
            return ResponseEntity.notFound().build();
        }

        String tipo = Files.probeContentType(ruta);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(tipo))
                .body(recurso);
    }
}