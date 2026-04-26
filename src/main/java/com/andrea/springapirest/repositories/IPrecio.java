package com.andrea.springapirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrea.springapirest.entities.Precio;


@Repository
public interface IPrecio extends JpaRepository<Precio, Integer> {
}
