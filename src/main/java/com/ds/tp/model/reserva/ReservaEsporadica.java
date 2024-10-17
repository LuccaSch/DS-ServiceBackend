package com.ds.tp.model.reserva;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ds.tp.model.usuario.Bedel;

public class ReservaEsporadica extends Reserva{
    /*
    atributos heredados:

    long id;
    long idDocente;
    long idAsignatura;
    String nombreDocente;
    String nombreAsignatura;
    int cantAlumnos;
    Timestamp fechaRegistro;
    Bedel bedel;
    */

    //atriburos
    private List<DiaReservaEsporadica> diasReserva;

    //constructores

    public ReservaEsporadica(){
        diasReserva=new ArrayList<>();
    }
    public ReservaEsporadica(long id,long idDocente,long idAsignatura,String nombreDocente,String nombreAsignatura,int cantAlumnos,Timestamp fechaRegistro,Bedel bedel){
        //constructor sin agregar diasReserva
        this.id=id;
        this.idDocente=idDocente;
        this.idAsignatura=idAsignatura;
        this.nombreDocente=nombreDocente;
        this.nombreAsignatura=nombreAsignatura;
        this.cantAlumnos=cantAlumnos;
        this.fechaRegistroTimestamp=fechaRegistro;
        this.bedel=bedel;
        
        this.diasReserva=new ArrayList<>();
    }

    public ReservaEsporadica(long id,long idDocente,long idAsignatura,String nombreDocente,String nombreAsignatura,int cantAlumnos,Timestamp fechaRegistro,Bedel bedel,List<DiaReservaEsporadica> diasReserva){
        this.id=id;
        this.idDocente=idDocente;
        this.idAsignatura=idAsignatura;
        this.nombreDocente=nombreDocente;
        this.nombreAsignatura=nombreAsignatura;
        this.cantAlumnos=cantAlumnos;
        this.fechaRegistroTimestamp=fechaRegistro;
        this.bedel=bedel;
        
        this.diasReserva=diasReserva;
    }
    
    //getter/setter
    public List<DiaReservaEsporadica> getDiasReserva() {
        return diasReserva;
    }

    public void setDiasReserva(List<DiaReservaEsporadica> diasReservas) {
        this.diasReserva = diasReservas;
    }

    //funciones
    public void addDiaReserva(DiaReservaEsporadica diaReserva){
        this.diasReserva.add(diaReserva);
    }
}
