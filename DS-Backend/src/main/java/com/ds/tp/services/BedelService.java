package com.ds.tp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ds.tp.repositories.BedelRepository;
import com.ds.tp.models.usuario.Bedel;

@Service
public class BedelService  {

    
    private BedelRepository bedelRepository;

    @Autowired
    public BedelService(BedelRepository bedelRepository){
        this.bedelRepository = bedelRepository;
    }

    public List<Bedel> getBedels(){
        return this.bedelRepository.findAll();
    }

    public ResponseEntity<Object> newBedel(Bedel unBedel) {
        //verificamos si el usuario ya esta creado
       Optional<Bedel> res= bedelRepository.findByid(unBedel.getId());

        HashMap<String,Object> datos = new HashMap<>();

       if(res.isPresent()){
        datos.put("Error",true);
        return new ResponseEntity<>(
            datos,
            HttpStatus.CONFLICT
            );
       }
       bedelRepository.save(unBedel);
       datos.put("El bedel se registro correctamente",true);
       return new ResponseEntity<>(
            datos,
            HttpStatus.CREATED
            );

    }










}
