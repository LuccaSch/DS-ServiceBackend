package com.ds.tp.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.tp.models.reserva.DiaReserva;

public interface DiaReservaRepository extends  JpaRepository<DiaReserva,Long>{
    // Busca todas las reservas para una fecha específica
    List<DiaReserva> findByFechaReserva(LocalDate fechaReserva);
}
