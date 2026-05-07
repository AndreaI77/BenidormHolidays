package com.andrea.springapirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.andrea.springapirest.models.dto.ApartamentoDTO;
import com.andrea.springapirest.models.dto.ApartamentoDetailDTO;
import com.andrea.springapirest.models.dto.ApartamentoFiltroDTO;
import com.andrea.springapirest.models.dto.ComentarioDTO;
import com.andrea.springapirest.entities.Apartamento;
import com.andrea.springapirest.services.IApartamentoService;
import com.andrea.springapirest.services.IReservaService;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/apartamentos")
public class ApartamentoController {

    @Autowired
    private IApartamentoService apartamentoService;
    @Autowired
    private IReservaService reservaService;

    @GetMapping
    public List<Apartamento> getAll() {
        return apartamentoService.findAll();
    }

    @GetMapping("/debug/{id}")
    public Apartamento debug(@PathVariable Integer id) {
        return apartamentoService.findById(id);
    }
    @GetMapping("/public/{id}")
    public ApartamentoDetailDTO getDetail(@PathVariable Integer id) {
        return apartamentoService.getDetail(id);
    }
    
    @GetMapping("/apartamento/{id}")
    public Apartamento getById(@PathVariable Integer id) {
        return apartamentoService.findById(id);
    }

    @GetMapping("/public/{id}/comentarios")
    public List<ComentarioDTO> getComentarios(@PathVariable Integer id) {
        return reservaService.findComentariosByApartamento(id);
    }
 
    @PreAuthorize("hasRole('EMPLEADO')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createApartamento(
            @RequestParam("titulo") String titulo,
            @RequestParam(value = "direccion", required = true) String direccion,
            @RequestParam(value = "descripcion", required = true) String descripcion,
            @RequestParam("capacidad") int capacidad,
            @RequestParam(value = "dormitorios", required = true, defaultValue = "0") int dormitorios,
            @RequestParam(value = "banos", required = true, defaultValue = "0") int banos,
            @RequestParam(value = "estado", required = false, defaultValue = "A") String estado,
            @RequestParam(value = "coordenadax", required = false) Double coordenadax,
            @RequestParam(value = "coordenaday", required = false) Double coordenaday,
            @RequestParam(value = "propietarioId", required = true) Integer propietarioId,
            @RequestParam(value = "files", required = false) MultipartFile[] files
    ) throws IOException {

    	try {
	        Integer id = apartamentoService.createWithFiles(
	                titulo, direccion, descripcion, capacidad,
	                dormitorios, banos, estado,
	                coordenadax, coordenaday,
	                propietarioId, files
	        );
	        
	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body("Apartamento creado correctamente");
    	 } catch (Exception e) {

             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body(e.getMessage());
         }
        
    }
    

    @PostMapping("/public/buscar")
    public List<ApartamentoDTO> buscar(@RequestBody ApartamentoFiltroDTO filtro) {
    	System.out.print("Cargando apartamentos");
        return apartamentoService.buscarDisponibles(
            filtro.getFechaInicio(),
            filtro.getFechaFin(),
            filtro.getCapacidad() != null ? filtro.getCapacidad() : 1
        );
    }
    
    @PreAuthorize("hasRole('EMPLEADO')")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> updateApartamento(
            @PathVariable Integer id,
            @RequestParam("titulo") String titulo,
            @RequestParam(value = "direccion", required = true) String direccion,
            @RequestParam(value = "descripcion", required = true) String descripcion,
            @RequestParam("capacidad") int capacidad,
            @RequestParam(value = "dormitorios", required = true, defaultValue = "0") int dormitorios,
            @RequestParam(value = "banos", required = true, defaultValue = "0") int banos,
            @RequestParam(value = "estado", required = false, defaultValue = "A") String estado,
            @RequestParam(value = "coordenadax", required = false) Double coordenadax,
            @RequestParam(value = "coordenaday", required = false) Double coordenaday,
            @RequestParam(value = "propietarioId", required = true) Integer propietarioId,
            @RequestParam(value = "fotosEliminadas", required = false)String fotosEliminadasJson,
            @RequestParam(value = "files", required = false) MultipartFile[] files
    ) {
        try {

            apartamentoService.updateWithFiles(
                    id,
                    titulo, direccion, descripcion, capacidad,
                    dormitorios, banos, estado,
                    coordenadax, coordenaday,
                    propietarioId, fotosEliminadasJson,files
            );

            return ResponseEntity.ok(Map.of(
            	    "message", "Apartamento actualizado correctamente"
            		));

        } catch (Exception e) {
        	 return ResponseEntity
        	            .status(HttpStatus.INTERNAL_SERVER_ERROR)
        	            .body(Map.of(
        	                "error", e.getMessage()
        	            ));
        }
    }
    @PreAuthorize("hasRole('EMPLEADO')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        apartamentoService.delete(id);
    }
}
