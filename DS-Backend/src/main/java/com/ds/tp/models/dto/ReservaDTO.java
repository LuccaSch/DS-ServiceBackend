package com.ds.tp.models.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservaDTO{

    @JsonProperty("id")
    private Long id;

    @JsonProperty("idDocente")
    private Long idDocente;

    @JsonProperty("idAsignatura")
    private Long idAsignatura;

    @JsonProperty("idBedel")
    private Long idBedel;
    
    @JsonProperty("nombreDocente")
    private String nombreDocente;
    
    @JsonProperty("nombreAsignatura")
    private String nombreAsignatura;
    
    @JsonProperty("cantAlumnos")
    private Integer cantAlumnos;

    @JsonProperty("fechaRegistro")
    private Timestamp fechaRegistroTimestamp;

    public ReservaDTO(Long id,Integer cantAlumnos, Timestamp fechaRegistroTimestamp, Long idAsignatura, Long idBedel, Long idDocente, String nombreAsignatura, String nombreDocente) {
        this.cantAlumnos = cantAlumnos;
        this.fechaRegistroTimestamp = fechaRegistroTimestamp;
        this.id = id;
        this.idAsignatura = idAsignatura;
        this.idBedel = idBedel;
        this.idDocente = idDocente;
        this.nombreAsignatura = nombreAsignatura;
        this.nombreDocente = nombreDocente;
    }

    public ReservaDTO(Long id, Long idAsignatura, Long idBedel, Long idDocente, String nombreAsignatura, String nombreDocente) {
        this.id = id;
        this.idAsignatura = idAsignatura;
        this.idBedel = idBedel;
        this.idDocente = idDocente;
        this.nombreAsignatura = nombreAsignatura;
        this.nombreDocente = nombreDocente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Long idDocente) {
        this.idDocente = idDocente;
    }

    public Long getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Long getIdBedel() {
        return idBedel;
    }

    public void setIdBedel(Long idBedel) {
        this.idBedel = idBedel;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public Integer getCantAlumnos() {
        return cantAlumnos;
    }

    public void setCantAlumnos(Integer cantAlumnos) {
        this.cantAlumnos = cantAlumnos;
    }

    public Timestamp getFechaRegistroTimestamp() {
        return fechaRegistroTimestamp;
    }

    public void setFechaRegistroTimestamp(Timestamp fechaRegistroTimestamp) {
        this.fechaRegistroTimestamp = fechaRegistroTimestamp;
    }
}