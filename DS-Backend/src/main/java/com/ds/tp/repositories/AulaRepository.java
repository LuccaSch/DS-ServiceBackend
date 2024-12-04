package com.ds.tp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ds.tp.models.aula.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula,Long>{

}
