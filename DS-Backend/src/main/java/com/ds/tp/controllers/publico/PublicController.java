package com.ds.tp.controllers.publico;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.tp.services.securityconfig.PasswordEncoderService;

@RestController
@RequestMapping("/public")
    public class PublicController {


    public PasswordEncoderService passwordEncoderService;


    @GetMapping("/home")
    public String home() {
        return "Hola Public";
    }

}
