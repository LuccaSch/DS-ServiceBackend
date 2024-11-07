package com.ds.tp.models.reserva;

import java.sql.Timestamp;

import com.ds.tp.models.usuario.Bedel;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva_esporadica")
public class ReservaEsporadica extends Reserva{
    /*
    atributos heredados:

    long id;
    long idDocente;
    long idAsignatura;
    String nombreDocente;
    String nombreAsignatura;
    int cantAlumnos;
    Timestamp fechaRegistro;
    Bedel bedel;
    */

    //constructores

    //constructor: por defecto
    public ReservaEsporadica(){}

    //constructor: todos los campos
    public ReservaEsporadica(Bedel bedel, int cantAlumnos, Timestamp fechaRegistroTimestamp, int idAsignatura, int idDocente, String nombreAsignatura, String nombreDocente) {
        this.bedel = bedel;
        this.cantAlumnos = cantAlumnos;
        this.fechaRegistroTimestamp = fechaRegistroTimestamp;
        this.idAsignatura = idAsignatura;
        this.idDocente = idDocente;
        this.nombreAsignatura = nombreAsignatura;
        this.nombreDocente = nombreDocente;
    }

    //constructor: Solo los campos que no pueden ser null
    public ReservaEsporadica(Bedel bedel, int cantAlumnos, Timestamp fechaRegistroTimestamp, int idAsignatura, int idDocente) {
        this.bedel = bedel;
        this.cantAlumnos = cantAlumnos;
        this.fechaRegistroTimestamp = fechaRegistroTimestamp;
        this.idAsignatura = idAsignatura;
        this.idDocente = idDocente;
    }
}