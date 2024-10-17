package com.ds.tp.model.reserva;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ds.tp.model.usuario.Bedel;

public class ReservaPeriodica extends Reserva{
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

    //atributos
    private Periodo periodo;
    private List<DiaReservaPeriodica> diasReserva;
    
    //constructores
    public ReservaPeriodica(){
        diasReserva=new ArrayList<>();
    }

    public ReservaPeriodica(long id,long idDocente,long idAsignatura,String nombreDocente,String nombreAsignatura,int cantAlumnos,Timestamp fechaRegistro,Bedel bedel,Periodo periodo) {
        //constructor sin agregar diaReserva
        this.id=id;
        this.idDocente=idDocente;
        this.idAsignatura=idAsignatura;
        this.nombreDocente=nombreDocente;
        this.nombreAsignatura=nombreAsignatura;
        this.cantAlumnos=cantAlumnos;
        this.fechaRegistroTimestamp=fechaRegistro;
        this.bedel=bedel;
        
        this.periodo = periodo;
        this.diasReserva=new ArrayList<>();
    }

    public ReservaPeriodica(long idDocente,long idAsignatura,String nombreDocente,String nombreAsignatura,int cantAlumnos,Timestamp fechaRegistro,Bedel bedel,Periodo periodo) {
        //constructor sin agregar diaReserva y sin id
        this.idDocente=idDocente;
        this.idAsignatura=idAsignatura;
        this.nombreDocente=nombreDocente;
        this.nombreAsignatura=nombreAsignatura;
        this.cantAlumnos=cantAlumnos;
        this.fechaRegistroTimestamp=fechaRegistro;
        this.bedel=bedel;
        
        this.periodo = periodo;
    }

    public ReservaPeriodica(long id,long idDocente,long idAsignatura,String nombreDocente,String nombreAsignatura,int cantAlumnos,Timestamp fechaRegistro,Bedel bedel,Periodo periodo,List<DiaReservaPeriodica> diasReserva) {
        this.id=id;
        this.idDocente=idDocente;
        this.idAsignatura=idAsignatura;
        this.nombreDocente=nombreDocente;
        this.nombreAsignatura=nombreAsignatura;
        this.cantAlumnos=cantAlumnos;
        this.fechaRegistroTimestamp=fechaRegistro;
        this.bedel=bedel;
        
        this.periodo = periodo;
        this.diasReserva=diasReserva;
    }

    //getter/setter
    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public List<DiaReservaPeriodica> getDiasReserva() {
        return diasReserva;
    }

    public void setDiasReserva(List<DiaReservaPeriodica> diasReserva) {
        this.diasReserva = diasReserva;
    }

    //funciones
    public void addDiaReserva(DiaReservaPeriodica diaReserva){
        this.diasReserva.add(diaReserva);
    }
}
