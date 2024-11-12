package com.ds.tp.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.tp.models.dto.BedelDTO;
import com.ds.tp.models.usuario.Bedel;
import com.ds.tp.services.BedelService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin/rest")
//ENDPOINT para la api rest
public class AdminRestController {
    @Autowired
    private final BedelService bedelService;

    //constructor
    public AdminRestController(BedelService bedelService){
        this.bedelService = bedelService;
    }

    @GetMapping("/getBedel")
    public List<Bedel> getBedel(){
        return bedelService.getBedels();
    }

    @PostMapping("/postBedel")
    public ResponseEntity<Object> registrarBedel(@RequestBody BedelDTO unBedelDTO, HttpServletRequest request) {
        
        //Sistema de logs, en este caso muestra la ip del cliente que solicita el registro, a futuro debemos almacenarlos en un txt y usar log4j
        String clientIp = request.getRemoteAddr();
        System.out.println("[INFO] Registro de bedel desde IP: " + clientIp);

        return this.bedelService.postBedel(unBedelDTO);
    }
}
