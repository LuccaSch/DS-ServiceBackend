package com.ds.tp.model.reserva;

import java.sql.Timestamp;

import com.ds.tp.model.usuario.Bedel;

public class Reserva {
    //constructores
    protected long id;
    protected long idDocente;
    protected long idAsignatura;
    protected String nombreDocente;
    protected String nombreAsignatura;
    protected int cantAlumnos;
    protected Timestamp fechaRegistroTimestamp;
    protected Bedel bedel;

    //getter-setter
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getIdDocente() {
        return idDocente;
    }
    public void setIdDocente(long idDocente) {
        this.idDocente = idDocente;
    }
    public long getIdAsignatura() {
        return idAsignatura;
    }
    public void setIdAsignatura(long idAsignatura) {
        this.idAsignatura = idAsignatura;
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
    public int getCantAlumnos() {
        return cantAlumnos;
    }
    public void setCantAlumnos(int cantAlumnos) {
        this.cantAlumnos = cantAlumnos;
    }
    public Timestamp getFechaRegistro() {
        return fechaRegistroTimestamp;
    }
    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistroTimestamp = fechaRegistro;
    }
    public Bedel getBedel() {
        return bedel;
    }
    public void setBedel(Bedel bedel) {
        this.bedel = bedel;
    }
}
