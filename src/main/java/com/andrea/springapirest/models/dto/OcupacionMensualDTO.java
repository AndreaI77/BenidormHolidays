package com.andrea.springapirest.models.dto;

public class OcupacionMensualDTO {
	   private Integer mes;
	    private Double ocupacion;

	    public OcupacionMensualDTO(Integer mes, Double ocupacion) {
	        this.mes = mes;
	        this.ocupacion = ocupacion;
	    }

	    public Integer getMes() {
	        return mes;
	    }

	    public Double getOcupacion() {
	        return ocupacion;
	    }

}
