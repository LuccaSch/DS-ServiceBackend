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

    @GetMapping("/seleccionarTipoReserva")
    public String tipoReserva(){
        return "seleccionarTipoReserva";
    }
    @GetMapping("/reservaPeriodica")
    public String reservaPeriodica(){
        return "reservaPeriodica";
    }
    @GetMapping("/reservaEsporadica")
    public String reservaEsporadica(){
        return "reservaEsporadica";
    }
}
