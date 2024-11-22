package com.ds.tp.models.reserva;

import java.sql.Timestamp;

import com.ds.tp.models.usuario.Bedel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reserva")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Reserva {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name="id_docente")
    protected Long idDocente;
    @Column(name="id_asignatura")
    protected Long idAsignatura;
    @Column
    protected String nombreDocente;
    @Column
    protected String nombreAsignatura;
    @Column(name="cant_alumnos")
    protected Integer cantAlumnos;
    @Column(name="fecha_registro")
    protected Timestamp fechaRegistroTimestamp;

    @OneToOne
    @JoinColumn(name = "id_bedel", referencedColumnName = "id", nullable = false)
    protected Bedel bedel;

    //getter-setter
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
