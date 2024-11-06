package com.ds.tp.models.aula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "aula")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Aula {

    // Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int idAula;

    @Column(name = "maximo_alumnos",nullable=false)
    protected int maximoAlumnos;

    @Column(nullable=false)
    protected boolean estado;

    @Column
    protected String piso;

    @Column(name = "tipo_pizzarron")
    protected String tipoPizaarron;

    @Column(name = "aire_acondicionado")
    protected boolean aireAcondicionado;
    
    // Getters y Setters

    public int getIdAula() {
        return idAula;
    }
    
    public void setIdAula(int id) {
        this.idAula = id;
    }

    public int getMaximoAlumnos() {
        return maximoAlumnos;
    }

    public void setMaximoAlumnos(int maximoAlumnos) {
        this.maximoAlumnos = maximoAlumnos;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getTipoPizaarron() {
        return tipoPizaarron;
    }

    public void setTipoPizaarron(String tipoPizaarron) {
        this.tipoPizaarron = tipoPizaarron;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }
}