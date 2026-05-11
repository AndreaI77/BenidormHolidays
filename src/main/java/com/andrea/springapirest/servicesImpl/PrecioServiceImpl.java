package com.andrea.springapirest.servicesImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrea.springapirest.entities.Apartamento;
import com.andrea.springapirest.entities.PrecioDia;
import com.andrea.springapirest.models.dto.PrecioDiaDTO;
import com.andrea.springapirest.repositories.IPrecioDiaRepository;
import com.andrea.springapirest.repositories.IPrecioTemporadaRepository;
import com.andrea.springapirest.services.IPrecioService;

@Service
public class PrecioServiceImpl implements IPrecioService {
	
	 @Autowired
	private IPrecioTemporadaRepository precioTemporadaRepo;
	 
	 @Autowired
	 private IPrecioDiaRepository precioDiaRepo;
	 
	 
	 @Override
	 public BigDecimal calcularPrecio(
	         Integer idApto,
	         LocalDate fechaInicio,
	         LocalDate fechaFin,
	         int personas
	 ) {

	     BigDecimal total = BigDecimal.ZERO;

	     for (LocalDate dia = fechaInicio;
	          dia.isBefore(fechaFin);
	          dia = dia.plusDays(1)) {

	         final LocalDate diaActual = dia;

	         BigDecimal precioBase;

	         // PRECIO DÍA
	         Optional<BigDecimal> precioDia = precioDiaRepo
	                 .findPrecioByApartamentoIdAptoAndFecha(idApto, diaActual);

	         if (precioDia.isPresent()) {
	             precioBase = precioDia.get();
	         } else {

	             // TEMPORADA
	             Optional<BigDecimal> precioTemporada = precioTemporadaRepo
	                     .findPrecioByApartamentoIdAptoAndFecha(idApto, diaActual);

	             if (precioTemporada.isEmpty()) {
	                return null;
	                
	             }

	             precioBase = precioTemporada.get();
	         }

	         precioBase = aplicarReglaPersonas(precioBase, personas);

	         total = total.add(precioBase);
	     }

	     return total;
	 }

	/*@Override
	public BigDecimal calcularPrecio(
	        Integer idApto,
	        LocalDate fechaInicio,
	        LocalDate fechaFin,
	        int personas
	) {

		BigDecimal total = BigDecimal.ZERO;

	    for (LocalDate dia = fechaInicio;
	         dia.isBefore(fechaFin);
	         dia = dia.plusDays(1)) {

	        final LocalDate diaActual = dia;

	       
	        BigDecimal precioBase;

	     // PRECIO DÍA
	        Optional<BigDecimal> precioDia = precioDiaRepo
	                .findPrecioByApartamentoAndFecha(idApto, diaActual);

	        if (precioDia.isPresent()) {
	            precioBase = precioDia.get();
	        } else {

	            // TEMPORADA
	            Optional<BigDecimal> precioTemporada = precioTemporadaRepo
	                    .findPrecioByApartamento_IdAptoAndFecha(idApto, diaActual);

	            if (precioTemporada.isEmpty()) {
	                return null; 
	            }

	            precioBase = precioTemporada.get();
	        }

	        // 🧠 calculo personas extra
	        precioBase = aplicarReglaPersonas(precioBase, personas);
	        

	        total = total.add(precioBase);
	    }

	    

	    return total;
	}*/
	
	private BigDecimal aplicarReglaPersonas(BigDecimal precioBase, int personas) {

	    if (personas <= 2) {
	        return precioBase;
	    }

	    int extra = personas - 2;

	    BigDecimal incremento = precioBase
	            .multiply(BigDecimal.valueOf(0.5))
	            .multiply(BigDecimal.valueOf(extra));

	    return precioBase.add(incremento);
	}
	
	  @Override
	    public PrecioDia crear(PrecioDiaDTO dto) {

	        // 🔴 VALIDACIÓN: evitar duplicados
	        boolean existe = precioDiaRepo
	                .existsByApartamento_IdAptoAndFecha(dto.getIdApto(), dto.getFecha());

	        if (existe) {
	            throw new IllegalArgumentException("Ya existe un precio para ese día");
	        }

	        // 🧱 MAPEO
	        PrecioDia precio = new PrecioDia();

	        Apartamento apto = new Apartamento();
	        apto.setIdApto(dto.getIdApto());

	        precio.setApartamento(apto);
	        precio.setFecha(dto.getFecha());
	        precio.setPrecioNoche(dto.getPrecioNoche());
	        precio.setMotivo(dto.getMotivo());

	        return precioDiaRepo.save(precio);
	    }
 
}
