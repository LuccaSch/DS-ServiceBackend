package com.ds.tp.model.reserva;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiaReservaEsporadica{
    private long id;
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private int duracion;

    //constructores
    public DiaReservaEsporadica(){}

    public DiaReservaEsporadica(long id, DayOfWeek diaSemana, LocalTime horaInicio, int duracion) {
        this.id = id;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.duracion = duracion;
    }

    public DiaReservaEsporadica(DayOfWeek diaSemana, LocalTime horaInicio, int duracion) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.duracion = duracion;
    }
    
    //getter/setter
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }
    public void setDiaSemana(DayOfWeek diaSemana) {
        this.diaSemana = diaSemana;
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
}
