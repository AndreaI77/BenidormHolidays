package com.andrea.springapirest.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrea.springapirest.entities.Servicio;
import com.andrea.springapirest.repositories.IServicio;
import com.andrea.springapirest.services.IServicioService;

@Service
public class ServicioServiceImpl implements IServicioService {

    @Autowired
    private IServicio servicioRepo;

    @Override
    public List<Servicio> findAll() { return servicioRepo.findAll(); }

    @Override
    public Servicio findById(Integer id) { return servicioRepo.findById(id).orElse(null); }

    @Override
    public Servicio saveServicio(Servicio servicio) { return servicioRepo.save(servicio); }

    @Override
    public Servicio updateServicio(Integer id, Servicio updated) {
        return servicioRepo.findById(id).map(s -> {
            s.setFecha(updated.getFecha());
            s.setHoraInicio(updated.getHoraInicio());
            s.setHoraFin(updated.getHoraFin());
            s.setEstado(updated.getEstado());
            s.setTipo(updated.getTipo());
            s.setDescripcion(updated.getDescripcion());
            s.setComentario(updated.getComentario());
            s.setReserva(updated.getReserva());
            s.setIncidencia(updated.getIncidencia());
            return servicioRepo.save(s);
        }).orElse(null);
    }

    @Override
    public void deleteServicio(Integer id) { servicioRepo.deleteById(id); }
}
