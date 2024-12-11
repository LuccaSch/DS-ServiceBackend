package com.ds.tp.models.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RequerimientoDisponibilidadDTO {

    @JsonProperty("cantidadAlumnos")
    private Integer cantidadAlumnos;

    @JsonProperty("tipoReserva")
    private Boolean tipoReserva;

    // 0 Informatica, 1 Multimedia, 2 SinRecurso
    @JsonProperty("tipoAula")
    int tipoAula;

    @JsonProperty("diasReserva")
    List<DiaReservaDTO> diasReserva;

    public RequerimientoDisponibilidadDTO(Integer cantidadAlumnos, List<DiaReservaDTO> diasReserva, int tipoAula, Boolean tipoReserva) {
        this.cantidadAlumnos = cantidadAlumnos;
        this.diasReserva = diasReserva;
        this.tipoAula = tipoAula;
        this.tipoReserva = tipoReserva;
    }

    public Integer getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(Integer cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }

    public Boolean getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(Boolean tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    public int getTipoAula() {
        return tipoAula;
    }

    public void setTipoAula(int tipoAula) {
        this.tipoAula = tipoAula;
    }

    public List<DiaReservaDTO> getDiasReserva() {
        return diasReserva;
    }

    public void setDiasReserva(List<DiaReservaDTO> diasReserva) {
        this.diasReserva = diasReserva;
    }
}
