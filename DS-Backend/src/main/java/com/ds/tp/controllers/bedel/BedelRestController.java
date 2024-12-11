package com.ds.tp.controllers.bedel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.tp.models.dto.RequerimientoDisponibilidadDTO;
import com.ds.tp.services.AulaService;


@RestController
@RequestMapping("/bedel/api")

public class BedelRestController {

    //Atributos
    @Autowired
    AulaService aulaService;

    //Constructor
    public BedelRestController(AulaService aulaService){
        this.aulaService=aulaService;
    }

    //Metodos
    
    /*
    @PostMapping("/getAula/periodica")
    public ResponseEntity<Object> buscarAulasPeriodica(@RequestBody RequerimientoDisponibilidadDTO requisito) {
        return aulaService.buscarAulaPeriodica(requisito); 
    }
    */

    @PostMapping("/getAula/esporadica")
    public ResponseEntity<Object> buscarAulasEsporadica(@RequestBody RequerimientoDisponibilidadDTO requisito) {
        return aulaService.getAulaEsporadica(requisito); 
    }
    
}
