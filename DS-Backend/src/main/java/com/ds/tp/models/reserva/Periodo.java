package com.ds.tp.models.reserva;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="periodo")
public class Periodo {
    //atributos
    @Id
    private int id;

    @Column
    private String cuatrimestre; //crear el enum ¿?

    @Column
    private LocalDate fechaInicio;

    @Column
    private LocalDate fechaFin;

    //getter/setter
    public long getId() {
        return id;
    }
    public void setId(int id) {
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
