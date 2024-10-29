package com.ds.tp.models.reserva;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva_periodica")
public class ReservaPeriodica extends Reserva{
    /*
    atributos heredados:

    int id;
    int idDocente;
    int idAsignatura;
    String nombreDocente;
    String nombreAsignatura;
    int cantAlumnos;
    Timestamp fechaRegistro;
    Bedel bedel;
    */

    //atributos
    @ManyToOne  
    @JoinColumn(name = "id_periodo", nullable = false)
    private Periodo periodo;
    

    //getter/setter
    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

}
