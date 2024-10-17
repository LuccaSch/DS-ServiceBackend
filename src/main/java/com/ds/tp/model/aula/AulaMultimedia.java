package com.ds.tp.model.aula;

public class AulaMultimedia extends Aula{
    /*
    atributos heredados:

    long id;
    int maximoAlumnos;
    boolean estado;
    String piso;
    String tipoPizaarron;
    boolean aireAcondicionado;
    */

    //atributos

    private boolean televisor;
    private boolean canion;
    private boolean computadora;
    private boolean ventiladores;

    //constructores 
    public AulaMultimedia(){}

    public AulaMultimedia(long id,int maximoAlumnos,boolean estado, String piso, String tipoPizarron, boolean aireAcondicionado,boolean televisor, boolean canion, boolean computadora, boolean ventiladores) {
        this.id=id;
        this.maximoAlumnos=maximoAlumnos;
        this.estado=estado;
        this.piso=piso;
        this.tipoPizaarron=tipoPizarron;
        this.aireAcondicionado=aireAcondicionado;
        
        this.televisor = televisor;
        this.canion = canion;
        this.computadora = computadora;
        this.ventiladores = ventiladores;
    }

    public AulaMultimedia(int maximoAlumnos,boolean estado, String piso, String tipoPizarron, boolean aireAcondicionado,boolean televisor, boolean canion, boolean computadora, boolean ventiladores) {
        this.maximoAlumnos=maximoAlumnos;
        this.estado=estado;
        this.piso=piso;
        this.tipoPizaarron=tipoPizarron;
        this.aireAcondicionado=aireAcondicionado;
        
        this.televisor = televisor;
        this.canion = canion;
        this.computadora = computadora;
        this.ventiladores = ventiladores;
    }

    
    //getter/setter
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
