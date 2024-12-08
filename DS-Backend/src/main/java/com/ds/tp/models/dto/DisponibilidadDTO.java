package com.ds.tp.models.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;



public class DisponibilidadDTO {

    @JsonProperty("reserva")
    private ReservaDTO reserva;

    @JsonProperty("Aulas")
    private List<AulaDTO> listaAulas;

    //Constructor
    public DisponibilidadDTO(List<AulaDTO> listaAulas, ReservaDTO reserva) {
        this.listaAulas = listaAulas;
        this.reserva = reserva;
    }

    public DisponibilidadDTO(List<AulaDTO> listaAulas) {
        this.listaAulas = listaAulas;
    }

    public DisponibilidadDTO(ReservaDTO reserva) {
        this.reserva=reserva;
    }

    public DisponibilidadDTO(){}

    //GETTER/SETTER

    public ReservaDTO getReserva() {
        return reserva;
    }

    public void setReserva(ReservaDTO reserva) {
        this.reserva = reserva;
    }

    public List<AulaDTO> getListaAulas() {
        return listaAulas;
    }

    public void setListaAulas(List<AulaDTO> listaAulas) {
        this.listaAulas = listaAulas;
    }
}
