package com.ds.tp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ds.tp.models.dto.BedelDTO;
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

    public ResponseEntity<Object> postBedel(BedelDTO unBedelDTO) {
        HashMap<String, Object> respuesta = new HashMap<>();

        /* 
        //verificar datos bedel
        if(verificarDatos(unBedelDTO)){
            respuesta.put("mensaje", "Datos invalidos");
            respuesta.put("estado", false);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        }
        */
        
        Bedel unBedel = crearBedel(unBedelDTO);

        // Verificar si el usuario ya existe
        Optional<Bedel> resultadoBedel = bedelRepository.findByUsuario(unBedel.getUsuario());

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

    public boolean verificarDatos(BedelDTO unBedel){
        //definir las verificaciones y se debe crear la verificacion mokapeada por programa externo
        if(unBedel.getUsuario().isEmpty() 
        || unBedel.getNombre().isEmpty() 
        || unBedel.getApellido().isEmpty() 
        || unBedel.getContrasenia().isEmpty()
        || unBedel.getConfContrasenia().isEmpty() 
        || unBedel.getTurno()==0){
            System.out.print("Error existe un campo noNulleable nulo queriendo registrar" + unBedel.getUsuario());
            return false;
        }
        else if(!unBedel.getContrasenia().equals(unBedel.getConfContrasenia())){
        System.out.print("Intento de registrar un bedel con contraseñas distintas, usuario: " +unBedel.getUsuario());
        return false;
       }
       else if(this.validarFormatoContrasenia(unBedel.getContrasenia())){
        System.out.print("Formato de contrasenia erroneo, usuario: " +unBedel.getUsuario());
        return false;
       }
       return true;
    }

    public Bedel crearBedel(BedelDTO unBedelDTO){
        return new Bedel(unBedelDTO.getUsuario(), unBedelDTO.getNombre(), unBedelDTO.getApellido(), unBedelDTO.getContrasenia(),unBedelDTO.getTurno(),true);
    }

    public boolean validarFormatoContrasenia(String contrasenia){
        //definir la verificacion de contrasenia que se debe instanciar contra el gestor externo
        return false;
    }
}
