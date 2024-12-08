package com.ds.tp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ds.tp.models.aula.Aula;
import com.ds.tp.models.dto.DisponibilidadDTO;
import com.ds.tp.models.dto.RequerimientoDisponibilidadDTO;
import com.ds.tp.repositories.AulaRepository;
import com.ds.tp.util.DSUtilResponseEntity;

@Service
public class AulaService {

    // Atributos inyectados por Spring 
    @Autowired
    AulaRepository aulaRepository;

    private static final int INFORMATICA = 0;
    private static final int MULTIMEDIA = 1;
    private static final int SINRECURSOS = 2;

    //Constructor
    public AulaService(AulaRepository aulaRepository){
        this.aulaRepository=aulaRepository;
    }

    // FUNCIONES DEL SERVICIO AULA

    //--------------------------------------------------GET AULAS--------------------------------------------------

    public ResponseEntity<Object> getAulaEsporadica(RequerimientoDisponibilidadDTO requisito) {
        Optional<? extends List<? extends Aula>> aulasOpt;

        switch (requisito.getTipoAula()) {
            case INFORMATICA -> aulasOpt = aulaRepository.findAulasInformaticaByMaximoAlumnos(requisito.getCantidadAlumnos());
            case MULTIMEDIA -> aulasOpt = aulaRepository.findAulasMultimediaByMaximoAlumnos(requisito.getCantidadAlumnos());
            case SINRECURSOS -> aulasOpt = aulaRepository.findAulasSinRecursosByMaximoAlumnos(requisito.getCantidadAlumnos());
            default -> {
                        return DSUtilResponseEntity.statusBadRequest("Tipo de aula invalido válido: Seleccione entre Informatica, Multimedia Y Sin Recursos");
                    }
        }

        if (aulasOpt.isEmpty()) {
            return DSUtilResponseEntity.statusBadRequest("ERROR: No existe aula que cumpla con las características solicitadas");
        }

        // Hacemos una conversion explicita de tipos ya que solo nesesitamos los datos de la clase generica AULA.
        List<Aula> listaAulas = new ArrayList<>(aulasOpt.get());

        DisponibilidadDTO disponibilidadDTO= new DisponibilidadDTO();



        return ResponseEntity.ok(listaAulas);
    }


    
}
