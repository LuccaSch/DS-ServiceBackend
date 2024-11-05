package com.ds.tp.models.aula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "aula_multimedia")
@PrimaryKeyJoinColumn(name = "id") 
public class AulaMultimedia extends Aula{
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
    private boolean televisor;

    @Column
    private boolean canion;

    @Column
    private boolean computadora;

    @Column
    private boolean ventiladores;

    //constructores

    //constructor: por defecto
    public AulaMultimedia(){}

    //constructor: todos los campos
    public AulaMultimedia(int maximoAlumnos, boolean estado, String piso, String tipoPizaarron, boolean aireAcondicionado,boolean televisor,boolean canion,boolean computadora, boolean ventiladores){
        this.maximoAlumnos = maximoAlumnos;
        this.estado = estado;
        this.piso = piso;
        this.tipoPizarron = tipoPizaarron;
        this.aireAcondicionado = aireAcondicionado;
        this.televisor=televisor;
        this.canion=canion;
        this.computadora=computadora;
        this.ventiladores=ventiladores;
    }

    //constructor: Solo los campos que no pueden ser null
    public AulaMultimedia(int maximoAlumnos, boolean estado){
        this.maximoAlumnos = maximoAlumnos;
        this.estado = estado;
    }

    public boolean isTelevisor() {
        return televisor;
    }
    public void setTelevisor(boolean televisor) {
        this.televisor = televisor;
    }

    public boolean isCanion() {
        return canion;
    }
    public void setCanion(boolean canion) {
        this.canion = canion;
    }

    public boolean isComputadora() {
        return computadora;
    }
    public void setComputadora(boolean computadora) {
        this.computadora = computadora;
    }

    public boolean isVentiladores() {
        return ventiladores;
    }
    public void setVentiladores(boolean ventiladores) {
        this.ventiladores = ventiladores;
    }
}
