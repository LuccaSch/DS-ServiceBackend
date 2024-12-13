package com.ds.tp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ds.tp.models.reserva.Periodo;
import com.ds.tp.models.reserva.Reserva;



@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long>{

    @SuppressWarnings("null")
    @Override
    public Optional<Reserva> findById(Long id);

    @Query("SELECT p FROM Periodo p WHERE p.id = :periodoId")
    Optional<Periodo> findPeriodoById(@Param("periodoId") Long periodoId);
}
