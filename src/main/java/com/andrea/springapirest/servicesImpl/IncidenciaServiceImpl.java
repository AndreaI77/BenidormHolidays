package com.andrea.springapirest.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrea.springapirest.entities.Incidencia;
import com.andrea.springapirest.repositories.IIncidencia;
import com.andrea.springapirest.services.IIncidenciaService;

@Service
public class IncidenciaServiceImpl implements IIncidenciaService {

    @Autowired
    private IIncidencia incidenciaRepo;

    @Override
    public List<Incidencia> findAll() { return incidenciaRepo.findAll(); }

    @Override
    public Incidencia findById(Integer id) { return incidenciaRepo.findById(id).orElse(null); }

    @Override
    public Incidencia saveIncidencia(Incidencia incidencia) { return incidenciaRepo.save(incidencia); }

    @Override
    public Incidencia updateIncidencia(Integer id, Incidencia updated) {
        return incidenciaRepo.findById(id).map(i -> {
            i.setFecha(updated.getFecha());
            i.setTipo(updated.getTipo());
            i.setDescripcion(updated.getDescripcion());
            i.setEstado(updated.getEstado());
            i.setEmpleado(updated.getEmpleado());
            i.setReserva(updated.getReserva());
            return incidenciaRepo.save(i);
        }).orElse(null);
    }

    @Override
    public void deleteIncidencia(Integer id) { incidenciaRepo.deleteById(id); }
}
