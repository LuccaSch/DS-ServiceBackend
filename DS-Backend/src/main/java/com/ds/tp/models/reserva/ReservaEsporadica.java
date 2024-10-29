package com.ds.tp.models.reserva;

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
}
