package com.ds.tp.models.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class DiasReservaDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("fechaReserva")
    private LocalDate fechaReserva;

    @JsonProperty("horaInicio")
    private LocalTime horaInicio;

    @JsonProperty("duracion")
    private Integer duracion;
    
    @JsonProperty("idAula")
    private Long idAula;
}
