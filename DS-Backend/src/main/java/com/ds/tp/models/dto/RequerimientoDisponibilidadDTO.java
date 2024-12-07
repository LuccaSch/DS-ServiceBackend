package com.ds.tp.models.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequerimientoDisponibilidadDTO {

    @JsonProperty("cantidadAlumnos")
    Integer cantidadAlumnos;

    @JsonProperty("tipoReserva")
    Boolean tipoReserva;

    @JsonProperty("tipoAula")
    Integer tipoAula;

    @JsonProperty("diasReserva")
    List<DiasReservaDTO> diasReserva;
}
