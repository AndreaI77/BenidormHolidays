package com.andrea.springapirest.servicesImpl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.andrea.springapirest.entities.Apartamento;
import com.andrea.springapirest.entities.Precio;
import com.andrea.springapirest.entities.Usuario;
import com.andrea.springapirest.models.dto.ApartamentoDTO;
import com.andrea.springapirest.models.dto.ApartamentoDetailDTO;
import com.andrea.springapirest.models.dto.TipoReserva;
import com.andrea.springapirest.repositories.IApartamento;
import com.andrea.springapirest.repositories.IReserva;
import com.andrea.springapirest.repositories.IUsuario;
import com.andrea.springapirest.services.IApartamentoService;
import com.andrea.springapirest.utils.Utils;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Service
public class ApartamentoServiceImpl implements IApartamentoService {

    @Autowired
    private IApartamento apartamentoRepo;
    
    @Autowired
    private IReserva reservaRepo;
    
   
    
    @Autowired
    private IUsuario usuarioRepo;
    
    @Autowired
    private FileStorageService fileStorageService;
    
    @Autowired
    private PrecioServiceImpl precioService;
    
    @Value("${file.upload-dir}")
	private String uploadDir;
   
    @Override
    @Transactional(readOnly = true)
    public List<Apartamento> findAll() {
        return apartamentoRepo.findAllByOrderByEstadoAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Apartamento findById(Integer id) {
        return apartamentoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Apartamento no encontrado"));
    }

    @Override
    public Apartamento save(Apartamento apartamento) {
        return apartamentoRepo.save(apartamento);
    }

    @Override
    public Apartamento update(Integer id, Apartamento updated) {
        return apartamentoRepo.findById(id).map(a -> {
            a.setDireccion(updated.getDireccion());
            a.setDescripcion(updated.getDescripcion());
            a.setCapacidad(updated.getCapacidad());
            a.setEstado(updated.getEstado());
            a.setCoordenadax(updated.getCoordenadax());
            a.setCoordenaday(updated.getCoordenaday());
            a.setDormitorios(updated.getDormitorios());
            a.setBanos(updated.getBanos());
            a.setFotos(updated.getFotos());
            a.setPropietario(updated.getPropietario());
            return apartamentoRepo.save(a);
        }).orElse(null); 
    }

    @Override
    public void delete(Integer id) {
    	 

    	    boolean tieneReservas = reservaRepo.existsByApartamento_IdAptoAndTipo(
    	            id,
    	            TipoReserva.CLIENTE);

    	    if (tieneReservas) {
    	        throw new RuntimeException("No se puede eliminar: tiene reservas de clientes");
    	    }else {
    	    	String basePath= uploadDir + "/apartamentos/";
    	    	  fileStorageService.deleteApartamentoFolder(basePath,id);
    	    	  apartamentoRepo.deleteById(id);
    	    }	  

    }
    @Override
    public List<ApartamentoDTO> buscarDisponibles(
            LocalDate fechaInicio,
            LocalDate fechaFin,
            int capacidad
    ) {
        List<Apartamento> apartamentos =
                apartamentoRepo.buscarDisponibles(fechaInicio, fechaFin, capacidad);

        return apartamentos.stream()
                .map(a -> {
                    BigDecimal precio = precioService.calcularPrecio(
                            a.getIdApto(),
                            fechaInicio,
                            fechaFin,
                            capacidad
                    );

                    if (precio == null) {
                        return null;
                    }
                   
                    return new ApartamentoDTO(a, precio);
                })
                .filter(Objects::nonNull) //elimina los con precio nulo
                .toList();
    }
    
    
    
    
    //solución antes de las 2 tablas de precios
  /*  public List<ApartamentoDTO> buscarDisponibles(
            LocalDate fechaInicio,
            LocalDate fechaFin,
            int capacidad
    ) {
        List<Apartamento> apartamentos =
        		apartamentoRepo.buscarDisponibles(fechaInicio, fechaFin, capacidad);

        return apartamentos.stream()
            .map(a -> new ApartamentoDTO(
                a,
                calcularPrecioEstancia(a, fechaInicio, fechaFin, capacidad)
            ))
            .toList();
    }
    private Double calcularPrecioEstancia(
            Apartamento apartamento,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            int capacidad
    ) {
        double total = 0;

        for (LocalDate dia = fechaInicio;
             dia.isBefore(fechaFin);
             dia = dia.plusDays(1)) {
        	
        	 final LocalDate diaActual = dia;
            Precio precioDia = apartamento.getPrecios().stream()
                .filter(p ->
                    !diaActual.isBefore(p.getFechaPrincipio()) &&
                    diaActual.isBefore(p.getFechaFin())
                )
                .findFirst()
                .orElse(null);

            if (precioDia == null) {
                return null; // falta precio para algún día - no es correcto el precio final
            }
            
            double precioBase = precioDia.getPrecioNoche();

            // cálculo por personas extra
            int personasExtra = Math.max(0, capacidad - 2);
            double suplemento = personasExtra * (precioBase / 2);

            total += precioBase + suplemento;
            
        }
        return total;
    }*/
    @Override
    @Transactional(readOnly = true)
    public ApartamentoDetailDTO getDetail(Integer id) {

        Apartamento a = apartamentoRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Apartamento no encontrado"));

        Double media = reservaRepo.mediaValoracionByApartamento(id);
        Long total = reservaRepo.countValoracionesByApartamento(id);

        return new ApartamentoDetailDTO(
            a.getIdApto(),
            a.getTitulo(),
            a.getDescripcion(),
            a.getCapacidad(),
            a.getDormitorios(),
            a.getBanos(),
            a.getDireccion(),
            a.getCoordenadax(),
            a.getCoordenaday(),
            Utils.parseFotos(a.getFotos()),
            media,
            total
        );
    
    }

    @Override
    @Transactional
    public Integer createWithFiles(
            String titulo,
            String direccion,
            String descripcion,
            Integer capacidad,
            Integer dormitorios,
            Integer banos,
            String estado,
            Double coordenadax,
            Double coordenaday,
            Integer propietarioID,
            MultipartFile[] files
    ) throws IOException {
    	
    	Usuario propietario = usuarioRepo.findById(propietarioID)
    	        .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));

        Apartamento apto = new Apartamento();

        apto.setTitulo(titulo);
        apto.setDireccion(direccion);
        apto.setDescripcion(descripcion);
        apto.setCapacidad(capacidad);
        apto.setDormitorios(dormitorios);
        apto.setBanos(banos);
        apto.setEstado(estado);
        apto.setCoordenadax(coordenadax);
        apto.setCoordenaday(coordenaday);
        apto.setPropietario(propietario);

        // guardar primero para obtener id
        apto = apartamentoRepo.save(apto);

        String basePath = uploadDir + "/apartamentos/" + apto.getIdApto() + "/";

        File carpeta = new File(basePath);
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // crea la carpeta si no existe
        }

        List<String> nombresFotos = new ArrayList<String>();

        if(files != null){
        	if(files.length > 10){
        
        		throw new RuntimeException("Máximo 10 fotos permitidas");
			}else {
	
	            for(MultipartFile file : files){
	
	                String nombre = fileStorageService.storeFile(basePath, file);
	
	                nombresFotos.add(nombre);
	            }
	        }
	
	        apto.setFotos(String.join(";", nombresFotos));
        }

        return apto.getIdApto();
    }
	
    @Override
    @Transactional
    public void updateWithFiles(
            Integer id,
            String titulo,
            String direccion,
            String descripcion,
            int capacidad,
            int dormitorios,
            int banos,
            String estado,
            Double coordenadax,
            Double coordenaday,
            Integer propietarioId,
            String fotosEliminadasJson,
            MultipartFile[] files
    ) throws IOException {
    	Apartamento a = apartamentoRepo.findById(id)
    	        .orElseThrow(() -> new RuntimeException("Apartamento no encontrado"));

    	// actualizar campos
    	a.setTitulo(titulo);
    	a.setDireccion(direccion);
    	a.setDescripcion(descripcion);
    	a.setCapacidad(capacidad);
    	a.setDormitorios(dormitorios);
    	a.setBanos(banos);
    	a.setEstado(estado);
    	a.setCoordenadax(coordenadax);
    	a.setCoordenaday(coordenaday);

    	// propietario
    	Usuario propietario = usuarioRepo.findById(propietarioId)
    	        .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));

    	a.setPropietario(propietario);

    	apartamentoRepo.save(a);
    	
    	 String basePath = uploadDir + "/apartamentos/" + a.getIdApto() + "/";
  	  
    	
  	  	
	  	  File carpeta = new File(basePath);
	      if (!carpeta.exists()) {
	          carpeta.mkdirs(); // crea la carpeta si no existe
	      }
	      
	      
	      //eliminamos las fotos quitadas
	      
	  	
	    	ObjectMapper mapper = new ObjectMapper();

	    	List<String> fotosEliminadas = new ArrayList<>();

	    	if (fotosEliminadasJson != null) {
	    	    fotosEliminadas = mapper.readValue(
	    	        fotosEliminadasJson,
	    	        new TypeReference<List<String>>() {}
	    	    );
	    	}
	      
	      Path folderPath = Paths.get(basePath);

	   //  eliminar archivos físicos
	   for (String foto : fotosEliminadas) {
	       Path filePath = folderPath.resolve(foto);
	       Files.deleteIfExists(filePath);
	   }

	   List<String> nombresFotos = new ArrayList<String>();
	   
	   if(a.getFotos() != null && !a.getFotos().isEmpty()) {

		   nombresFotos = new ArrayList<>(Arrays.asList(a.getFotos().split(";")));
		   //  eliminar de la lista en BD
		   if(!fotosEliminadas.isEmpty()) {
			   nombresFotos.removeAll(fotosEliminadas);
		   }
	   }

         if(files != null){
         	if(files.length > 10){
         
         		throw new RuntimeException("Máximo 10 fotos permitidas");
 			}else {
 	
 	            for(MultipartFile file : files){
 	
 	                String nombre = fileStorageService.storeFile(basePath, file);
 	
 	                nombresFotos.add(nombre);
 	            }
 	        } 
         }
         a.setFotos(String.join(";", nombresFotos));
	     apartamentoRepo.save(a);
    }

	@Override
	public List<Apartamento> getApartamentosPropietario(String userId) {
		 return apartamentoRepo.findByPropietarioIdUsuario(userId);
	}
    
    
	
}