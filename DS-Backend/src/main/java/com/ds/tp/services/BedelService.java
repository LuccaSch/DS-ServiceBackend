package com.ds.tp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;

import com.ds.tp.models.dto.BedelDTO;
import com.ds.tp.models.usuario.Administrador;
import com.ds.tp.models.usuario.Bedel;
import com.ds.tp.repositories.AdminRepository;
import com.ds.tp.repositories.BedelRepository;

@Service
public class BedelService {
    // Atributos inyectados por Spring
    @Autowired
    private final BedelRepository bedelRepository;

    @Autowired
    private final EmpresaService empresaService;

    @Autowired
    private final AdminRepository adminRepository;

    // El passwordEncoder es inyectado desde la configuración de seguridad, se puede redefinir en passwordEncoder() dentro de config
    @Autowired
    private final PasswordEncoder passwordEncoder; 

    // Constructor
    public BedelService(BedelRepository bedelRepository, EmpresaService empresaService, PasswordEncoder passwordEncoder, AdminRepository adminRepository) {
        this.bedelRepository = bedelRepository;
        this.empresaService = empresaService;
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
    }

    // Funciones del servicio BEDEL
    public List<BedelDTO> getBedels() {
        List<Bedel> bedelList = this.bedelRepository.findAll();
        return this.crearListaBedelDto(bedelList);
    }

    public ResponseEntity<Object> postBedel(BedelDTO unBedelDTO) {
        HashMap<String, Object> respuesta = new HashMap<>();

        Optional<String> resultadoValidacion = verificarDatos(unBedelDTO);

        // Verificación de datos del bedel
        if (resultadoValidacion.isPresent()) {
            respuesta.put("mensaje", resultadoValidacion.get());
            respuesta.put("estado", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
        
        // Verificar el formato de la contraseña
        if (!validarFormatoContrasenia(unBedelDTO.getContrasenia())) {
            respuesta.put("mensaje", "ERROR: Formato de contraseña invalido");
            respuesta.put("estado", false);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        }

        // Verificar si el usuario ya existe, tanto como bedel como admin.

        Optional<Bedel> resultadoBedel = bedelRepository.findByUsuario(unBedelDTO.getUsuario());
        
        if (resultadoBedel.isPresent()) {
            respuesta.put("mensaje", "Usuario ya está registrado: " + unBedelDTO.getUsuario());
            respuesta.put("estado", false);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        }

        Optional<Administrador> resultadoAdmin = adminRepository.findByUsuario(unBedelDTO.getUsuario());
        
        if (resultadoAdmin.isPresent()) {
            respuesta.put("mensaje", "Usuario ya está registrado: " + unBedelDTO.getUsuario());
            respuesta.put("estado", false);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(respuesta);
        }
        
        // Crear el objeto Bedel con la contraseña encriptada
        Bedel unBedel = crearBedel(unBedelDTO);

        // Guardar el bedel en la base de datos
        try {
            bedelRepository.save(unBedel);
            respuesta.put("mensaje", "El bedel se registró correctamente");
            respuesta.put("estado", true);
            System.out.println("[INFO] Se registró: " + unBedel.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (DataAccessException e) {
            System.out.println("Error de acceso a datos: " + e.getMessage());
            respuesta.put("mensaje", "Internal Server Error" + e.getMessage());
            respuesta.put("estado", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    public Optional<String> verificarDatos(BedelDTO unBedel) {
        Optional<String> resultadoVerificado = Optional.empty();
        
        // Validaciones de campos
        if (unBedel.getUsuario().isEmpty() || unBedel.getNombre().isEmpty() || unBedel.getApellido().isEmpty() 
            || unBedel.getContrasenia().isEmpty() || unBedel.getConfContrasenia().isEmpty() || unBedel.getTurno() == 0) {
            resultadoVerificado = Optional.of("ERROR: Se quiere registrar un campo requerido vacío");
        } else if (!unBedel.getContrasenia().equals(unBedel.getConfContrasenia())) {
            resultadoVerificado = Optional.of("ERROR: Contraseña y confirmación de contraseña no son idénticas");
        }
        
        if (unBedel.getUsuario().length() > 30 || unBedel.getUsuario().length() < 5 || unBedel.getNombre().length() > 40 
            || unBedel.getApellido().length() > 40 || unBedel.getContrasenia().length() > 40 || unBedel.getConfContrasenia().length() > 40) {
            resultadoVerificado = Optional.of("ERROR: Campo con cantidad de dígitos incorrecta");
        }
        
        return resultadoVerificado;
    }

    public Bedel crearBedel(BedelDTO unBedelDTO) {
        // Encriptar la contraseña antes de crear el Bedel
        String contraseniaEncriptada = passwordEncoder.encode(unBedelDTO.getContrasenia());
        
        return new Bedel(unBedelDTO.getUsuario(), unBedelDTO.getNombre(), unBedelDTO.getApellido(), contraseniaEncriptada, unBedelDTO.getTurno(), true);
    }

    public boolean validarFormatoContrasenia(String contrasenia) {
        return empresaService.validarRequerimientoContrasenia(contrasenia);
    }

    public BedelDTO crearBedelDTO(Bedel unBedel){
        return new BedelDTO(unBedel.getUsuario(), unBedel.getNombre(),unBedel.getApellido(),unBedel.getTurno());
    }

    public List<BedelDTO> crearListaBedelDto(List<Bedel> listaBedels){
        return listaBedels.stream()
                        .map(b -> crearBedelDTO(b))
                        .collect(Collectors.toList());
    }
}
