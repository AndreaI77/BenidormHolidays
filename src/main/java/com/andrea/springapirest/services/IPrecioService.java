package com.andrea.springapirest.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.andrea.springapirest.entities.PrecioDia;
import com.andrea.springapirest.models.dto.PrecioDiaDTO;

public interface IPrecioService {
	
    
	public BigDecimal calcularPrecio(Integer idApto,
            LocalDate inicio,
            LocalDate fin,
            int personas);
	
	
	 PrecioDia crear(PrecioDiaDTO dto);

}
