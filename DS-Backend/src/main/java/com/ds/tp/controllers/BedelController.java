package com.ds.tp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.tp.models.usuario.Bedel;
import com.ds.tp.services.BedelService;

@RestController
@RequestMapping("/bedel")

public class BedelController {
    @Autowired
    private final BedelService bedelService;

    public BedelController(BedelService bedelService){
        this.bedelService = bedelService;
    }

    @GetMapping
    public List<Bedel> getBedel(){
        return bedelService.getBedels();
    }

    @PostMapping("/registrar")
    public ResponseEntity<Object> registrarBedel(@RequestBody Bedel unBedel){
        return this.bedelService.postBedel(unBedel);
    }

}
