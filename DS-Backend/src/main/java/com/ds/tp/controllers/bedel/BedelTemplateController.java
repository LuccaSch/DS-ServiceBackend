package com.ds.tp.controllers.bedel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bedel")

public class BedelTemplateController {

    //Constructor
    public BedelTemplateController(){}

    //Menu principal
    @GetMapping()
    public String home(){
        return "menuBedel";
    }

    @GetMapping("/reservas")
    public String misReservas(){
        return "buscarReserva";
    }

}
