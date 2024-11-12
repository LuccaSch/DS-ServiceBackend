package com.ds.tp.controllers.bedel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BedelTemplateController {

    public BedelTemplateController(){}

    @GetMapping("/registrarBedel")
    public String mostrarFormularioRegistro() {
        return "registrarBedel";
    }
}
