package com.andrea.springapirest.servicesImpl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andrea.springapirest.entities.Apartamento;
import com.andrea.springapirest.entities.Reserva;
import com.andrea.springapirest.entities.Usuario;
import com.andrea.springapirest.models.dto.ComentarioDTO;
import com.andrea.springapirest.models.dto.CrearReservaRequest;
import com.andrea.springapirest.models.dto.IngresoMensualDTO;
import com.andrea.springapirest.models.dto.OcupacionMensualDTO;
import com.andrea.springapirest.models.dto.TipoReserva;
import com.andrea.springapirest.models.dto.ValoracionRequest;
import com.andrea.springapirest.repositories.IApartamento;
import com.andrea.springapirest.repositories.IReserva;
import com.andrea.springapirest.repositories.IUsuario;
import com.andrea.springapirest.services.IReservaService;

@Service
public class ReservaServiceImpl implements IReservaService {

    @Autowired
    private IReserva reservaRepo;
    
    @Autowired
    private IUsuario usuarioRepo;

    @Autowired
    private IApartamento apartamentoRepo;

    @Override
    public List<Reserva> findAll() {
        return reservaRepo.findAll();
    }

    @Override
    public Reserva findById(Integer id) {
        return reservaRepo.findById(id).orElse(null);
    }

    @Override
    public Reserva crearReserva(CrearReservaRequest request) {
    	 
    	 // 1. Obtener ID usuario desde JWT
        Integer idUsuario = Integer.parseInt(
            SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()
                .toString()
        );

        Usuario usuario = usuarioRepo.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Apartamento apartamento = apartamentoRepo.findById(request.getApartamentoId())
            .orElseThrow(() -> new RuntimeException("Apartamento no encontrado"));

        if (request.getFechaFin().isBefore(request.getFechaPrincipio()) ||
            request.getFechaFin().isEqual(request.getFechaPrincipio())) {
            throw new RuntimeException("Fechas inválidas");
        }

        boolean ocupada = reservaRepo.hayDisponibilidad(
            apartamento.getIdApto(),
            request.getFechaPrincipio(),
            request.getFechaFin()
        );

        if (ocupada) {
            throw new RuntimeException("No hay disponibilidad en esas fechas");
        }

        Reserva reserva = new Reserva();

        reserva.setCliente(usuario);
        reserva.setApartamento(apartamento);

        reserva.setFechaPrincipio(request.getFechaPrincipio());
        reserva.setFechaFin(request.getFechaFin());
        reserva.setTipo(request.getTipo());

        reserva.setPersonas(request.getPersonas());


        long dias = ChronoUnit.DAYS.between(
            request.getFechaPrincipio(),
            request.getFechaFin()
        );

        reserva.setDuracion((int) dias);

        if (request.getTipo() == TipoReserva.BLOQUEO) {
            reserva.setPrecio(null);
            reserva.setPersonas(null);
        } else {
            reserva.setPrecio(request.getPrecio());
        }

        return reservaRepo.save(reserva);
    }

    @Override
    public List<Reserva> getReservasCliente(Authentication auth) {

        Integer userId = Integer.parseInt(auth.getName());

        return reservaRepo.findByClienteIdUsuarioAndTipoOrderByFechaPrincipioDesc(
                userId,
                TipoReserva.CLIENTE
            );
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
	
	/*private Integer getIdUsuario() {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    return Integer.parseInt(auth.getPrincipal().toString());
	}*/

	@Override
	public void valorarReserva(Integer id, ValoracionRequest request, Authentication auth) {
		 Integer userId = Integer.parseInt(auth.getName());

		    Reserva reserva = reservaRepo.findById(id)
		            .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

		    // 🔐 seguridad: solo el dueño puede valorar
		    if (!reserva.getCliente().getIdUsuario().equals(userId)) {
		        throw new RuntimeException("No autorizado");
		    }
		    if (reserva.getValoracion() != null) {
		        throw new RuntimeException("Esta reserva ya está valorada");
		    }

		    reserva.setValoracion(request.getValoracion());
		    reserva.setComentario(request.getComentario());

		    reservaRepo.save(reserva);
		
	}

	@Override
	public List<Reserva> findByApartamento(Integer id) {
		 return reservaRepo.findByApartamentoIdAptoOrderByFechaPrincipioDesc(id);
	}
	@Override
	public List<Reserva> findByRango(LocalDate inicio, LocalDate fin) {
	    return reservaRepo.findByRango(inicio, fin);
	}
	@Override
	public List<IngresoMensualDTO> getIngresos(int year) {
	    return reservaRepo.ingresosPorMes(year);
	}
	
	@Override
	public List<OcupacionMensualDTO> calcularOcupacion(int year) {

	    List<Reserva> reservas = reservaRepo.findByYear(year);

	    int totalApartamentos = apartamentoRepo.countByEstado("A");

	    Map<Integer, Integer> nochesPorMes = new HashMap<>();

	    for (int i = 1; i <= 12; i++) {
	        nochesPorMes.put(i, 0);
	    }

	    for (Reserva r : reservas) {

	        if ("BLOQUEO".equals(r.getTipo())) {
	            continue;
	        }

	        LocalDate inicio = r.getFechaPrincipio();
	        LocalDate fin = r.getFechaFin();

	        int noches = (int) ChronoUnit.DAYS.between(inicio, fin);

	        int mes = inicio.getMonthValue();

	        nochesPorMes.put(
	            mes,
	            nochesPorMes.get(mes) + noches
	        );
	    }

	    List<OcupacionMensualDTO> resultado = new ArrayList<>();

	    for (int mes = 1; mes <= 12; mes++) {

	        int diasMes = YearMonth.of(year, mes).lengthOfMonth();

	        int disponibles = diasMes * totalApartamentos;

	        double ocupacion =
	            disponibles == 0
	            ? 0
	            : (nochesPorMes.get(mes) * 100.0) / disponibles;

	        resultado.add(
	            new OcupacionMensualDTO(
	                mes,
	                Math.round(ocupacion * 100.0) / 100.0
	            )
	        );
	    }

	    return resultado;
	}
	
}
