package com.ds.tp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.ds.tp.models.dto.BedelDTO;
import com.ds.tp.models.usuario.Bedel;
import com.ds.tp.repositories.BedelRepository;

@Service
public class BedelService {
    //atributos inyectados por spring
    @Autowired
    private final BedelRepository bedelRepository;

    @Autowired
    private final EmpresaService empresaService;

     

    //Constructor

   
    public BedelService(BedelRepository bedelRepository, EmpresaService empresaService) {
        this.bedelRepository = bedelRepository;
        this.empresaService = empresaService;
     
    }
    
        
                //Funciones del servicio BEDEL
                public List<Bedel> getBedels() {
                    return this.bedelRepository.findAll();
                }
            
                public ResponseEntity<Object> postBedel(BedelDTO unBedelDTO) {
                    HashMap<String, Object> respuesta = new HashMap<>();
            
                    Optional<String> resultadoValidacion = verificarDatos(unBedelDTO);
            
                    //Se divide la verificacion ya que dependiendo para retornar BAD_REQUEST o CONFLIT al cliente
            
                    //segunda verificacion de datos del bedel
                    if(resultadoValidacion.isPresent()){
                        respuesta.put("mensaje", resultadoValidacion.get());
                        respuesta.put("estado", false);
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
                    }
                    
                    //verificar mediante el programa externo el formato de contraseña
                    if(!validarFormatoContrasenia(unBedelDTO.getContrasenia())){
                        respuesta.put("mensaje", "ERROR: Formato de contraseña invalido");
                        respuesta.put("estado", false);
                        return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
                    }
            
                    // Verificar si el usuario ya existe
                    Optional<Bedel> resultadoBedel = bedelRepository.findByUsuario(unBedelDTO.getUsuario());
            
                    if (resultadoBedel.isPresent()) {
                        respuesta.put("mensaje", "El bedel ya está registrado: " + unBedelDTO.getUsuario());
                        respuesta.put("estado", false);
                        System.out.println("Se intentó registrar un bedel ya creado: " + unBedelDTO.getUsuario());
                        return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
                    }
                            
                    Bedel unBedel = crearBedel(unBedelDTO);
            
                    //Se accede al repositorio de JPA para instanciar el bedel ya verificado
                    try{
                       
                        bedelRepository.save(unBedel);
                        respuesta.put("mensaje", "El bedel se registró correctamente");
                        respuesta.put("estado", true);
                        System.out.println("[INFO] Se registro: "+ unBedel.toString());
                        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
                    }
                    catch (DataAccessException e) {
                        System.out.println("Error de acceso a datos: " + e.getMessage());
                        respuesta.put("mensaje", "Internal Server Error" + e.getMessage());
                        respuesta.put("estado", false);
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
                    }
                }
            
                public Optional<String> verificarDatos(BedelDTO unBedel){
                    //definimos el opcional como vacio
                    Optional<String> resultadoVerificado = Optional.empty();
            
                    //si alguna de las verificaciones falla el opcional llevara el string de error
                    if(unBedel.getUsuario().isEmpty() 
                    || unBedel.getNombre().isEmpty() 
                    || unBedel.getApellido().isEmpty() 
                    || unBedel.getContrasenia().isEmpty()
                    || unBedel.getConfContrasenia().isEmpty() 
                    || unBedel.getTurno()==0){
                        resultadoVerificado= Optional.of("ERROR: Se quiere registrar un campo requerido vacio");
                    }
                    else if(!unBedel.getContrasenia().equals(unBedel.getConfContrasenia())){
                        resultadoVerificado= Optional.of("ERROR: Se quiere registrar un bedel con contraseñas y confirmacion de contraseña no identicas");
                   }
                   if(unBedel.getUsuario().length()>30 ||  unBedel.getUsuario().length()<5
                   || unBedel.getNombre().length()>40 
                   || unBedel.getApellido().length()>40  
                   || unBedel.getContrasenia().length()>40 
                   || unBedel.getConfContrasenia().length()>40){
                        resultadoVerificado= Optional.of("ERROR: Se quiere registrar un campo con una cantidad de de digitos incorrecta");
                   }
            
                   return resultadoVerificado;
                }
            
                public Bedel crearBedel(BedelDTO unBedelDTO){
                    return new Bedel(unBedelDTO.getUsuario(), unBedelDTO.getNombre(), unBedelDTO.getApellido(), unBedelDTO.getContrasenia(),unBedelDTO.getTurno(),true);
                }
            
                public boolean validarFormatoContrasenia(String contrasenia){
                    return empresaService.validarRequerimientoContrasenia(contrasenia);
                }
            
            

    
}
