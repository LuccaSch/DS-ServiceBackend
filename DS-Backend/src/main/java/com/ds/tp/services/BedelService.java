package com.ds.tp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ds.tp.models.usuario.Bedel;
import com.ds.tp.repositories.BedelRepository;

@Service
public class BedelService {

    private final BedelRepository bedelRepository;

    public BedelService(BedelRepository bedelRepository) {
        this.bedelRepository = bedelRepository;
    }

    public List<Bedel> getBedels() {
        return this.bedelRepository.findAll();
    }

    public ResponseEntity<Object> postBedel(Bedel unBedel) {
        // Verificar si el usuario ya existe
        Optional<Bedel> resultadoBedel = bedelRepository.findByUsuario(unBedel.getUsuario());

        HashMap<String, Object> respuesta = new HashMap<>();

        if (resultadoBedel.isPresent()) {
            respuesta.put("mensaje", "El bedel ya está registrado: " + unBedel.getUsuario());
            respuesta.put("estado", false);
            System.out.println("Se intentó registrar un bedel ya creado: " + unBedel.getUsuario());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        }

        bedelRepository.save(unBedel);
        respuesta.put("mensaje", "El bedel se registró correctamente");
        respuesta.put("estado", true);
        System.out.println("El bedel " + unBedel.getUsuario() + " se creó correctamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}
