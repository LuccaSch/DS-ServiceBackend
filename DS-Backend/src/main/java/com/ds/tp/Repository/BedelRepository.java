package com.ds.tp.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ds.tp.models.usuario.Bedel;

@Repository
public interface BedelRepository extends JpaRepository<Bedel,Long> {
    
    Optional<Bedel> findByid(Long id);
}
