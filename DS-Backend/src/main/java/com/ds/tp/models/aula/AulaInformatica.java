package com.ds.tp.models.aula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "aula_informatica")
@PrimaryKeyJoinColumn(name = "id")  
public class AulaInformatica extends Aula{
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

    @Column(name="cant_pc", nullable=false)
    private int cantPc;

    @Column
    private boolean canion; 

    //constructores

    //constructor: por defecto
    public AulaInformatica(){}

    //constructor: todos los campos
    public AulaInformatica(int maximoAlumnos, boolean estado, String piso, String tipoPizaarron, boolean aireAcondicionado,int cantPc,boolean canion){
        this.maximoAlumnos = maximoAlumnos;
        this.estado = estado;
        this.piso = piso;
        this.tipoPizarron = tipoPizaarron;
        this.aireAcondicionado = aireAcondicionado;
        this.cantPc=cantPc;
        this.canion=canion;
    }

    //constructor: Solo los campos que no pueden ser null
    public AulaInformatica(int maximoAlumnos, boolean estado){
        this.maximoAlumnos = maximoAlumnos;
        this.estado = estado;
    }


    //getter/setter
    public int getCantPc() {
        return cantPc;
    }
    public void setCantPc(int cantPc) {
        this.cantPc = cantPc;
    }

    public boolean isCanion() {
        return canion;
    }
    public void setCanion(boolean canion) {
        this.canion = canion;
    }
}
