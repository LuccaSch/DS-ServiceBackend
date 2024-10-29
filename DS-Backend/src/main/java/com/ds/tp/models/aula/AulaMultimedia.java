package com.ds.tp.models.aula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "aula_multimedia")
@PrimaryKeyJoinColumn(name = "id_aula") 
public class AulaMultimedia extends Aula{
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

    @Column
    private boolean televisor;

    @Column
    private boolean canion;

    @Column
    private boolean computadora;

    @Column
    private boolean ventiladores;

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
