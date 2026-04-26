package com.andrea.springapirest.services;

import java.util.List;

import com.andrea.springapirest.entities.Servicio;

public interface IServicioService {
	 List<Servicio> findAll();
	 Servicio findById(Integer id);
	 Servicio saveServicio(Servicio servicio);
	 Servicio updateServicio(Integer id, Servicio servicio);
	 void deleteServicio(Integer id);

}
