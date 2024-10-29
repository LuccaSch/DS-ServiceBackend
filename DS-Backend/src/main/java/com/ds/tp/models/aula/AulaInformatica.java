package com.ds.tp.models.aula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "aula_informatica")
@PrimaryKeyJoinColumn(name = "aula_id")  
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

    @Column(name="cant_pc")
    private int cantPc;

    @Column
    private boolean canion;  

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
