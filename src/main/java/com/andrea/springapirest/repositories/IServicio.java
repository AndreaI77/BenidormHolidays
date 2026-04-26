package com.andrea.springapirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrea.springapirest.entities.Servicio;


@Repository
public interface IServicio extends JpaRepository<Servicio, Integer> {

}