package com.ds.tp.model.aula;

public class AulaInformatica extends Aula{
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
    private int cantPc;
    private boolean canion;

    //constructores

    public AulaInformatica(){}

    public AulaInformatica(long id,int maximoAlumnos,boolean estado, String piso, String tipoPizarron, boolean aireAcondicionado,int cantPc, boolean canion){
        this.id=id;
        this.maximoAlumnos=maximoAlumnos;
        this.estado=estado;
        this.piso=piso;
        this.tipoPizaarron=tipoPizarron;
        this.aireAcondicionado=aireAcondicionado;

        this.cantPc=cantPc;
        this.canion=canion;
    }

    public AulaInformatica(int maximoAlumnos,boolean estado, String piso, String tipoPizarron, boolean aireAcondicionado,int cantPc, boolean canion){
        this.maximoAlumnos=maximoAlumnos;
        this.estado=estado;
        this.piso=piso;
        this.tipoPizaarron=tipoPizarron;
        this.aireAcondicionado=aireAcondicionado;

        this.cantPc=cantPc;
        this.canion=canion;
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
