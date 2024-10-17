package com.ds.tp.model.usuario;

public class Bedel extends Usuario{
    /*
    atributos heredados:

    long id;
    String usuario;
    String nombre;
    String apellido;
    String contraseña;
    */

    //atributos
    private int turno;
    private boolean estado;

    //constructores

    public Bedel(){}

    public Bedel(long id,String usuario, String nombre,String apellido,String contrasenia,int turno, boolean estado) {
        this.id=id;
        this.usuario=usuario;
        this.nombre=nombre;
        this.apellido=apellido;
        this.contrasenia=contrasenia;
        this.turno = turno;
        this.estado = estado;
    }

    public Bedel(String usuario, String nombre,String apellido,String contrasenia,int turno, boolean estado) {
        this.usuario=usuario;
        this.nombre=nombre;
        this.apellido=apellido;
        this.contrasenia=contrasenia;
        this.turno = turno;
        this.estado = estado;
    }

    //getter/setter
    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
