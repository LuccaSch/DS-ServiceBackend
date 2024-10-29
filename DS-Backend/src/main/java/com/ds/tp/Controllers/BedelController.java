package com.ds.tp.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ds.tp.Service.BedelService;
import com.ds.tp.models.usuario.Bedel;

@RestController
//@RequestMapping(path = )

public class BedelController {
    
    private final BedelService bedelService;


    @Autowired
    public BedelController(BedelService bedelService){
        this.bedelService = bedelService;
    }

    @GetMapping
    public List<Bedel> getBedel(){
        return bedelService.getBedels();
    }

    @PostMapping
    public ResponseEntity<Object> registrarBedel(@RequestBody Bedel unBedel){
        return this.bedelService.newBedel(unBedel);
    }


}
