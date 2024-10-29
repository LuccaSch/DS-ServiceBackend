package com.ds.tp.models.aula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="aula_sin_recursos")
public class AulaSinRecursos extends Aula{

    /*
    atributos heredados:

    int id;
    int maximoAlumnos;
    boolean estado;
    String piso;
    String tipoPizaarron;
    boolean aireAcondicionado;
    */

    //atributos
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idSr;

    @Column
    protected boolean ventiladores;
    
    @Column
    protected String descripcion;

    //getter/setter
    public int getIdSr() {
        return idSr;
    }
    public void setId_sr(int id_sr) {
        this.idSr = id_sr;
    }

    public boolean isVentiladores() {
        return ventiladores;
    }
    public void setVentiladores(boolean ventiladores) {
        this.ventiladores = ventiladores;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
