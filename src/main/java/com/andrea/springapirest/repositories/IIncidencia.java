package com.andrea.springapirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrea.springapirest.entities.Incidencia;


@Repository
public interface IIncidencia extends JpaRepository<Incidencia, Integer> {
   
}