package com.ds.tp.models.aula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="aula_sin_recursos")
public class AulaSinRecursos extends Aula{

    /*
    atributos heredados:

    Long id;
    int maximoAlumnos;
    boolean estado;
    String piso;
    String tipoPizaarron;
    boolean aireAcondicionado;
    */

    //atributos
    @Column
    protected boolean ventiladores;
    
    @Column
    protected String descripcion;

    //constructor

    //constructor: por defecto
    public AulaSinRecursos(){}

    //constructor: todos los campos
    public AulaSinRecursos(int maximoAlumnos, boolean estado, String piso, String tipoPizaarron, boolean aireAcondicionado,boolean ventiladores,String descripcion){
        this.maximoAlumnos = maximoAlumnos;
        this.estado = estado;
        this.piso = piso;
        this.tipoPizaarron = tipoPizaarron;
        this.aireAcondicionado = aireAcondicionado;
        this.ventiladores=ventiladores;
        this.descripcion=descripcion;    
    }

    //constructor: Solo los campos que no pueden ser null
    public AulaSinRecursos(int maximoAlumnos, boolean estado){
        this.maximoAlumnos = maximoAlumnos;
        this.estado = estado;
    }

    //getter/setter
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
