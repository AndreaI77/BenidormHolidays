package com.andrea.springapirest.services;

import java.util.List;

import com.andrea.springapirest.entities.Incidencia;

public interface IIncidenciaService {
	 List<Incidencia> findAll();
	 Incidencia findById(Integer id);
	 Incidencia saveIncidencia(Incidencia incidencia);
	 Incidencia updateIncidencia(Integer id, Incidencia incidencia);
	 void deleteIncidencia(Integer id);

}
