package com.ds.tp.models.dto;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservaDTO{

    @JsonProperty("id")
    private Long id;

    @JsonProperty("idDocente")
    private Long idDocente;

    @JsonProperty("idAsignatura")
    private Long idAsignatura;

    @JsonProperty("usuarioBedel")
    private String usuarioBedel;
    
    @JsonProperty("nombreDocente")
    private String nombreDocente;
    
    @JsonProperty("nombreAsignatura")
    private String nombreAsignatura;
    
    @JsonProperty("cantAlumnos")
    private Integer cantAlumnos;

    @JsonProperty("fechaRegistro")
    private Timestamp fechaRegistroTimestamp;

    @JsonProperty("diaReservaDTO")
    private List<DiaReservaDTO> listaDiasReservaDTO;

    @JsonProperty("tipo")
    // 0 periodica, 1 esporadica
    private int tipo;

    @JsonProperty("periodo")
    //0 Anual, 1 primerCuatrimestr, 2 SegundoCuatrimestre 
    private int periodo;


    public ReservaDTO(Long id,Integer cantAlumnos, Timestamp fechaRegistroTimestamp, Long idAsignatura, String idBedel, Long idDocente, String nombreAsignatura, String nombreDocente,int tipo, List<DiaReservaDTO> listaDiasReservaDTO) {
        this.cantAlumnos = cantAlumnos;
        this.fechaRegistroTimestamp = fechaRegistroTimestamp;
        this.id = id;
        this.idAsignatura = idAsignatura;
        this.usuarioBedel = idBedel;
        this.idDocente = idDocente;
        this.nombreAsignatura = nombreAsignatura;
        this.nombreDocente = nombreDocente;
        this.listaDiasReservaDTO=listaDiasReservaDTO;
        this.tipo=tipo;
    }

    public ReservaDTO(Long id, Long idAsignatura, String idBedel, Long idDocente, String nombreAsignatura, String nombreDocente) {
        this.id = id;
        this.idAsignatura = idAsignatura;
        this.usuarioBedel = idBedel;
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

    public String getIdBedel() {
        return usuarioBedel;
    }

    public void setIdBedel(String idBedel) {
        this.usuarioBedel = idBedel;
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

    public List<DiaReservaDTO> getListaDiasReservaDTO() {
        return listaDiasReservaDTO;
    }

    public void setListaDiasReservaDTO(List<DiaReservaDTO> listaDiasReservaDTO) {
        this.listaDiasReservaDTO = listaDiasReservaDTO;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}