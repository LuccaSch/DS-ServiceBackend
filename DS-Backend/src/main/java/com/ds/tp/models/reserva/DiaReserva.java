package com.ds.tp.models.reserva;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ds.tp.models.aula.Aula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="dia_reserva")
public class DiaReserva{
    //atributos
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="fecha_reserva")
    private LocalDate fechaReserva;

    @Column(name="hora_inicio")
    private LocalTime horaInicio;

    @Column
    private Integer duracion;
    
    @ManyToOne
    @JoinColumn(name = "id_aula",referencedColumnName="id",nullable=false)
    private Aula aula;


    //constructores

    //constructor: por defecto
    public DiaReserva(){}

    //constructor: todos los campos
    public DiaReserva(Aula aula, Integer duracion, LocalDate fechaReserva, LocalTime horaInicio) {
        this.aula = aula;
        this.duracion = duracion;
        this.fechaReserva = fechaReserva;
        this.horaInicio = horaInicio;
    }
    
    //getter/setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    public Integer getDuracion() {
        return duracion;
    }
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
    public Aula getAula() {
        return aula;
    }
    public void setAula(Aula aula) {
        this.aula = aula;
    }
}
