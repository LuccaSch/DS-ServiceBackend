package com.ds.tp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ds.tp.models.reserva.Reserva;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long>{

}
