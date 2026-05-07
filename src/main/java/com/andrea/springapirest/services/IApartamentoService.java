package com.andrea.springapirest.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.andrea.springapirest.entities.Apartamento;
import com.andrea.springapirest.models.dto.ApartamentoDTO;
import com.andrea.springapirest.models.dto.ApartamentoDetailDTO;

public interface IApartamentoService {
	  List<Apartamento> findAll();
	  Apartamento findById(Integer id);
	  Apartamento save(Apartamento apartamento);
	  Apartamento update(Integer id, Apartamento apartamento);
	  void delete(Integer id);
	  ApartamentoDetailDTO getDetail(Integer id);
	  List<ApartamentoDTO> buscarDisponibles(LocalDate fechaInicio, LocalDate fechaFin, int capacidad);
	  
	
	 Integer createWithFiles(
	            String titulo,
	            String direccion,
	            String descripcion,
	            Integer capacidad,
	            Integer dormitorios,
	            Integer banos,
	            String estado,
	            Double coordenadax,
	            Double coordenaday,
	            Integer propietarioID,
	            MultipartFile[] files
	    ) throws IOException;
	 
	void updateWithFiles(
			Integer id, 
			String titulo, 
			String direccion, 
			String descripcion, 
			int capacidad,
			int dormitorios, 
			int banos, 
			String estado, 
			Double coordenadax, 
			Double coordenaday, 
			Integer propietarioId,
			String fotosEliminadasJson,
			MultipartFile[] files
			) throws IOException;
	List<Apartamento> getApartamentosPropietario(String userId);
	 
	 
	 
	 
}
