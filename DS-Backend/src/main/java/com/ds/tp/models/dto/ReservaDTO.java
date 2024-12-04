package com.ds.tp.models.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaDTO{

    @JsonProperty("id")
    protected Long id;

    @JsonProperty("idDocente")
    protected Long idDocente;

    @JsonProperty("idAsignatura")
    protected Long idAsignatura;

    @JsonProperty("idBedel")
    protected Long idBedel;
    
    @JsonProperty("nombreDocente")
    protected String nombreDocente;
    
    @JsonProperty("nombreAsignatura")
    protected String nombreAsignatura;
    
    @JsonProperty("cantAlumnos")
    protected Integer cantAlumnos;

    @JsonProperty("fechaRegistro")
    protected Timestamp fechaRegistroTimestamp;
}