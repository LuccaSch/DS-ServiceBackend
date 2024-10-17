package com.ds.tp.model.reserva;

import java.time.LocalDate;

public class Periodo {
    private long id;
    private String cuatrimestre; //crear el enum luego
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    //constructores
    public Periodo(){};

    public Periodo(long id, String cuatrimestre, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.cuatrimestre = cuatrimestre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    //getter/setter
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCuatrimestre() {
        return cuatrimestre;
    }
    public void setCuatrimestre(String cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
}
