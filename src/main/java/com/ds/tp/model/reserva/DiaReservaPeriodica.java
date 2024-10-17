package com.ds.tp.model.reserva;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ds.tp.model.aula.Aula;


public class DiaReservaPeriodica{
    private long id;
    private LocalDate fechaReserva;
    private LocalTime horaInicio;
    private int duracion;
    private Aula aula;

    //constructor
    public DiaReservaPeriodica(){}

    public DiaReservaPeriodica(long id, LocalDate fechaReserva, LocalTime horaInicio, int duracion, Aula aula) {
        this.id = id;
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.duracion = duracion;
        this.aula = aula;
    }

    public DiaReservaPeriodica(LocalDate fechaReserva, LocalTime horaInicio, int duracion, Aula aula) {
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
        this.duracion = duracion;
        this.aula = aula;
    }
    
    //getter/setter
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }
    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }
    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    public Aula getAula() {
        return aula;
    }
    public void setAula(Aula aula) {
        this.aula = aula;
    }
}
