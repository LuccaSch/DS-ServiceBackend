package com.ds.tp.models.usuario;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_bedel")
public class Bedel extends Usuario{

    /* 
    atributos heredados:
    
    Long id;
    String usuario;
    String nombre;
    String apellido;
    String contrasenia;

    */

    //atributos

    @AttributeOverride(name = "id", column = @Column(name = "id_bedel"))//mapea el id del padre con el nombre correcto id_bedel

    @Column
    private int turno;
    @Column
    private boolean estado;

    //constructor

    //constructor: por defecto
    public Bedel(){}

    //constructor: todos los campos
    public Bedel(String usuario, String nombre, String apellido, String contrasenia,int turno,boolean estado){
        this.usuario=usuario;
        this.nombre=nombre;
        this.contrasenia=contrasenia;
        this.apellido=apellido;
        this.turno=turno;
        this.estado=estado;
    }

    //constructor: Solo los campos que no pueden ser null
    public Bedel(String usuario,String contrasenia,int turno,boolean estado){
        this.usuario=usuario;
        this.contrasenia=contrasenia;
        this.turno=turno;
        this.estado=estado;
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

    //otros metodos

    @Override
    public String toString(){
        return "BEDEL {Id: " + id +
                    ", Usuario: "+usuario+
                    ", Nombre: " + nombre + 
                    ", Apellido "+apellido+
                    "}";
    }
}
