package com.ds.tp.model.aula;

public class AulaSinRecursos extends Aula{
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
    protected boolean ventiladores;
    protected String descripcion;

    //constructores
    public AulaSinRecursos(){}

    public AulaSinRecursos(long id,int maximoAlumnos,boolean estado, String piso, String tipoPizarron, boolean aireAcondicionado,boolean ventiladores,String descripcion){
        this.id=id;
        this.maximoAlumnos=maximoAlumnos;
        this.estado=estado;
        this.piso=piso;
        this.tipoPizaarron=tipoPizarron;
        this.aireAcondicionado=aireAcondicionado;
        
        this.ventiladores = ventiladores;
        this.descripcion=descripcion;
    }

    public AulaSinRecursos(int maximoAlumnos,boolean estado, String piso, String tipoPizarron, boolean aireAcondicionado,boolean ventiladores,String descripcion){
        this.maximoAlumnos=maximoAlumnos;
        this.estado=estado;
        this.piso=piso;
        this.tipoPizaarron=tipoPizarron;
        this.aireAcondicionado=aireAcondicionado;
        
        this.ventiladores = ventiladores;
        this.descripcion=descripcion;
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
