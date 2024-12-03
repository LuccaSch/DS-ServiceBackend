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
    protected Long id;

    @Column(name = "maximo_alumnos",nullable=false)
    protected Integer maximoAlumnos;

    @Column(nullable=false)
    protected Boolean estado;

    @Column
    protected String piso;

    @Column(name = "tipo_pizzarron")
    protected String tipoPizarron;

    @Column(name = "aire_acondicionado")
    protected Boolean aireAcondicionado;
    
    // Getters y Setters

    public Long getIdAula() {
        return id;
    }
    
    public void setIdAula(Long id) {
        this.id = id;
    }

    public Integer getMaximoAlumnos() {
        return maximoAlumnos;
    }

    public void setMaximoAlumnos(Integer maximoAlumnos) {
        this.maximoAlumnos = maximoAlumnos;
    }

    public Boolean isEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getTipoPizarron() {
        return tipoPizarron;
    }

    public void setTipoPizarron(String tipoPizaarron) {
        this.tipoPizarron = tipoPizaarron;
    }

    public Boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(Boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }
}