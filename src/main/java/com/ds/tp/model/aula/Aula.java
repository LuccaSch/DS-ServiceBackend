package com.ds.tp.model.aula;

public abstract class Aula {
    //atributos
    protected long id;
    protected  int maximoAlumnos;
    protected boolean estado;
    protected String piso;
    protected String tipoPizaarron;
    protected boolean aireAcondicionado;

    //getter/setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMaximoAlumnos() {
        return maximoAlumnos;
    }

    public void setMaximoAlumnos(int maximoAlumnos) {
        this.maximoAlumnos = maximoAlumnos;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getTipoPizaarron() {
        return tipoPizaarron;
    }

    public void setTipoPizaarron(String tipoPizaarron) {
        this.tipoPizaarron = tipoPizaarron;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }
}
