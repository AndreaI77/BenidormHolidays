package com.andrea.springapirest.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andrea.springapirest.entities.Reserva;
import com.andrea.springapirest.models.dto.ComentarioDTO;
import com.andrea.springapirest.repositories.IReserva;
import com.andrea.springapirest.services.IReservaService;

@Service
public class ReservaServiceImpl implements IReservaService {

    @Autowired
    private IReserva reservaRepo;

    @Override
    public List<Reserva> findAll() {
        return reservaRepo.findAll();
    }

    @Override
    public Reserva findById(Integer id) {
        return reservaRepo.findById(id).orElse(null);
    }

    @Override
    public Reserva save(Reserva reserva) {
        return reservaRepo.save(reserva);
    }

    @Override
    public Reserva update(Integer id, Reserva updated) {
        return reservaRepo.findById(id).map(r -> {
            r.setFechaPrincipio(updated.getFechaPrincipio());
            r.setFechaFin(updated.getFechaFin());
            r.setDuracion(updated.getDuracion());
            r.setPersonas(updated.getPersonas());
            r.setPrecio(updated.getPrecio());
            r.setComentario(updated.getComentario());
            r.setValoracion(updated.getValoracion());
            r.setCliente(updated.getCliente());
            r.setApartamento(updated.getApartamento());
            return reservaRepo.save(r);
        }).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        reservaRepo.deleteById(id);
    }

	@Override
	public List<ComentarioDTO> findComentariosByApartamento(Integer id) {
		// TODO Auto-generated method stub
		return reservaRepo.findComentariosByApartamento(id);
	}

	@Override
	public Double mediaValoracionByApartamento(Integer id) {
		// TODO Auto-generated method stub
		return reservaRepo.mediaValoracionByApartamento(id);
	}

	@Override
	public Long countValoracionesByApartamento(Integer id) {
		// TODO Auto-generated method stub
		return reservaRepo.countValoracionesByApartamento(id);
	}

	@Override
	public boolean existsByApartamentoId(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
