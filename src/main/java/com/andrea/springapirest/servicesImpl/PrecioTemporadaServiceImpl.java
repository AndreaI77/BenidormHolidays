package com.andrea.springapirest.servicesImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.andrea.springapirest.entities.PrecioTemporada;
import com.andrea.springapirest.models.dto.PrecioTemporadaDTO;
import com.andrea.springapirest.repositories.IApartamento;
import com.andrea.springapirest.repositories.IPrecioTemporadaRepository;
import com.andrea.springapirest.services.IPrecioTemporadaService;


@Service
public class PrecioTemporadaServiceImpl implements IPrecioTemporadaService{
	
	 @Autowired
	 private IPrecioTemporadaRepository repo;

	 @Autowired
	 private IApartamento apartamentoRepo;

	@Override
	public PrecioTemporada crear(PrecioTemporadaDTO dto) {
		validarSolapamiento(dto.getIdApto(), dto.getFechaInicio(), dto.getFechaFin());

	    PrecioTemporada p = new PrecioTemporada();

	    p.setApartamento(
	    		 apartamentoRepo.getReferenceById(dto.getIdApto())
	    );

	    p.setFechaInicio(dto.getFechaInicio());
	    p.setFechaFin(dto.getFechaFin());
	    p.setPrecioNoche(dto.getPrecioNoche());
	    p.setNombreTemporada(dto.getNombreTemporada());

	    return repo.save(p);
	}

	@Override
	public PrecioTemporada actualizar(Integer id, PrecioTemporadaDTO dto) {
		  PrecioTemporada p = repo.findById(id)
			        .orElseThrow(() -> new RuntimeException("No existe la temporada"));

			    validarSolapamientoEdicion(
			        dto.getIdApto(),
			        dto.getFechaInicio(),
			        dto.getFechaFin(),
			        id
			    );

			    p.setFechaInicio(dto.getFechaInicio());
			    p.setFechaFin(dto.getFechaFin());
			    p.setPrecioNoche(dto.getPrecioNoche());
			    p.setNombreTemporada(dto.getNombreTemporada());

			    return repo.save(p);
	}

	@Override
	public void eliminar(Integer id) {
		 repo.deleteById(id);
		
	}

	@Override
	public List<PrecioTemporada> listarPorApartamento(Integer idApto) {
		 return repo.findByApartamento_IdApto(idApto);
		
	}
	
	
	private void validarSolapamiento(Integer idApto,
	            LocalDate inicio,
	            LocalDate fin) {
	
		List<PrecioTemporada> solapados =
		repo.findSolapados(idApto, inicio, fin);
		
		if (!solapados.isEmpty()) {
		throw new IllegalArgumentException("Temporada solapada");
		}
	}
	
	private void validarSolapamientoEdicion(Integer idApto,
	            LocalDate inicio,
	            LocalDate fin,
	            Integer idActual) {
	
		List<PrecioTemporada> solapados =
		repo.findSolapados(idApto, inicio, fin)
		.stream()
		.filter(p -> !p.getId().equals(idActual))
		.toList();
		
		if (!solapados.isEmpty()) {
			throw new IllegalArgumentException("Temporada solapada");
		}
	}

	@Override
	public PrecioTemporada obtenerPorId(Integer id) {
		 Optional<PrecioTemporada> opt = repo.findById(id);

		    if (opt.isEmpty()) {
		        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontrado");
		    }
		    return opt.get();
	}

	

}
