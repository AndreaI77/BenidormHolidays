package com.andrea.springapirest.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.andrea.springapirest.entities.Precio;
import com.andrea.springapirest.entities.PrecioDia;
import com.andrea.springapirest.models.dto.PrecioDiaDTO;

public interface IPrecioService {
	/*List<Precio> findAll();
    Precio findById(Integer id);
    Precio savePrecio(Precio precio);
    Precio updatePrecio(Integer id, Precio precio);
    void deletePrecio(Integer id);*/
    
	public BigDecimal calcularPrecio(Integer idApto,
            LocalDate inicio,
            LocalDate fin,
            int personas);
	
	
	 PrecioDia crear(PrecioDiaDTO dto);

}
